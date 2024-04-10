

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 말이 되고싶은 원숭이
    static int K, W, H;
    static int[][] board;
    static int[] horsex = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] horsey = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] monkeyx = {0, 1, 0, -1};
    static int[] monkeyy = {1, 0, -1, 0};
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H][W];
        visited = new boolean[H][W][K+1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = bfs(0, 0);
        if (result == -1)
            System.out.println("-1");
        else
            System.out.println(result);

    }

    // 여기서 관건이, 말의 이동방식을 '언제' 써야 최단거리가 될 수 있을지 알 수 없다는 것.
    // 동일한 칸에 도달하더라도 말의 움직임을 몇 번 사용하고 도달한 칸인지를 함께 저장해야한다.
    // visited -> 3차원 배열
    public static int bfs(int startX, int startY) {
        int minMoves = -1;

        Queue<Monkey> q = new LinkedList<>();
        q.offer(new Monkey(startX, startY ,0, 0));
        visited[startX][startY][0] = true;

        while (!q.isEmpty()) {
            Monkey current = q.poll();
            int x = current.x;
            int y = current.y;

            if (x == H-1 && y == W-1) {
                // 도착지점에 도달했을때, 가장 작은 이동 횟수를 업데이트시킴.
                if (minMoves == -1 || current.totalCount < minMoves) {
                    minMoves = current.totalCount;
                }
            }

            if (current.horseCount < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + horsex[i];
                    int ny = y + horsey[i];

                    if (nx >= 0 && nx < H && ny >= 0 && ny < W && board[nx][ny] == 0 && !visited[nx][ny][current.horseCount + 1]) {
                        visited[nx][ny][current.horseCount + 1] = true;
                        q.offer(new Monkey(nx, ny, current.horseCount + 1, current.totalCount + 1));
                    }
                }
            }

            for (int j = 0; j < 4; j++) {
                int nx = x + monkeyx[j];
                int ny = y + monkeyy[j];

                if (nx >= 0 && nx < H && ny >= 0 && ny < W && board[nx][ny] == 0 && !visited[nx][ny][current.horseCount]) {
                    visited[nx][ny][current.horseCount] = true;
                    q.offer(new Monkey(nx, ny, current.horseCount, current.totalCount + 1));

                }
            }

        }
        return minMoves;
    }

    public static class Monkey {
        int x;
        int y;
        int horseCount;
        int totalCount;

        public Monkey(int x, int y, int horseCount, int totalCount) {
            this.x = x;
            this.y = y;
            this.horseCount = horseCount;
            this.totalCount = totalCount;
        }
    }
}
