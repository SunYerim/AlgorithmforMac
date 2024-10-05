import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {0, 0, 1, -1, 1, 1, -1, -1, 0};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1, 0};
    static int[][] chess;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        chess = new int[8][8];

        for (int i = 0; i < 8; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 8; j++) {
                chess[i][j] = tmp.charAt(j);
            }
        }
        int result = bfs(7, 0);

        System.out.println(result);

    }

    private static int bfs(int wookjaeX, int wookjaeY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{wookjaeX, wookjaeY});
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 1초마다 움직임 처리
            boolean[][] visited = new boolean[8][8]; // 매번 새로운 visited 배열 초기화

            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int currX = cur[0];
                int currY = cur[1];
                // 벽이면
                if (chess[currX][currY] == '#') {
                    continue;
                }
                if (currY == 7 && currX == 0)
                    return 1;
                for (int j = 0; j < 9; j++) {
                    int tmpX = cur[0] + dx[j];
                    int tmpY = cur[1] + dy[j];
                    if (tmpY >= 0 && tmpX >= 0 && tmpX < 8 && tmpY < 8) {
                        if (chess[tmpX][tmpY] == '.' && !visited[tmpX][tmpY]) {
                            queue.offer(new int[]{tmpX, tmpY});
                            visited[tmpX][tmpY] = true;
                        }
                    }
                }
            }
            wallDrop();
        }
        return 0;
    }

    // 벽을 내림
    private static void wallDrop() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                chess[i+1][j] = chess[i][j];
            }
        }
        for (int i = 0; i < 8; i++) {
            chess[0][i] = '.';
        }
    }

}
