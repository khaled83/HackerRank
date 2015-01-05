package contest.week10;
import java.util.*;

public class Towers {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		int K = sc.nextInt();
		List<Long> heights = new ArrayList<Long>();
		for(int i=1; i<=K; i++)
			heights.add( sc.nextLong() );
			
		Permutations<Long> perms = new Towers().new Permutations<Long>();
		Collection<List<Long>> allPerms = perms.permute(heights); //perms.permute2(heights, N);  
		Collection<List<Long>> result = new ArrayList<List<Long>>();
		
		
		for(List<Long> perm : allPerms)
			if(height(perm) == N)
				result.add(perm);
				
		System.out.println(result.size() * 2);
		for(List<Long> perm : result)
		{
				System.out.print("[");
				for(Long value : perm)
					System.out.print(value + ",");
				System.out.println("]");
		}
		
		System.out.println();
		System.out.println("~ ~ ~ ~");
		for(List<Long> perm : allPerms)
		{
				System.out.print("[");
				for(Long value : perm)
					System.out.print(value + ",");
				System.out.println("]");
		}
		
		
//		Permutations<Integer> obj = new Towers().new Permutations<Integer>();
//		Collection<Integer> input = new ArrayList<Integer>();
//		input.add(1);
//		input.add(2);
//		input.add(3);
//
//		Collection<List<Integer>> output = obj.permute(input);
//		System.out.println(output.size());
//		for(List<Integer> perm : output)
//		{
//			System.out.print("[");
//			for(Integer value : perm)
//				System.out.print(value + ",");
//			System.out.println("]");
//		}
//		
//		Collection<List<Integer>> output2 = obj.permute2(input);
//		System.out.println(output.size());
//		for(List<Integer> perm : output)
//		{
//			System.out.print("[");
//			for(Integer value : perm)
//				System.out.print(value + ",");
//			System.out.println("]");
//		}
		
//		int k = 0;
//		Set<List<Integer>> pnr = null;
//		for (int i = 0; i <= input.size(); i++) {
//			pnr = new HashSet<List<Integer>>();
//			for (List<Integer> integers : output) {
//				pnr.add(integers.subList(i, integers.size()));
//			}
//			k = input.size() - i;
//			System.out.println("P(" + input.size() + "," + k + ") :"
//					+ "Count (" + pnr.size() + ") :- " + pnr);
//		}
	}
	
	private static long height(List<Long> perm)
	{
		long result = 0;
		for(Long h : perm)
			result+= h;
		return result;
	}
	
	private class Permutations<T> {
		
		public Collection<List<T>> permute2(Collection<T> input, long maxHeight)
		{
			Collection<List<T>> output = new ArrayList<List<T>>();
			long curHeight = 0;
			
			if(input.isEmpty())
			{
				output.add(new ArrayList<T>());
				return output;
			}
			
			// [1, 2, 3]
			List<T> inList = new ArrayList<T>(input);
			T h = inList.get(0);
			List<T> rest = inList.subList(1, inList.size());
			for(List<T> perm : permute2(rest, maxHeight))
			{
				for(int i=0; i<inList.size() && curHeight <= maxHeight; i++)
				{
					List<T> newPerm = new ArrayList<T>(perm);
					newPerm.add(i, h);
					curHeight+= height((List<Long>)newPerm);
					output.add(newPerm);
				}
			}
			
			return output;
		}
		
		public Collection<List<T>> permute(Collection<T> input) {
			
			Collection<List<T>> output = new ArrayList<List<T>>();
			
			if (input.isEmpty()) {
				output.add(new ArrayList<T>());
				return output;
			}
			
			// [1, 2, 3]
			List<T> list = new ArrayList<T>(input);
			T head = list.get(0); // 1
			List<T> rest = list.subList(1, list.size()); //[2, 3]
			for (List<T> permutations : permute(rest)) { // perm[2, 3]
				List<List<T>> subLists = new ArrayList<List<T>>();
				for (int i = 0; i <= permutations.size(); i++) {
					List<T> subList = new ArrayList<T>();
					subList.addAll(permutations);
					subList.add(i, head);
					subLists.add(subList);
				}
				output.addAll(subLists);
			}
			return output;
		}
	}

}
