import java.util.*;

class Solution {
    static int answer;
    static int[][] prefix;
    public int solution(int[][] board, int[][] skill) {
        answer = 0;
        int n = board.length;
        int m = board[0].length;
        prefix = new int[n + 1][m + 1];
        for (int[] curr : skill) {
            int type = curr[0];
            int r1 = curr[1];
            int c1 = curr[2];
            int r2 = curr[3];
            int c2 = curr[4];
            int degree = curr[5];
            
            if (type == 1) degree = -degree;
            
            prefix[r1][c1] += degree;
            prefix[r1][c2 + 1] -= degree;
            prefix[r2+1][c1] -= degree;
            prefix[r2 + 1][c2 + 1] += degree;

        }
        
        // 가로
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] += prefix[i][j-1];
            }
        }
        
        
        
        // 세로
        for (int j = 0; j < m; j++) {
            for (int i = 1; i <= n; i++) {
                prefix[i][j] += prefix[i-1][j];
            }
        }
        
        // return
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + prefix[i][j] > 0) answer++;
            }
        }
        return answer;
    }

}