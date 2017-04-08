package com.indeed.onlinetest.task1;

import java.util.*;

public class Main {
	
	@SuppressWarnings("unused")
	private static final Main instance = new Main();

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		// "dcbadcbadcbadde"
		// a ... z: 26 chars
		// 0 ... 25
		CharWithCount[] charCountArr = new CharWithCount[26];
		
		for(char c : s.toCharArray())
		{
			int charIndx = c - 'a';
			if(charCountArr[charIndx] == null)
			{
				CharWithCount newCount = new CharWithCount();
				newCount.ch = c;
				newCount.count = 1;
				charCountArr[charIndx] = newCount;
			}
			else
			{
				CharWithCount charCount = charCountArr[charIndx];
				charCount.count++;
			}
		}
		
		// filter out nulls
		ArrayList<CharWithCount> charCountList = new ArrayList<CharWithCount>();
		for(CharWithCount charWithCount : charCountArr)
		{
			if(charWithCount != null)
				charCountList.add(charWithCount);
		}
		
		Collections.sort(charCountList, new Comparator<CharWithCount>() {
			@Override
			public int compare(CharWithCount o1, CharWithCount o2) {
				int countComparison = o1.count.compareTo(o2.count);
				if(countComparison != 0)
					return -countComparison;
				else 
					return o1.ch.compareTo(o2.ch);
			}
		});
		
		for(CharWithCount charWithCount : charCountList)
		{
			System.out.println(charWithCount.ch);
		}
		
		
		sc.close();

	}
	
	private static class CharWithCount 
	{
		Character ch;
		Integer count;
		
	}

}
