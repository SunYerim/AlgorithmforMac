import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*logic
    1. 미로찾기 최단경로 문제 -> bfs사용
    2*/
public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int[][] maze;

    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N][M];
        maze = new int[N][M];


        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++){
                maze[i][j] = input.charAt(j) - '0';
            }
        }

        // 출발지 -> 방문처리
        //visited[0][0] = true;
        bfs(0, 0);
        System.out.println(maze[N-1][M-1]);



    }

    public static void bfs(int x, int y) {
        // queue 사용
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        // q가 비어있을 때 까지.
        while (!q.isEmpty()){
            int[] now = q.poll();

            int nx = now[0];
            int ny = now[1];
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextX = nx + dx[i];
                int nextY = ny + dy[i];

                // 범위 내에 있으면서
                if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M) {
                    // 길이면서 방문하지 않은 칸일때,
                    if (maze[nextX][nextY]==1 && !visited[nextX][nextY]) {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                        maze[nextX][nextY] = maze[nx][ny] + 1;

                    }
                }

            }
        }

    }
}
