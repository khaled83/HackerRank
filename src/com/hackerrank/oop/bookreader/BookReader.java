//package com.hackerrank.oop.bookreader;
//
//import java.util.*;
//
//public class BookReader {
//	// DB users cached in memory <username, User>
//	private final HashMap<String, User> users = new HashMap<String, User>();
//	private User activeUser;
//	/**
//	Hashmap: needs exact search key.
//	SortedArray(List): lookup=O(log(N)), insertion: O(N), growth: O(N)
//	SortedLinkedList: lookup=O(N), insertion: O(N), growth: O(1)
//	TreeMap: lookup=O(log(N)), insertion: O(log(N)), growth: O(1)
//
//	Tri: I'm not very familir with it, but it could be a good solution.
//	(Story)
//	 G
//	/ \
//	O 
//	\
//	 G
//	*/
//	/** store work tokens rather than complete titles 
//	    search for similar match over each key, e.g. lavenstiein distance
//	*/
//	// DB books cached in memory
//	private final BookTreeMap<String,Book> books = new BookTreeMap<String, Book>; 
//
//	private static final BookReader instance = new BookReader();
//
//	public static HttpServletResponse response( HttpServletRequest request ) 
//	{
//	// Request(1) login. Request attributes: un, pw 
//	BookReader reader = BookReader.getInstance();
//	User user = User.login( username, password );
//	// Request(2) search. Request attr: bookTitle
//	// pre: display in results list web page -> user selects book
//	Book book = Book.search( bookTitle ).get( 0 );
//	// Request(3) read. Request attr: bookTitle
//	Book.read( user, book );
//	}
//
//	// saves the status of system into DB
//	public void save() {  }
//
//	// loads the status of system from DB. e,g, upon booting up
//	public void load() {  }
//
//
//	private BookReader() {}
//	public static BookReader getInstance() { return instance; }
//
//	public static User singup( String username, String password, String details ) 
//	{ 
//	User user = new User();
//	user.username = username; user.password = password; user.details = details;
//	users.put( username, user );
//	}
//
//	public static User singin( String username, String password ) 
//	{
//	User user = users.get( username );
//	// simple validation; typically in a separate method
//	if( ! user.getPassword().equals( password ) )
//	return null;
//	return user;
//	}
//
//	public static void singout() {
//	activeUser = null;
//	}
//
//	public static boolean validate(User user ) {
//	return activeUser != null && activeUser.equals( user );
//	}
//
//	}