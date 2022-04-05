// Java program to find minimum time
// to finish all jobs with given
// number of assignees
//The idea is to use Binary Search. Think if we have a function (say isPossible()) that tells 
//us if it’s possible to finish all jobs within a given time and number of available assignees. 
//We can solve this problem by doing a binary search for the answer. If the middle point of binary 
//search is not possible, then search in second half, else search in first half. Lower bound for Binary
//Search for minimum time can be set as 0. The upper bound can be obtained by adding all given job times. 
//Now how to implement isPossible()? This function can be implemented using Greedy Approach. Since we want 
//	to know if it is possible to finish all jobs within a given time, we traverse through all jobs and 
//		keep assigning jobs to current assignee one by one while a job can be assigned within the given time
//limit. When time taken by current assignee exceeds the given time, create a new assignee and start assigning 
//jobs to it. If the number of assignees becomes more than k, then return false, else return true.

class JobAssign
{
	// Utility function to get
	// maximum element in job[0..n-1]
	static int getMax(int arr[], int n)
	{
	int result = arr[0];
	for (int i=1; i<n; i++)
		if (arr[i] > result)
			result = arr[i];
		return result;
	}
	
	// Returns true if it is possible to finish jobs[]
	// within given time 'time'
	static boolean isPossible(int time, int K,
									int job[], int n)
	{
		// cnt is count of current
		// assignees required for jobs
		int cnt = 1;
		
		// time assigned to current assignee
		int curr_time = 0;
	
		for (int i = 0; i < n;)
		{
			// If time assigned to current assignee
			// exceeds max, increment count of assignees.
			if (curr_time + job[i] > time) {
				curr_time = 0;
				cnt++;
			}
			
			// Else add time of job to current
			// time and move to next job.
			else
			{
				curr_time += job[i];
				i++;
			}
		}
	
		// Returns true if count
		// is smaller than k
		return (cnt <= K);
	}
	
	// Returns minimum time required to
	// finish given array of jobs
	// k --> number of assignees
	// T --> Time required by every assignee to finish 1 unit
	// m --> Number of jobs
	static int findMinTime(int K, int T, int job[], int n)
	{
		// Set start and end for binary search
		// end provides an upper limit on time
		int end = 0, start = 0;
		for (int i = 0; i < n; ++i)
			end += job[i];
			
		// Initialize answer
		int ans = end;
	
		// Find the job that takes maximum time
		int job_max = getMax(job, n);
	
		// Do binary search for
		// minimum feasible time
		while (start <= end)
		{
			int mid = (start + end) / 2;
	
			// If it is possible to finish jobs in mid time
			if (mid >= job_max && isPossible(mid, K, job, n))
			{
				// Update answer
				ans = Math.min(ans, mid);
				
				end = mid - 1;
			}

			else
				start = mid + 1;
		}
	
		return (ans * T);
	}
	
	// Driver program
	public static void main(String arg[])
	{
		int job[] = {10, 7, 8, 12, 6, 8};
		int n = job.length;
		int k = 4, T = 5;
		System.out.println(findMinTime(k, T, job, n));
	}
}

// This code is contributed by Anant Agarwal.

