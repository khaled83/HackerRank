package com.cleancode.names;

import java.util.Arrays;
import java.util.List;

public class Functions {
	
	public static void main(String[] args) {
		Integer[] data = Arrays.stream(PrimeNumbers.generatePrimeNumbers(1000)).boxed().toArray(Integer[]::new);
		Printer.printOnConole(data);
	}
	
	static class PrimeNumbers {
		
		public static int[] generatePrimeNumbers(int count) {
			final int ORDMAX = 30;
			int[] res = new int[count + 1];
			int J;
			int K;
			boolean JPRIME;
			int ordinal;
			int sqr;
			int N = 0;
			int[] mul = new int[ORDMAX + 1];

			J = 1;
			K = 1;
			res[1] = 2;
			ordinal = 2;
			sqr = 9;

			while (K < count) {
				do {
					J += 2;
					if (J == sqr) {
						ordinal++;
						sqr = res[ordinal] * res[ordinal];
						mul[ordinal - 1] = J;
					}
					N = 2;
					JPRIME = true;
					while (N < ordinal && JPRIME) {
						while (mul[N] < J)
							mul[N] += res[N] + res[N];
						if (mul[N] == J)
							JPRIME = false;
						N++;
					}
				} while (!JPRIME);
				K++;
				res[K] = J;
			}
			return res;
		}
		
	}
	
	static class Printer {
		
		private List<?> data;
		private int pageNumber;
		private int pageOffset;
		private int maxRows;
		private static final int DEFAULT_MAX_ROWS = 50;
		private int maxCols;
		private static final int DEFAULT_MAX_COLS = 4;
		StringBuilder content;
		private static final PrintLayout DEFAULT_LAYOUT = PrintLayout.GRID;
		private PrintLayout layout;
		
		private enum PrintLayout {
			GRID
		}
		
		public static void printOnConole(Object[] content) {
			List<Object> list = Arrays.asList(content);
			new Printer(list).printOnConole();
		}
		
		public static void printOnConole(List<?> content) {
			new Printer(content).printOnConole();
		}
		
		private Printer(List<?> content) {
			this.data = content;
			pageNumber = 1;
			pageOffset = 1;
			maxRows = DEFAULT_MAX_ROWS;
			maxCols = DEFAULT_MAX_COLS;
			layout = DEFAULT_LAYOUT;
		}
		
		private void printOnConole() {
			generateContent();
			System.out.println(content);
		}
		
		private void generateContent() {
			while (pageOffset <= length()) {
				page();
				pageNumber++;
				pageOffset += maxRows * maxCols;
			}
		}

		private void page() {
			header();
			body();
			footer();
		}

		private void body() {
			switch (layout) {
				case GRID: bodyGridLayout();
			}
		}

		private void bodyGridLayout() {
			for (int row = pageOffset; row <= pageOffset + maxRows - 1; row++) {
				for (int col = 0; col <= maxCols - 1 && row + col * maxRows <= length(); col++)
					cell(valueAt(row, col));
				newline();
			}
		}

		private Object valueAt(int row, int col) {
			return data.get(row + col * maxRows);
		}

		private void cell(Object text) {
			content.append(String.format("%10d", text.toString()));
		}

		private void footer() {
			content.append("\f");
		}

		private void header() {
			text("The First")
			.space()
			.total()
			.text("Prime Numbers --- Page ")
			.pageNumber()
			.newline();
		}

		private Printer text(String arg) {
			content.append(arg);
			return this;
		}
		
		private Printer space() {
			content.append(" ");
			return this;
		}
		
		private Printer newline() {
			content.append("\n");
			return this;
		}
		
		private Printer total() {
			content.append(length());
			return this;
		}
		
		private Printer pageNumber() {
			content.append(pageNumber);
			return this;
		}
		
		private int length() {
			return data.size();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
