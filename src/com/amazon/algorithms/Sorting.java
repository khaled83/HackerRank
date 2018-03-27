package com.amazon.algorithms;

import java.util.*;

import com.khaledabbas.datastructures.arrays.ArrayUtils;

public abstract class Sorting {

	public static void selectionSort(int[] arr) {
		// total: n-1
		for (int unsorted = arr.length - 1; unsorted >= 1; unsorted--) {
			// 1 * (n-1)+(n-2)+...+1 = n (n-1) / 2
			int largestIndx = largestIndx(arr, unsorted + 1);
			// 3 * (n-1)
			swap(arr, unsorted, largestIndx);
		}
	}

	private static int largestIndx(int[] arr, int n) {
		int indxSoFar = 0;
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[indxSoFar])
				indxSoFar = i;
		}
		return indxSoFar;
	}

	public static void bubbleSort(int[] arr) {
		int n = arr.length;
		boolean sorted = false;
		for (int pass = 1; pass < n && !sorted; pass++) {
			sorted = true;
			int unsorted = n - pass;
			for (int i = 0; i < unsorted; i++) {
				if (arr[i] > arr[i + 1]) {
					swap(arr, i, i + 1);
					sorted = false;
				}
			}
		}
	}

	// @TODO implement again
	public static void insertionSort(int[] arr) {
		int n = arr.length;
		for (int unsorted = 1; unsorted < n; unsorted++) {
			int next = arr[unsorted];
			int loc = unsorted;
			while (loc >= 1 && arr[loc - 1] > next) {
				arr[loc] = arr[loc - 1];
				--loc;
			}
			arr[loc] = next;
		}
	}

	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	private static void mergeSort(int[] arr, int first, int last) {
		if (first < last) {
			int mid = (first + last) / 2;
			mergeSort(arr, 0, mid);
			mergeSort(arr, mid + 1, last);
			merge(arr, first, mid, last);
		}
	}

	private static void merge(int[] arr, int first, int mid, int last) {
		int[] temp = new int[arr.length];
		// first sorted half
		int first1 = first;
		int last1 = mid;
		// second sorted half
		int first2 = mid + 1;
		int last2 = last;

		int index = first1; // index in temp

		for (; first1 <= last1 && first2 <= last2; index++) {
			if (arr[first1] < arr[first2]) {
				temp[index] = arr[first1];
				first1++;
			} else {
				temp[index] = arr[first2];
				first2++;
			}
		}

		// finish off first half
		for (; first1 <= last1; first1++) {
			temp[index++] = arr[first1];
		}

		// finish off second half
		for (; first2 <= last2; first2++) {
			temp[index++] = arr[first2];
		}

		// copy temp into arr
		for (index = first; index <= last; index++) {
			arr[index] = temp[index];
		}
	}

	public static void quickSort(int[] arr) {
		int first = 0;
		int last = arr.length - 1;
		int pivot = choosePivot(arr, first, last);
		sort(arr, pivot, first, last);
	}

	public static void sort(int[] arr, int pivot, int first, int last) {
		if (first < last) {
			// sort whole array
			int newPivot = quicksort(arr, choosePivot(arr, first, last), first, last);
			// sort left partion before new pivot
			quicksort(arr, choosePivot(arr, first, newPivot - 1), first, newPivot - 1);
			// sort right partition after new pivot
			quicksort(arr, choosePivot(arr, newPivot + 1, last), newPivot + 1, last);
		}
	}

	private static int quicksort(int[] arr, int pivot, int first, int last) {
	    /**
	    arr    29 10 14 37 13
	           p
	              [
	                         ]
	              u
	           s1
	    */
		// move pivot to first location
		swap(arr, pivot, first);

		int firstUnknown = first + 1;
		int lastS1 = first; // empty S1

		for (; firstUnknown <= last; firstUnknown++) {
			if (arr[firstUnknown] < arr[pivot]) {
				swap(arr, firstUnknown, lastS1 + 1);
				lastS1++;
			}
		}

		// move pivot to its proper location
		swap(arr, first, lastS1);

		// return new pivot point
		return lastS1;
	}

	private static int choosePivot(int[] arr, int first, int last) {
		return first;
	}

	public static void radixSort(String[] arr) {
		if (arr == null || arr.length == 0)
			return;

		// ascii char groups, location matches ascii code
		LinkedList<String>[] groups = null;

		// result contains strings in the right order so far
		LinkedList<String> result = new LinkedList(Arrays.asList(arr));

		// assume string length is fixed
		int d = arr[0].length();

		for (int loc = d - 1; loc >= 0; loc--) {
			groups = new LinkedList[128];

			for (String s : result) {
				char c = s.charAt(loc);
				int group = (int) c;
				if (groups[group] == null)
					groups[group] = new LinkedList<String>();
				groups[group].add(s);
			}

			result = new LinkedList<String>();
			for (int group = 0; group < groups.length; group++) {
				if (groups[group] != null)
					result.addAll(groups[group]);
			}
		}

		int i = 0;
		for (String s : result) {
			arr[i++] = s;
		}
	}
	
	// O (m*n)
	public static void merge1(int[] A, int[] B) {
	    for (int unsorted = A.length - B.length, cur = 0; 
	            unsorted < A.length && cur < B.length;
	            unsorted++, cur++)
	    {
	        A[unsorted] = B[cur];
	        // insert element into sorted location
	        for (int i = unsorted; i >= 1; i--)
	            if (A[i] < A[i-1])
	                swap(A, i, i-1);
	    }
	}
	
	public static void merge2(int[] A, int lastA, int[] B, int lastB) {
		for (int a = lastA, b = lastB, dst = lastA + lastB + 1; b >= 0; dst--) {
			if (A[a] > B[b])
				A[dst] = A[a--];
			else
				A[dst] = B[b--];
		}
	}
	
	// O (m+n)
	public static void merge2(int[] A, int[] B) {
	    for (int indxA = A.length - B.length - 1, 
	         indxB = B.length - 1,
	         mergeIndx = A.length - 1;
	         indxB >= 0;
	         mergeIndx--) 
	     {
	         if (indxA >= 0 && A[indxA] > B[indxB])
	             A[mergeIndx] = A[indxA--];
	         else
	             A[mergeIndx] = B[indxB--];
	     }
	}

	public static void swap(int[] arr, int x, int y) {
	    int tmp = arr[x];
	    arr[x] = arr[y];
	    arr[y] = tmp;
	}    
	
	public static void sortAnagrams(String[] arr) {
	    Arrays.sort(arr, new AnagramComparator());
	}

	public static void sortAnagrams2(String[] arr) {
		HashMap<Integer, LinkedList<String>> groups = new HashMap<Integer, LinkedList<String>>();
		for (String s : arr) {
			int numericValue = getNumericValue(s);
			if (groups.get(numericValue) == null)
				groups.put(numericValue, new LinkedList<String>());
			groups.get(numericValue).add(s);
		}

		int index = 0;
		for (LinkedList<String> list : groups.values())
			for (String s : list)
				arr[index++] = s;
	}

	private static int getNumericValue(String s) {
		int result = 0;
		for (char c : s.toCharArray())
			result += c;
		return result;
	}
	
	private static class AnagramComparator implements Comparator<String> {

	    public int compare(String s1, String s2) {
	        return getNumericValue(s1).compareTo(getNumericValue(s2));
	    }
	    
	    private Integer getNumericValue(String s) {
	        int result = 0;
	        for (char c : s.toCharArray())
	            result += c;
	        return result;
	    }

	}
	
	public static void sortDutchNationalFlag(int[] arr, int pIndx) {
	    int p = arr[pIndx];
	    int lastPivotIndx = -1;
	    // move pivots to the beginning
	    for (int i=0; i<arr.length; i++) {
	        if (arr[i] == p) {
	            swap(arr, i, ++lastPivotIndx);
	        }    
	    }
	    
	    // split elements after pivot into s1(smaller) and s2(larger)
	    // s1 is defined as range [lastPivotIndx+1, s2-1]
	    int s2 = lastPivotIndx+1;
	    for (int i=lastPivotIndx+1; i<arr.length; i++) {
	        if (arr[i] < p) {
	            swap(arr, s2, i);
	            s2++;
	        }
	    }
	}

