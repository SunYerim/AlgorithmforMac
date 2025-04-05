import java.util.*;

class Solution {
    static char[][] board;
    static int answer;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    public int solution(String[] storage, String[] requests) {
        answer = 0;
        int n = storage.length;
        int m = storage[0].length();
        board = new char[n][m];
        // 옮기기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++){ 
                board[i][j] = storage[i].charAt(j);
            }
        }
        
        // 지게차 -> 하나
        // 크레인 -> Queue활용해서 연쇄
        
        for (int i = 0; i < requests.length; i++) {
            if (requests[i].length() == 1) jigecha(requests[i].charAt(0), n, m);
            else crane(requests[i].charAt(0), n, m);
            
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] != '.') answer++;
            }
        }
        
        return answer;
    }
    
    public static void jigecha(char c, int n, int m) {
        boolean[][] isOutSide = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        
        // 테두리 탐색
        for (int i = 0; i < n; i++) {
            if (board[i][0] == '.') {
                isOutSide[i][0] = true;
                queue.offer(new int[]{i, 0});
            }
            if (board[i][m-1] == '.') {
                isOutSide[i][m-1] = true;
                queue.offer(new int[]{i, m-1});
            }
        }
        
        for (int j = 0; j < m; j++) {
            if (board[0][j] == '.') {
                isOutSide[0][j] = true;
                queue.offer(new int[]{0, j});
            }
            
            if (board[n-1][j] == '.') {
                isOutSide[n-1][j] = true;
                queue.offer(new int[]{n-1, j});
            }
        }
        
        // bfs
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];
            
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (!isOutSide[ny][nx] && board[ny][nx] == '.') {
                    isOutSide[ny][nx] = true;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
        
        // 외부 공기 맞닿아있는 c 제거
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == c) {
                    for (int d = 0; d < 4; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                        if (isOutSide[ny][nx]) {
                            list.add(new int[]{i, j});
                            break;
                        }
                    }
                }
            }
        }
        
        for (int[] pos : list) {
            board[pos[0]][pos[1]] = '.';
        }
        
        for (int i = 0; i < n; i++) {
            if (board[i][0] == c) board[i][0] = '.';
            if (board[i][m-1] == c) board[i][m-1] = '.';
        }
        
        for (int j = 0; j < m; j++) {
            if (board[0][j] == c) board[0][j] = '.';
            if (board[n-1][j] == c) board[n-1][j] = '.';
        }
    }

    
    
    public static void crane(char c, int n, int m) {     
        // 그냥 똑같은 글자 다 뽑음
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) { 
                if (board[i][j] == c)
                    board[i][j] = '.';
            }
        }
    }
}