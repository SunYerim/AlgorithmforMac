import java.util.*;

class Solution
{
    static int answer = 0;
    public int solution(int[][] board)
    {
        // 한 줄인 경우
        int a = board.length; // y
        int b = board[0].length; // x
        
        for (int i = 0; i < a; i++) {
            if (board[i][0] == 1) answer = 1;
        }
        
        for (int j = 0; j < b; j++) {
            if (board[0][j] == 1) answer = 1;
        }
        
        // 나머지
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(Math.min(board[i-1][j], board[i][j-1]), board[i-1][j-1]) + 1;
                }
                answer = Math.max(board[i][j], answer);
            }
        }
        return answer * answer;
    }
}