/**
arr		["Ian,Botham", "David,Gower", "Ian,Bell", "Ian,Chappell"]
sorted  ["Ian,Botham", "Ian,Bell", "Ian,Chappell", "David,Gower"]	n log(n) { n: number of words, k: number of letters (in first word only)
									curName >>						n k

src		dst		cur		prev	arr
---		---		---		----	---
0		0		Ian		""		["Ian,Botham", "Ian,Bell", "Ian,Chappell", "David,Gower"]
1		1		Ian		Ian
2		1		Ian		Ian
3		2		David	David	["Ian,Botham", "David,Gower", null, null]

*/
	
	public static void removeDuplicateFirstNames(String[] arr) {
		Arrays.sort(arr, new Comparator<String>() {
			
			public int compare(String s1, String s2) {
				String name1 = s1.substring(0, s1.indexOf(","));
				String name2 = s2.substring(0, s2.indexOf(","));
				return name1.compareTo(name2);
			}
			
		});
		
		String prev = "";
		int src, dst;
		for (src = 0, dst = 0; src < arr.length; src++) {
			String cur = arr[src].substring(0, arr[src].indexOf(",")); // first name
			if (!cur.equals(prev)) {
				arr[dst++] = arr[src];
				prev = cur;
			}
		}
		
		while (dst < arr.length) {
			arr[dst++] = null;
		}
	}

	public static void sortDutchFlag(int[] arr, int pIndx) {
		int pivot = arr[pIndx];
	    swap(arr, 0, pIndx);
	    
	    int p = 0, s2 = 1;
	    for (int i = 1; i < arr.length; i++) {
	        if (arr[i] <= pivot) {
	            // move elm to s1
	            swap(arr, i, s2);
	            s2++;
	            if (arr[s2-1] == pivot) {
	            	// move elm from s1 to p
	            	swap(arr, s2-1, p+1);
	            	p++;
	            }
	        }
	    }
	    
	    for (int lastS1 = s2-1; p >= 0; p--, lastS1--) {
            swap(arr, lastS1, p);
        }
	}
	
	private static void printArray(Object[] arr) {
		for (Object x : arr)
			System.out.print(x + " ");
		System.out.println();
	}

	private static void printArray(int[] arr) {
		for (int x : arr)
			System.out.print(x + " ");
		System.out.println();
	}
	    		
	public static void sortUpDownArray(int[] arr, int k) {
		    int n = arr.length;
		    int[] tmp = new int[n];
		    
		    // find regions
		    // int[] p = new int[k];
		    // int cur = -1;
		    
		    // copy first increment region into tmp
		    int first = 0, last = 1;
		    int sorted = -1;
		    for (int i = 0; i < n - 1; i++) {
		        if (arr[i] > arr[i + 1]) {
		            first = i + 1;
		            sorted = i;
		            break;
		        }
		    }
		    
		    // copy elements from tmp to array
//		    System.out.println("*** Copy first region");
//		    ArrayUtils.printArray(arr);
		    
		    boolean asc = false;
		    while (sorted < (n - 1)) {
		        // possible to avoid duplicate code by reversing desc area, and executing the first logic as for asc
		        if (asc) {
		            // find last elm of asc region
		            for (int i = sorted + 1; i < n; i++) {
		                // continue merging desc region
		                if (i == (n - 1) || arr[i] > arr[i + 1]) {
		                    last = i;
		                    break;
		                }
		            }
		            
		            int i, j, dst;
		            for (i = 0, j = sorted + 1, dst = 0; i < first && j <= last; dst++) {
		                if (arr[i] < arr[j]) {
		                    tmp[dst] = arr[i];
		                    i++;
		                }
		                else {
		                    tmp[dst] = arr[j];
		                    j++;
		                }
		            }
		            
		            // finish off first region
		            while (i < first) {
		                tmp[dst++] = arr[i++];
		            }
		            
		            // finish off second region
		            while (j <= last) {
		                tmp[dst++] = arr[j++];
		            }
		            
		            sorted = last;
//		            System.out.println("last="+last);
		            
		            // copy sorted elements from tmp to arr
		            System.arraycopy(tmp, 0, arr, 0, sorted + 1);
//		            System.out.println("*** Asscending");
//		            ArrayUtils.printArray(arr);
		        }

		/**
		f    l    s    i        j          arr                               tmp                    dst
		-    -    -    -        -          ---                               ---                    ---
		                                   0  1   2   3   4   5   6   7
		0    1    -1   0..2                57 131 493 294 221 339 418 452    
		3    1    2    0..2                57 131 493 294 221 339 418 452    57 131 493
		3    4    2    3..4                57 131 493 294 221 339 418 452    57 131 493
		3    4    2    0..3     4..3       57 131 493 294 221 339 418 452    57 131 493             0
		3    4    2    0        4          57 131 493 294 221 339 418 452    57 131 493             0
		3    4    2    1        4          57 131 493 294 221 339 418 452    57 131 493             1
		3    4    2    2        4          57 131 493 294 221 339 418 452    57 131 493             2
		3    4    2    2        3          57 131 493 294 221 339 418 452    57 131 221             3
		3    4    2    2        2          57 131 493 294 221 339 418 452    57 131 221 294         4
		3    4    4    2..3                57 131 493 294 221 339 418 452    57 131 221 294 493     5         // finish off first region
		3    4    4    2..3                57 131 221 294 493 339 418 452    57 131 221 294 493     5         // copy elemts back to arr

		*/
		        else {
		            // find last elm of desc region
		            for (int i = sorted + 1; i < n; i++) {
		                // continue merging desc region
		                if (i == (n - 1) || arr[i] < arr[i + 1]) {
		                    last = i;
		                    break;
		                }
		            }
		            
		            int i, j, dst;
		            for (i = 0, j = last, dst = 0; i < first && j >= first; dst++) {
		                if (arr[i] < arr[j]) {
		                    tmp[dst] = arr[i];
		                    i++;
		                }
		                else {
		                    tmp[dst] = arr[j];
		                    j--;
		                }
		            }
		            
		            // finish off first region
		            while (i < first) {
		                tmp[dst++] = arr[i++];
		            }
		            
		            // finish off second region
		            while (j >= first) {
		                tmp[dst++] = arr[j--];
		            }
		            
		            sorted = last;
		            
		            // copy sorted elements from tmp to arr
		            System.arraycopy(tmp, 0, arr, 0, sorted + 1);
//		            System.out.println("*** Descending");
//		            ArrayUtils.printArray(arr);
		        }
		        
		        // flip inc
		        asc = !asc;
		    }
		    
		    /**     0   1   2   3   4   5   6   7   8   9
		            +   +   +   -   -   +   +   +   -   -
		    arr    57 131 493 294 221 339 418 452 442 190
		    tmp    57 131 493 -   -   -  
		                    f
		                    s
		                    s
		    **/
		    
		    /***
		    private static void copy(int[] arr, int[] tmp, int sorted) {
		        // copy elements from tmp to array
		        for (int i = 0; i <= sorted; i++) {
		            arr[i] = tmp[i];
		        }
		    }
		    ***/
		    
		}

	public static void group (Student[] arr) {
	    int n = arr.length;
	    HashMap<Integer, ArrayList<Student>> h = new HashMap<Integer, ArrayList<Student>>();
	    for (Student s : arr) {
	        ArrayList<Student> list = h.get(s.getAge());
	        if (list == null) {
	            list = new ArrayList<Student>();
	            h.put(s.getAge(), list);
	        }
	        list.add(s);
	    }
	    
	    int indx = 0;
	    for (Integer age : h.keySet()) {
	        for (Student s : h.get(age)) {
	            arr[indx++] = s;
	        }
	    }
	}
	
	public static class Student {

		private String id;
	    private int age;
	    
	    public Student(String id, int age) {
	    	this.id = id;
	    	this.age = age;
	    }
	    
	    @Override
	    public int hashCode() {
	    	return Objects.hash(age);
	    }
	    
	    public int getAge() {
	    	return age;
	    }
	    
	    @Override
	    public String toString() {
	    	return "(" + id + "," + Integer.toString(age) + ")";
	    }
	    /**
{ com.amazon.algorithms.Sorting$Student@28,com.amazon.algorithms.Sorting$Student@1f,
com.amazon.algorithms.Sorting$Student@23,com.amazon.algorithms.Sorting$Student@26,
com.amazon.algorithms.Sorting$Student@20,com.amazon.algorithms.Sorting$Student@21,
com.amazon.algorithms.Sorting$Student@23,com.amazon.algorithms.Sorting$Student@26,com.amazon.algorithms.Sorting$Student@20,}

{ com.amazon.algorithms.Sorting$Student@1f,com.amazon.algorithms.Sorting$Student@20,
com.amazon.algorithms.Sorting$Student@20,com.amazon.algorithms.Sorting$Student@21,
com.amazon.algorithms.Sorting$Student@23,com.amazon.algorithms.Sorting$Student@23,
com.amazon.algorithms.Sorting$Student@26,com.amazon.algorithms.Sorting$Student@26,com.amazon.algorithms.Sorting$Student@28,}

*/
	}
	
	/**
	 0 1 2
A : [1 4 5]
B : [2 3]	 
	 */
	public static double median(int[] a, int[] b) {
	    int n = a.length;
	    int m = b.length;
	    // median has half elements to the right or left
	    int half = (n + m) / 2;
	    // first and last elements under binary search in a and b respectively
	    int first1 = 0;
	    int last1 = n - 1; 
	    int first2 = 0; 
	    int last2 = m - 1;
	    int mid1 = 0;
	    int mid2 = 0;
	    while (first1 <= last1 && first2 <= last2) {
	        mid1 = (first1 + last1) / 2;
	        mid2 = (first2 + last2) / 2;
	        // number of elements in A to the left of mid1
	        int countLeftA = mid1 - first1;
	        // number of elements in B to the left mid2
	        int countLeftB = mid2 - first2;
	        System.out.println("first1=" + first1 + " last1="+last1 + " a=" + a[mid1] + " leftA=" + countLeftA + 
	        		" first2="+first2 + " last2=" + last2 + " b=" + b[mid2] + " leftB=" + countLeftB);
	        if (a[mid1] > b[mid2]) {
	            int countLeft = countLeftA + countLeftB + 1;
	            System.out.println("left of " + a[mid1] + " =" + countLeft);
//	            if (countLeft == half) {
//	                return a[mid1];
//	            }
	            if (countLeft > half) {
	                last1 = mid1 - 1;
	            }
	            else {
	                first1 = mid1 + 1;
	                first2 = mid2 + 1;
	            }
	        }
	        // b[mid2] > a[mid1]
	        else {
	            int countLeft = countLeftB + countLeftA + 1;
	            System.out.println("left of " + b[mid2] + " =" + countLeft);
//	            if (countLeft == half) {
//	                return b[mid2];
//	            }
	            if (countLeft > half) {
	                last2 = mid2 - 1;
	            }
	            else {
	                first2 = mid2 + 1;
	                first1 = mid1 + 1;
	            }
	            
	        }
	    }
	    
	    int leftA = mid1 - first1;
	    int leftB = mid2 - first2;
	    
	    // return larger
	    boolean isOdd = (n + m) % 2 == 1;
	    if (isOdd) {
	    	if ((leftA + leftB + 1) == half) {
	    		if (a[mid1] > b[mid2]) {
	    			return a[mid1];
	    		}
	    		else {
	    			return b[mid2];
	    		}
	    	}
	    	// return smaller
	    	else {
	    		if (a[mid1] < b[mid2]) {
	    			return a[mid1];
	    		}
	    		else {
	    			return b[mid2];
	    		}
	    	}
	    }
	    else {
	    	return (a[mid1] + a[mid2]) / 2;
	    }
	}

	public static class Pair {
	    int first, second;
	    
	    public Pair(int first, int second) {
	    		this.first = first;
	    		this.second = second;
	    }
    }

	public static class Point implements Comparable<Point> {
	    int x;
	    boolean isStart;
	    
	    public Point(int x, boolean isStart) {
	        this.x = x;
	        this.isStart = isStart;
	    }
	    
	    @Override
	    public int compareTo(Point o) {
	        return Integer.valueOf(x).compareTo(o.x);
	    }
	}
	
	/** Elements of Programming Interviews: Problem 13.5: Render a Calendar */
	public static int maxConcurrent(Pair[] arr) {
	    Point[] seq = new Point[arr.length * 2];
	    int indx = 0;
	    for (Pair p : arr) {
	        seq[indx++] = new Point(p.first, true);
	        seq[indx++] = new Point(p.second, false);
	    }
	    Arrays.sort(seq);
	    int cur = 0;
	    int max = Integer.MIN_VALUE;
	    for (Point p : seq) {
	        if (p.isStart) {
	            cur++;
	            max = java.lang.Math.max(cur, max);
	        }
	        else {
	            cur--;
	        }
	    }
	    return max;
	}
	
	public static class Interval implements Comparable<Interval> {
	    private int start, end;
	    
	    public Interval(int start, int end) {
	    		this.start = start;
	    		this.end = end;
	    }
	    
	    public int compareTo(Interval o) {
	        return Integer.valueOf(start).compareTo(Integer.valueOf(o.start));
	    }
	    
	    public boolean isOverlapsNextInterval(Interval x) {
	        return x.start <= this.end;
	    }
	    
	    public void setEnd(int end) {
	        this.end = end;
	    }
	    
	    @Override
	    public String toString() {
		    	// TODO Auto-generated method stub
		    	return "[" + this.start + "," + this.end + "]";
	    }
	}

	public static List<Interval> union(Interval[] arr) {
	    // sort intervals by start time
	    Arrays.sort(arr);
	    List<Interval> res = new ArrayList<Interval>();
	    res.add(arr[0]);
	    for (int i = 1; i < arr.length; i++) {
	        // number of union intervals so far
	        int n = res.size();
	        Interval first = res.get(n - 1);
	        Interval second = arr[i];
	        if (first.isOverlapsNextInterval(second)) {
	            // merge by extending end time of earlier interval
	            first.setEnd(java.lang.Math.max(first.end, second.end));
	        } else {
	            // add new disjoint interval
	            res.add(second);
	        }
	    }    
	    return res;
	}

	public static boolean canTakeTeamPhoto(int[] T1, int[] T2) {
	    // calcualte total heights to find back and front rows
	    int h1 = 0;
	    for (int h : T1) {
	        h1 += h;
	    }
	    int h2 = 0;
	    for (int h : T2) {
	        h2 += h;
	    }
	    int[] back = T1;
	    int[] front  = T2;
	    if (h2 > h1) {
	        front = T1;
	        back = T2;
	    }
	    // sort both rows by height
	    Arrays.sort(T1);
	    Arrays.sort(T2);
	    // scan and compare heights of matched pairs of players 
	    int n = T1.length;
	    if (T2.length != n) {
	        throw new IllegalArgumentException("Array lengths do not match: " + n + "," + T2.length);
	    }
	    for (int i = 0; i < n; i++) {
	        if (back[i] <= front[i]) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static int klargest(int[] arr, int k) {
	    int n = arr.length;
	    return klargest(arr, n, k, 0, n - 1);
	}

	private static int klargest(int[] arr, int n, int k, int first, int last) {
		ArrayUtils.printArray(arr);
	    int pIndx = findPivot(arr, first, last);
	    int pivot = arr[pIndx];
	    swap(arr, first, pIndx); 
	    // divide the array into smaller and larger regions
	    int split = first + 1;
	    for (int i = split; i <= last; i++) {
	        if (arr[i] < pivot) {
	            swap(arr, i, split++);
	        }
	    }
	    pIndx = split - 1;
	    swap(arr, first, pIndx);
	    int target = n - k;
//	    int cur = n - pIndx;
	    ArrayUtils.printArray(arr);
	    if (pIndx == target) {
	        return arr[pIndx];
	    }
	    else if (target > pIndx) {
	    		return klargest(arr, n, k, split, last);
	    }
	    else {
	    		return klargest(arr, n, k, first, split - 1);
	    }
	}

	private static int findPivot(int[] arr, int first, int last) {
	    return first;
	}

	
}



















































