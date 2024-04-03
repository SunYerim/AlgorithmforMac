import java.util.*;
class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;
    public int solution(int[][] maps) {
        int answer = 0;
        int n = maps.length;
        int m = maps[0].length;

        visited = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        // bfs
        queue.add(new Node(0, 0)); // 출발
        // 큐가 비기 전까지
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            int currX = curr.x;
            int currY = curr.y;
            // 기저
            if (currX == n-1 && currY == m-1) {
                return maps[n-1][m-1];
            }
            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && maps[nx][ny] != 0 && !visited[nx][ny]) {
                    maps[nx][ny] = maps[currX][currY] + 1;
                    queue.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            
        }
        // 도달 안 되는 경우
        
        return -1;
    }
    
    static class Node{
        private int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
}