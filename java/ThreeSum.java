
// Java program to find a triplet
//Approach: By Sorting the array the efficiency of the algorithm can be improved. This efficient approach uses the two-pointer technique. Traverse the array and fix the first element of the triplet. Now use the Two Pointers algorithm to find if there is a pair whose sum is equal to x – array[i]. Two pointers algorithm take linear time so it is better than a nested loop.
//Algorithm : 
//Sort the given array.
//Loop over the array and fix the first element of the possible triplet, arr[i].
//Then fix two pointers, one at i + 1 and the other at n – 1. And look at the sum, 
//If the sum is smaller than the required sum, increment the first pointer.
//Else, If the sum is bigger, Decrease the end pointer to reduce the sum.
//Else, if the sum of elements at two-pointer is equal to given sum then print the triplet and break.
//Complexity Analysis: 
//Time complexity: O(N^2). 
//There are only two nested loops traversing the array, so time complexity is O(n^2). Two pointers algorithm takes O(n) time and the first element can be fixed using another nested traversal.
//Space Complexity: O(1). 
//As no extra space is required.
class ThreeSum {
 
    // returns true if there is triplet with sum equal
    // to 'sum' present in A[]. Also, prints the triplet
    boolean find3Numbers(int A[], int arr_size, int sum)
    {
        int l, r;
 
        /* Sort the elements */
        quickSort(A, 0, arr_size - 1);
 
        /* Now fix the first element one by one and find the
           other two elements */
        for (int i = 0; i < arr_size - 2; i++) {
 
            // To find the other two elements, start two index variables
            // from two corners of the array and move them toward each
            // other
            l = i + 1; // index of the first element in the remaining elements
            r = arr_size - 1; // index of the last element
            while (l < r) {
                if (A[i] + A[l] + A[r] == sum) {
                    System.out.print("Triplet is " + A[i] + ", " + A[l] + ", " + A[r]);
                    return true;
                }
                else if (A[i] + A[l] + A[r] < sum)
                    l++;
 
                else // A[i] + A[l] + A[r] > sum
                    r--;
            }
        }
 
        // If we reach here, then no triplet was found
        return false;
    }
 
    int partition(int A[], int si, int ei)
    {
        int x = A[ei];
        int i = (si - 1);
        int j;
 
        for (j = si; j <= ei - 1; j++) {
            if (A[j] <= x) {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[ei];
        A[ei] = temp;
        return (i + 1);
    }
 
    /* Implementation of Quick Sort
    A[] --> Array to be sorted
    si  --> Starting index
    ei  --> Ending index
     */
    void quickSort(int A[], int si, int ei)
    {
        int pi;
 
        /* Partitioning index */
        if (si < ei) {
            pi = partition(A, si, ei);
            quickSort(A, si, pi - 1);
            quickSort(A, pi + 1, ei);
        }
    }
 
    // Driver program to test above functions
    public static void main(String[] args)
    {
        ThreeSum triplet = new ThreeSum();
        int A[] = { 1, 4, 45, 6, 10, 8 };
        int sum = 22;
        int arr_size = A.length;
 
        triplet.find3Numbers(A, arr_size, sum);
    }
}
