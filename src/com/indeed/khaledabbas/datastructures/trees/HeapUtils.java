package com.indeed.khaledabbas.datastructures.trees;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HeapUtils {
	
	public static void main(String[] args)
	{
		mergeFiles("/Users/khaledabbas/Documents/workspace/Hacker Rank/data/heapmerge");
	}

	private static class Pair implements Comparable<Pair> {
		BufferedReader reader;
		String line;

		Pair(BufferedReader reader, String line) {
			this.reader = reader;
			this.line = line;
		}

		public int compareTo(Pair o) {
			return this.line.compareTo(o.line);
		}
	}

	public static void mergeFiles(String path) {
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>();
		ArrayList<BufferedReader> readers = new ArrayList<BufferedReader>();
		
		Charset charset = Charset.forName("UTF-8"); // check syntax
		Path output = Paths.get("/Users/khaledabbas/Documents/workspace/Hacker Rank/data/heapmerge/output.txt");

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path)) ) {
			for (Path file : stream) {
				System.out.println(file.toString());
				BufferedReader reader = Files.newBufferedReader(file, charset);
				readers.add(reader);
				heap.add( new Pair( reader, reader.readLine() ) );
			}

			try (BufferedWriter writer = Files.newBufferedWriter(output, charset)) {
				while (!heap.isEmpty()) {
					Pair pair = heap.remove();

					writer.write(pair.line + "\n");
					String line = pair.reader.readLine();
					if (line != null)
						heap.add(new Pair(pair.reader, line));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			

			// close resources
			for (BufferedReader reader : readers) {
				reader.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
