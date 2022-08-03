/**
 * Problem 378. Kth Smallest Element in a Sorted Matrix 
 * (Daily Problem for 02AUG2022)
 * 
 * Given an n x n matrix where each of the rows and columns is sorted in 
 * ascending order, return the kth smallest element in the matrix.
 *
 * Note that it is the kth smallest element in the sorted order, not the kth 
 * distinct element.
 *
 * You must find a solution with a memory complexity better than O(n2).
 * 
 * Example:
 * Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 * Output: 13
 * Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and 
 * the 8th smallest number is 13
 */
public class KthSmallestElement
{
    // binary search solution
    // time complexity  : O(n log m), where m = max-min
    // space complexity : O(1)
    public int kthSmallest( int[][] matrix, int k )
    {
        int n = matrix.length;

        // find the low and high values in the matrix
        int low  = matrix[0][0];
        int high = matrix[n-1][n-1];
        
        // find the kth smallest element by performing a binary search in the
        // matrix (using the value range [min,max] rather than the indices of
        // the matrix)
        while ( low < high )
        {
            int mid = low + ((high - low) / 2);

            // determine the number of elements in the matrix that are less
            // than or equal to the mid value
            int count = numNonGreaterElements( matrix, mid );

            // use previously determined 'rank' of the mid value to adjust the
            // search space accordingly 
            if ( count < k )
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }

        // once the low value crosses the high value, its value must be the 
        // kth smallest element 
        return low;
    }

    private int numNonGreaterElements( int[][] matrix, int val )
    {
        int count = 0;

        // start search 
        int n = matrix.length;
        
        // start counting from the lower left cell in the matrix
        int row = n - 1;
        int col = 0;

        // keep counting until the top right cell is reached
        while ( (row >= 0) && (col < n) )
        {
            // matrix cell is greater than target value
            // move up one row and continue count
            if ( matrix[row][col] > val )
            {
                row--;
            }

            // target value is greater than or equal to matrix cell, meaning
            // that all the values in the current column (up to and including
            // the current row) are less than or equal to the target value
            // move right one column and continue count
            else
            {
                count += (row+1);
                col++;
            }
        }

        return count;
    }

    // TODO: add solution with O(n) time complexity (e.g. solution that uses a heap)
 
    public static void main( String[] argv )
    {
        assert (new KthSmallestElement()
                .kthSmallest( new int[][] {{1,5,9},{10,11,13},{12,13,15}}, 8 ) == 13);
        assert (new KthSmallestElement()
                .kthSmallest( new int[][] {{-5}}, 1 ) == -5);
    }
}