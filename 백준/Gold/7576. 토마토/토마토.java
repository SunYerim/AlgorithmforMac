
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M, N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = bfs();

        if (isTomato()){
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }


    }


    public static boolean isTomato() {
        // map 판에 토마토 다 익었나 안 익었나.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static int bfs() {
        Queue<Node> q = new LinkedList<>();
        int days = 0;
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j++) {
                // 1이라면
                if (map[i][j] == 1){
                    q.offer(new Node(i, j, 0));
                }
            }
        }

        while (!q.isEmpty()){
           Node current = q.poll();
           days = current.count; // return 시키기 위함.
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + current.x;
                int ny = dy[i] + current.y;
                if (nx >= 0 && nx < N && ny >= 0 && ny < M){
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = 1;

                        q.offer(new Node(nx, ny, current.count+1));
                    }
                }

            }
        }
        return days;
    }

    static class Node{
        int x;
        int y;
        int count;
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
