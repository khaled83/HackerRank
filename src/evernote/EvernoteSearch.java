package evernote;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class EvernoteSearch {
	
	private enum COMMAND { CREATE, UPDATE, DELETE, SEARCH }
	
	private enum TAG { GUIID, CONTENT }
	
	private static EvernoteSearch instance = new EvernoteSearch();
	
	private static Set<EvernoteDocIndex> docs = new TreeSet<EvernoteDocIndex>();
	
	public static void main(String[] args) throws SAXException, IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		Scanner sc = new Scanner(System.in);
		
		StringBuilder xml = new StringBuilder();
		COMMAND curCMD = COMMAND.CREATE;
		String param = "";
		
		while(sc.hasNext() )
		{
			String token = sc.nextLine();
			
			if(isCreate(token))
			{
				parseXml(xml.toString(), curCMD);
				xml = new StringBuilder();
				
				curCMD = COMMAND.CREATE;
			}
			else if(isUpdate(token))
			{
				// TOOD update logic
				parseXml(xml.toString(), curCMD);
				xml = new StringBuilder();
				
				curCMD = COMMAND.UPDATE;
			}
			else if(isDelete(token))
			{
				// TOOD delete logic
				parseXml(xml.toString(), curCMD);
				xml = new StringBuilder();
				
				curCMD = COMMAND.DELETE;
			}
			else if(isSearch(token))
			{
				// TODO search logic
				parseXml(xml.toString(), curCMD);
				xml = new StringBuilder();
				
				curCMD = COMMAND.SEARCH;
			}
			// construct XML document
			else if( isCreate(curCMD.toString()) || isUpdate(curCMD.toString()))
				xml.append(token);
			else if(isSearch(curCMD.toString()))
				System.out.println( search(token) );
			
			if(token == null)
				return;
				
		}
		
		sc.close();
    }
	
	private static void parseXml(String xml, COMMAND CMD) throws SAXException, IOException
	{
		System.out.println("parse: " + xml);
		
		if(xml.length() <= 0)
			return;
		
		XMLReader parser = XMLReaderFactory.createXMLReader();
		
		EvernoteDocIndex docIndex;
		if(isCreate(CMD.toString()))
		{
			EvernoteHandler handler = instance.new EvernoteHandler();
			parser.setContentHandler(handler);
			parser.parse( new InputSource( new StringReader( xml ) ) );
			docs.add(handler.doc);
		}
		else if(isUpdate(CMD.toString())) {  } // TODO: implement update
		else if(isSearch(CMD.toString()))
		{
			
		}
		
	}
	
	private static List<String> search(String term)
	{
		List<String> docIds = new ArrayList<String>();
		for(EvernoteDocIndex doc : docs )
			if(doc.contains(term))
				docIds.add(doc.guid);
		
		return docIds;
	}
	
	/**
	 * verifies whether the input line is part of xml content
	 */
	private static final boolean isXml(String inputLine)
	{
		return !isCommand(inputLine);
	}
	
	// TODO: remove
	/**
	 * Returns true if the input line is one of the commands: CREATE, UPDATE, DELETE, SEARCH
	 * @param line	input line from console
	 * @return	true if this is a command, false otherwise; it's xml contnt
	 */
	private static boolean isCommand(String inputToken)
	{
		return isCreate(inputToken)
				|| isUpdate(inputToken)
				|| isDelete(inputToken)
				|| isSearch(inputToken);
	}
	
	private static boolean isCreate(String inputToken)
	{
		return inputToken.equals(COMMAND.CREATE.toString());
	}
	
	private static boolean isUpdate(String inputToken)
	{
		return inputToken.equals(COMMAND.UPDATE.toString());
	}
	
	private static boolean isSearch(String inputToken)
	{
		return inputToken.equals(COMMAND.SEARCH.toString());
	}
	
	private static boolean isDelete(String inputToken)
	{
		return inputToken.equals(COMMAND.DELETE.toString());
	}
	
	private class EvernoteHandler extends DefaultHandler
	{
		private EvernoteDocIndex doc = null;
		
		private boolean content = false;
		private boolean guid = false;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
		{
			if(qName.equalsIgnoreCase(TAG.GUIID.toString()))
				guid = true;
			else if(qName.equalsIgnoreCase(TAG.CONTENT.toString()))
				content = true;
		}
		
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException 
		{
			if(content)
				doc.index(new String(ch, start, length));
			else if(guid)
				doc = new EvernoteDocIndex(new String(ch, start, length));
		}
		
	}
	
	private class EvernoteDocIndex implements Comparable<EvernoteDocIndex>
	{
		private final String guid;
		
		// TODO Created Timestamp
		
		// TODO: handle tags
		// private static List<String> tags = new ArrayList<String>();
		
		// stores number of occurences of each ASCII char in content; used for level-1 quick search for fast failure
		int[] quickHash = new int[256];
		
		// stores a hash map of exact words
		HashSet<String> exactHashSet = null;
		
		// stores a datastructrure for handling prefixes
		List<TreeSet<String>> prefixSet = null;
		
		private EvernoteDocIndex(String guid) 
		{
			this.guid = guid;
		}
		
		private EvernoteDocIndex create(String guid)
		{
			return new EvernoteDocIndex(guid);
		}
		
		private void index(String content)
		{
			// level-1 quick hashing
			for(char c : content.toCharArray())
				quickHash[ (int) c ]++;
			
			StringTokenizer tokenizer = new StringTokenizer(content);
			
			// a hashset used for searching exact match words
			exactHashSet = new HashSet<String>( ( tokenizer.countTokens() * 2 ) / 3);
			
			// a data structure to search for  
			prefixSet = new ArrayList<TreeSet<String>>(256);
			
			while(tokenizer.hasMoreTokens())
			{
				String word = tokenizer.nextToken();
				exactHashSet.add(word);
				prefixSet.get( (int) word.charAt(0)).add(word);
			}
		}
		
		private boolean contains(String word)
		{
			if(word.charAt(word.length() - 1) == '*')
				return quickSearch(word) && prefixSearch(word);
			else
				return quickSearch(word) && exactSearch(word);
		}
		
		
		private boolean quickSearch(String word)
		{
			// level-1 quich search
			Map<Character, Integer> asciiCount = new HashMap<Character, Integer>();
			for(char c : word.toCharArray())
				if(asciiCount.containsKey(c))
					asciiCount.put(c, asciiCount.get(c) + 1);
				else
					asciiCount.put(c, 1);
			
			for(Character c : asciiCount.keySet())
				if( asciiCount.get(c) > quickHash[(int) c] )
					return false;
			
			return true;
		}
		
		private boolean exactSearch(String word)
		{
			return exactHashSet.contains(word);
		}
		
		private boolean prefixSearch(String prefix)
		{
			prefix = prefix.substring(0, prefix.length() - 1 );
			
			// tree of all Words that start with the same initial
			TreeSet<String> tree = prefixSet.get( (int) prefix.charAt(0) );
			String match = tree.ceiling(prefix);
			return match.indexOf(prefix) >= 0;
			
		}
		

		@Override
		public int compareTo(EvernoteDocIndex o) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
