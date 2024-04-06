import java.util.*;

public class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        int n = board.length;
        String tmp = board[h][w];
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + h;
            int ny = dy[i] + w;
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (board[nx][ny].equals(tmp)) {
                    answer++;
                }
            }
        }
        return answer;
    }
}