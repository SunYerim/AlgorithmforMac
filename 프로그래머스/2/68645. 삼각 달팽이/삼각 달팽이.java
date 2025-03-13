import java.util.*;
// 방향별 (총 3방향)
class Solution {
    static int[][] board;
    static int[] answer;
    public int[] solution(int n) {
        answer = new int[n*(n+1) / 2];
        board = new int[n][n];
        
        int x = 0;
        int y = -1;
        int number = 1;
        
        for (int j = 0; j < n; j++) {
            for (int i = j; i < n; i++) {
                // 대각선 아래
                if (j % 3 == 0) {
                    y++;
                }
                    
                // 가로
                else if (j % 3 == 1) {
                    x++;
                }
                
                // 대각선 위
                else if (j % 3 == 2) {
                    y--;
                    x--;
                }
                
                board[y][x] = number++;
            }
        }
        
        // 출력 - 디버깅
        // for (int j = 0; j < n; j++) {
        //     for (int i = 0; i < n; i++) {
        //         System.out.print(board[j][i] + " ");
        //     }
        //     System.out.println();
        // }
        
        // 넣기
        int idx = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (board[j][i] == 0) break;
                answer[idx++] = board[j][i];
            }
        }
        
        
        return answer;
    }
}