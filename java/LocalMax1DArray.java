

public class LocalMax1DArray {
 /*
    -infinite 1  2  3   1 -infinite
      mid-1 mid mid+1 can be increasing, decreasing, or a peak
     binary search
     time O(logn)
     must a peak
*/
	public static int findPeakElementIndex(int[] nums) {
		// only one
		if (nums.length == 1) return 0;
		int start = 0, end = nums.length - 1;
    
		// exit index: stat end 
		while (start + 1 < end) {
			int mid = (start + end) / 2;
        
			if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) 
				return mid;
			// / peak right
			else if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1] )
				start = mid;
			// peak left
			else 
				end = mid;
		}
    
		if (nums[start] > nums[end]) return start;
		if (nums[end] > nums[start]) return end;
    
		return -1;
	}
	
    public static void main(String[] args) {
    	System.out.println("test");
    	// 2 is expected
    	int[] testCase = {1,2,3,1};
    	int result = findPeakElementIndex(testCase);
    	System.out.println(result);
    }
}
