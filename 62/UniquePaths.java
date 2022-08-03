/**
 * Problem 62. Unique Paths (Daily Problem for 01AUG2022)
 * 
 * There is a robot on an m x n grid. The robot is initially located at the 
 * top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-
 * right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down 
 * or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths 
 * that the robot can take to reach the bottom-right corner.
 * 
 * Example:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach 
 * the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */
public class UniquePaths
{
    // dynamic programming solution
    // time complexity : O(mn)
    // space complexity : O(mn)
    public int uniquePaths( int m, int n )
    {
        // number of paths to reach each cell 
        int[][] numPaths = new int[m][n];

        // only one way to reach each cell in the left-most column
        for ( int i = 0; i < m; i++ )
        {
            numPaths[i][0] = 1;
        }

        // only one way to reach each cell in the top-most row
        for ( int i = 0; i < n; i++ )
        {
            numPaths[0][i] = 1;
        }

        // for each of the other cells, the number of unique paths can be 
        // determined by adding the number of unique paths for the cell just
        // to the left and the number of unique paths for the cell just above
        // (since these are the only ways that a cell can be reached)
        for ( int i = 1; i < m; i++ )
        {
            for ( int j = 1; j < n; j++ )
            {
                numPaths[i][j] = numPaths[i-1][j] + numPaths[i][j-1];
            }
        }
        
        // return result for bottom-right corner cell
        return numPaths[m-1][n-1];
    }

    // TODO: add solution that uses a 1D array rather than a 2D array

    public static void main( String[] argv )
    {
        assert (new UniquePaths().uniquePaths( 3, 7 ) == 28);
        assert (new UniquePaths().uniquePaths( 3, 2 ) == 3);
    }
}