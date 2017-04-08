package com.khaledabbas.concurrency;

import java.util.Arrays;
import java.util.Collections;

public class Concurrency {
	
	public static void main() throws InterruptedException
	{
		Thread t = new Thread()
		{
			public void run()
			{
				System.out.println("Hello world from new thread");
			}
		};
		
		t.start();
//		Thread.yield();
		Thread.sleep(1);
		
		System.out.println("Hello world from Main thread");
		t.join();
		
		class Counter
		{
			private int count;
			
			public synchronized void increment() { count++; }
			
			public int getCount() { return count; }
		}
		
		final Counter counter = new Counter();
		
		class CounterThread extends Thread
		{
			public void run()
			{
				for(int i=0; i<10000; i++)
					counter.increment();
			}
		}
		
		CounterThread t1 = new CounterThread();
		CounterThread t2 = new CounterThread();
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
		for(int i=0; i<10; i++)
		{
			Puzzle p = new Puzzle();
			p.t1.start();
			p.t2.start();
			p.t1.join();
			p.t2.join();
		}
		
		System.out.println(counter.getCount());
	}
	
	private static class Puzzle
	{
		private boolean answerReady = false;
		private int answer = 0;
		
		private Thread t1 = new Thread() {
			public void run() {
				answer = 42;
				answerReady = true;
			}
		};
		
		private Thread t2 = new Thread() {
			public void run() {
				if(answerReady)
					System.out.println("The meaning of life is " + answer);
				else
					System.out.println("I don't know the answer");
			}
		};
	}

}

























