import java.util.*;

class Solution {
    static int[] board;
    static int answer;
    public int solution(int n) {
        answer = 0;
        board = new int[n];
        backtracking(0, n);
        return answer;
    }
    
    public static void backtracking(int depth, int n) {
        // 기저
        if (depth == n) {
            answer++;
            // // debugging
            // for (int i = 0; i < n; i++) {
            //     System.out.print(board[i] + " ");
            // }
            // System.out.println();
            return;
        }
        // 유도
        // 다음 행
        for (int i = 0; i < n; i++) {
            board[depth] = i;
            // 가능하면
            if (attack(depth)) {
                backtracking(depth + 1, n);
            }
        }
    }
    
    private static boolean attack(int i) {
        for (int j = 0; j < i; j++) {
            // 놓을 수 있는지 확인
            if (board[i] == board[j]) return false; // 가로 세로
            if (Math.abs(i - j) == Math.abs(board[i] - board[j])) return false; // 대각선
        }
        return true;
    }
}