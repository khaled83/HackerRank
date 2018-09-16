package com.cleancode.names;

import java.util.Calendar;
import java.util.Date;

public class Names {
	
	/**=== Example 1: Add Months ===*/
	public static Calendar addMonthsOld(final int months, final Date base) {
		final int yy = (12 * base.getYear() + base.getMonth() + months - 1) / 12;
		final int mm = (12 * base.getYear() + base.getMonth() + months - 1) % 12 + 1;
		Calendar cc = Calendar.getInstance();
		Calendar baseCalendar = Calendar.getInstance();
		baseCalendar.setTime(base);
		cc.setTime(new Date(yy, mm, 1));
		final int dd = Math.min(baseCalendar.get(Calendar.DAY_OF_MONTH),
				cc.getActualMaximum(Calendar.MONTH));
		Calendar res = Calendar.getInstance();
		res.setTime(new Date(yy, mm, dd));
		return res;
	}
	
	
	public static Date addMonths(final int monthsToAdd, final Date baseDate) {
		Calendar baseCalendar = Calendar.getInstance();
		baseCalendar.setTime(baseDate);
		int baseDay = baseCalendar.get(Calendar.DAY_OF_MONTH);
		int baseMonth = baseCalendar.get(Calendar.MONTH) - Calendar.JANUARY;
		int baseYear = baseCalendar.get(Calendar.YEAR);
		int resultMonth = ((baseMonth + monthsToAdd) % 12) + Calendar.JANUARY;
		int resultYear = baseYear + (resultMonth / 12);
		Calendar resultCalendar = Calendar.getInstance();
		resultCalendar.set(resultYear, resultMonth, baseDay);
		int resultDay = Math.min(baseDay, resultCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		resultCalendar.set(Calendar.DAY_OF_MONTH, resultDay);
		return resultCalendar.getTime();
	}
	
	/**=== Example 2: Is In Range ===*/
	final static int INCLUDE_BOTH = 0;
	final static int INCLUDE_FIRST = 1;
	final static int INCLUDE_SECOND = 2;
	
	private enum DateInterval {
		OPEN, CLSOED, OPEN_LEFT, OPEN_RIGHT
	}
	
	public boolean isInRangeOld(final Date d1, final Date d2, final int include) {
		final long s1 = d1.getTime();
		final long s2 = d2.getTime();
		final long start = Math.min(s1,  s2);
		final long end = Math.max(s1, s2);
		
		final long d = new Date().getTime();
		if (include == INCLUDE_BOTH) {
			return (d >= start && d <= end);
		} else if (include == INCLUDE_FIRST) {
			return (d >= start && d <end);
		} else if (include == INCLUDE_SECOND) {
			return (d > start && d <= end);
		} else {
			return (d > start && d < end);
		}
	}
	
	// my attempt
	public boolean isOverlapsCurrentTime(final Date someDate, final Date anotherDate, boolean allowCurrentTimeEqualsFirst, boolean allowCurrentTimeEqualsSecond) {
		final long first = Math.min(someDate.getTime(), anotherDate.getTime());
		final long second = Math.max(someDate.getTime(), anotherDate.getTime());
		final long currentTime = Calendar.getInstance().getTimeInMillis();
		if (!allowCurrentTimeEqualsFirst && first == currentTime) {
			return false;
		}
		else if (!allowCurrentTimeEqualsSecond && second == currentTime) {
			return false;
		}
		else {
			return first <= currentTime && second >= currentTime; 
		}
	}
	
	public boolean isCurrentTimeInDateRange(final Date d1, final Date d2, final DateInterval intervalType) {
		final long lowerBound = Math.min(d1.getTime(), d2.getTime());
		final long upperBound = Math.max(d1.getTime(), d2.getTime());
		final long currentTime = Calendar.getInstance().getTimeInMillis();
		if (intervalType == DateInterval.OPEN_LEFT && lowerBound == currentTime) {
			return false;
		}
		else if (intervalType == DateInterval.OPEN_RIGHT && upperBound == currentTime) {
			return false;
		}
		else {
			return lowerBound <= currentTime && upperBound >= currentTime;
		}
	}
	
	
	/**=== Example 3: Functions ===*/
	public static class SetupTeardownIncluder {
		
		private PageData pageData;
		boolean isSuite;
		private WikiPage wikiPage;
		private StringBuffer content;
		private PageCrawler pageCrawler;
		
		public static String render(PageData pageData) {
			return render(pageData, false);
		}
		
		public static String render(PageData pageData, boolean isSuite) {
			return new SetupTeardownIncluder(pageData).render(isSuite);
		}
		
		private SetupTeardownIncluder(PageData pageDate) {
			this.pageData = pageData;
			wikiPage = pageData.getWikiPage();
			pageCrawler = wikiPage.getPageCrawler();
			content = new StringBuffer();
		}
		
		private String render(boolean isSuite) {
			this.isSuite = isSuite;
			if(isTestPage())
				includeSetupAndTeardownPages();
			updatePageContent();
			return pageData.getHtml();
		}
		
		private void includeSetupAndTeardownPages() {
			includeSetupPages();
			includePageContent();
			includeTeardownPages();
			updatePageContent();
		}
		
		private void includeSetupPages() {
			if (isTestPage())
				includeTestPages();
		}

		private void includeTestPages() {
			if(isSuite)
				includeSuiteSetupPage();
			includeSetupPage();
		}

		private void includeSuiteSetupPage() {
			include(SuiteResponder.SUITE_SETUP_NAME, "-setup");
		}
		
		private void includeSetupPage() {
			include("SetUp", "-setup");
		}
		
		private void includeTeardownPages() {
			includeTeardownPage();
			if(isSuite)
				includeTeardownSuitePage();
		}

		private void includeTeardownPage() {
			include("TearDown", "-teardown");
		}
		
		private void includeTeardownSuitePage() {
			include(SuiteResponder.SUITE_TEARDOWN_NAME, "-teardown");
		}

		private void include(String pageName, String arg) {
			WikiPage inheritedPage = findInheritedPage(pageName);
			if (inheritedPage != null) {
				String pagePathName = getPathNameForPage(inheritedPage);
				buildIncludeDirective(pagePathName, arg);
			}
		}

		private WikiPage findInheritedPage(String pageName) {
			return PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
		}
		
		private WikiPagePath getPagePath(WikiPage inheritedPage) {
			return pageCrawler.getFullPath(inheritedPage);
		}
		
		private String getPathNameForPage(WikiPage page) {
			WikiPagePath pagePath = getPagePath(page);
			return PathParser.render(pagePath);
		}
		
		private void buildIncludeDirective(String pagePathName, String arg) {
			content.append("!include ").append(arg).append(" .").append(pagePathName).append("\n");
		}
		
		private void includePageContent() {
			content.append(pageData.getContent());
		}
		
		private void updatePageContent() {
			pageData.setContent(content.toString());
		}

		private boolean isTestPage() {
			return pageData.hasAttribute("Test");
		}

	}
	
	public class PageCrawler {

		public WikiPagePath getFullPath(WikiPage suiteSetup) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public static class PathParser {

		public static String render(WikiPagePath pagePath) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class WikiPagePath {

	}

	public static class SuiteResponder {

		public static final String SUITE_SETUP_NAME = "Suite SetUp";
		public static final String SUITE_TEARDOWN_NAME = "Suite TearDown";

	}

	public static class PageCrawlerImpl {

		public static WikiPage getInheritedPage(String suiteSetupName, WikiPage wikiPage) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class WikiPage {

		public PageCrawler getPageCrawler() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class PageData {

		public WikiPage getWikiPage() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean hasAttribute(String string) {
			// TODO Auto-generated method stub
			return false;
		}

		public Object getContent() {
			// TODO Auto-generated method stub
			return null;
		}

		public void setContent(String string) {
			// TODO Auto-generated method stub
			
		}

		public String getHtml() {
			// TODO Auto-generated method stub
			return null;
		}

	}
	
}
