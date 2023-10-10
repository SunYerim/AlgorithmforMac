class Solution {
    // 상하좌우 배열
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;

    public int[] solution(int m, int n, int[][] picture) {
        // 방문 여부 저장
        boolean[][] visited = new boolean[m][n];

        // 순회
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    int size = dfs(m, n, picture, visited, i, j, picture[i][j]);
                    maxSizeOfOneArea = Math.max(size, maxSizeOfOneArea);
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    private int dfs(int m, int n, int[][]picture, boolean[][] visited, int x, int y, int color) {
        int size = 1;

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (!visited[nx][ny] && picture[nx][ny] == color) {
                    size += dfs(m, n, picture, visited, nx, ny, color);
                }
            }
        }
        return size;
    }
}