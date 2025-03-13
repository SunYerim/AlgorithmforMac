import java.util.*;

class Solution {
    static char[][] copyBoard;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        copyBoard = new char[m][n];
        
        // 저장
        for (int i = 0; i < copyBoard.length; i++) {
            copyBoard[i] = board[i].toCharArray();
        }
        
        while (true) {
            // 탈출 flag
            boolean flag = true;
            boolean[][] visited = new boolean[m][n];
            
            // 지울 수 있는가 탐색
            for (int i = 0; i < copyBoard.length - 1; i++) {
                for (int j = 0; j < copyBoard[0].length - 1;j++) {
                    // 블록 4개 체크
                    char now = copyBoard[i][j];
                    char up = copyBoard[i+1][j];
                    char right = copyBoard[i][j + 1];
                    char three = copyBoard[i+1][j+1];
                    
                    // 블록이 없는 자리면 continue
                    if (now == 'X' || up == 'X' || right == 'X' || three == 'X') continue;
                    
                    // 4개 전부 같으면
                    if (now == up && up == right && right == three) {
                        // 지웁니다.
                        if (!visited[i][j]) {
                            visited[i][j] = true;
                            answer++;
                        }
                        if (!visited[i+1][j]) {
                            visited[i+1][j] = true;
                            answer++;
                        }
                        if (!visited[i][j+1]) {
                            visited[i][j+1] = true;
                            answer++;
                        }
                        if (!visited[i+1][j+1]) {
                            visited[i+1][j+1] = true;
                            answer++;
                        }
                        flag = false;
                    }
                }
            }
            
            // 더이상 진행 안 되면 break
            if (flag) break;
            
            // 블록 내리기 (아래에서 위로 탐색)
            for (int j = 0; j < n; j++) { // 각 열별로 탐색
                for (int i = m - 1; i >= 0; i--) { 
                    if (visited[i][j]) { // 지운 블록이면
                        boolean isPossible = true;
                        for (int k = i - 1; k >= 0; k--) { 
                            // 블록이 존재하고 지워지지 않은 블록이면
                            if (copyBoard[k][j] != 'X' && !visited[k][j]) {
                                // 블록을 내림
                                copyBoard[i][j] = copyBoard[k][j];
                                copyBoard[k][j] = 'X';
                                visited[k][j] = true;
                                isPossible = false;
                                break;
                            }
                        }
                        // 내릴 수 있는 블록이 없으면 현 위치를 빈칸으로 변경
                        if (isPossible) {
                            copyBoard[i][j] = 'X';
                        }
                    }
                }
            }

            // for (int i = 0; i < copyBoard.length - 1; i++) {
            //     for (int j = 0; j < copyBoard[0].length; j++) {
            //         // 지운 블록이면
            //         if (visited[i][j]) {
            //             // 내릴 수 있는 블록이 있는지 확인
            //             boolean isPossible = true;
            //             for (int k = i + 1; k < copyBoard.length; k++) {
            //                 // 블록이 존재하고 지워지지 않은 블록이면
            //                 if (copyBoard[k][j] != 'X' && !visited[k][j]) {
            //                     // 내림
            //                     copyBoard[i][j] = copyBoard[k][j];
            //                     copyBoard[k][j] = 'X';
            //                     visited[k][j] = true;
            //                     isPossible = false;
            //                     break;
            //                 }
            //             }
            //             // 내릴 수 있는 블록이 없으면
            //             if (isPossible) {
            //                 // 현 위치 블록 지움
            //                 copyBoard[i][j] = 'X';
            //             }
            //         }
            //     }
            // }
        }
        return answer;
    }
}