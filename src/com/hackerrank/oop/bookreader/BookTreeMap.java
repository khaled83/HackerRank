//package com.hackerrank.oop.bookreader;
//
//import java.util.*;
//
//public class BookTreeSet {
//
//	private Node root;
//
//	private static class Node {
//		String key;
//		TreeSet<Book> books = new TreeSet<Book>();
//		Node leftChild, rightChild;
//	}
//
//	public List<Book> serach(User user, String title) {
//		if (!BookReader.validate(user))
//			throw new InvalidUserException("");
//		List<Book> results = new ArrayList<Book>();
//		search(root, title, Integer.MAX_VALUE, results);
//		return results;
//	}
//
//	private Node search( Node cur, String title, int minDistance, List<Book> results ) {
//if( cur == null || cur.key.equals( title ) {
//results.clearAll();
//results.addAll( cur.books );
//return cur;
//}
//else {
//// calculate lavenstein distance
//int distance = lavDistance();
//if( distance < minDistance ) {
//minDistance = distance;
//results.clear();
//}
//else if( distance == minDistance ) 
//results.addAll( cur.books );
//// continue searching
//else if( title.compareTo( cur.key ) < 0 ) {
//return search( cur.leftChild, title, minDistance, results );
//}
//else if( title.compareTo( cur.key ) > 0 ) {
//return search( cur.rightChild, title, minDistance, results );
//}
//}
//
//}
//
//	public Book readBook(User user, Book book) {
//		if (!BookReader.getInstance().validate(user))
//			throw new InvalidUserException("");
//		if (!user.getActiveBook().equals(book))
//			throw new InvalidBookExcception("");
//
//		// logic for reading a book
//		return book;
//	}
//	
//	public int lavDistance() {
//		return 0;
//	}
//
//}
