//package com.hackerrank.oop.bookreader;
//
//import java.util.*;
//
//public class User {
//	private String username, password, fullName, details;
//	private boolean isActive;
//	private Book activeBook;
//
//	public static User singup(String username, String password, String details) {
//		BookReader.getInstance().singup(username, password, details);
//	}
//
//	public static User singin( String username, String password ) {
//User user = BookReader.getInstance().singin( username, password );
//if( user == null )
//throw new InvalidCredentialsException(“Username/password invalid.”);
//}
//
//	public void singout() {
//		BookReader.getInstance().signout();
//	}
//}
