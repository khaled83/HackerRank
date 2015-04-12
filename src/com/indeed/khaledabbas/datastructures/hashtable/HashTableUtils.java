package com.indeed.khaledabbas.datastructures.hashtable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.indeed.khaledabbas.datastructures.ArrayList;

public class HashTableUtils {

	public static void main(String[] args) {
		List<String> words = Arrays.asList(new String[] 
				{ "debitcard", "elvis", "silent", "badcredit", "lives", "freedom", "listen", "levis" } );
		Collection<ArrayList<String>> groups = groupAnagrams(words);
		for(ArrayList<String> group : groups) {
			System.out.println(group.toString());
		}
	}

	private static class StringAnagram {

		String value;

		StringAnagram(String value) {
			this.value = value;
		}

		@Override
		public int hashCode() {
			int hash = 17;
			for (char c : value.toCharArray())
				hash = hash + ((int) c);
			return hash;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof StringAnagram))
				return false;

			StringAnagram another = (StringAnagram) o;
			char[] chars = another.value.toCharArray();
			Arrays.sort(chars);
			;
			String anotherValue = anotherValue = new String(chars);

			chars = value.toCharArray();
			Arrays.sort(chars);
			String thisValue = new String(chars);
			return thisValue.equals(anotherValue);
		}

	}

	public static Collection<ArrayList<String>> groupAnagrams(List<String> stringList) {
		HashMap<StringAnagram, ArrayList<String>> groups = new HashMap<StringAnagram, ArrayList<String>>();
		for (String s : stringList) {
			StringAnagram key = new StringAnagram(s);
			ArrayList<String> group = groups.get(key);

			if (group == null)
				group = new ArrayList<String>();
			
			group.add(s);
			groups.put(key, group);
		}

		return groups.values();

	}

}
