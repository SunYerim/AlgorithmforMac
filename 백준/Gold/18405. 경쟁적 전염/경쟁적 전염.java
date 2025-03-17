import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, s;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Queue<Virus> q = new LinkedList<>();
    static List<Virus> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num != 0) {
                    list.add(new Virus(i, j, num, 0));
                }
            }
        }

        Collections.sort(list);

        for (Virus virus : list) {
            q.add(virus);
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        bfs();

        System.out.println(board[x-1][y-1]);
    }

    private static void bfs() {

        while (!q.isEmpty()) {
            Virus curr = q.poll();
            int x = curr.x;
            int y = curr.y;

            if (curr.time == s) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] == 0) {
                    board[nx][ny] = curr.number;
                    q.add(new Virus(nx, ny, curr.number, curr.time + 1));
                }
            }
        }
    }

    static class Virus implements Comparable<Virus>{
        int x, y, number, time;
        public Virus(int x, int y, int number, int time) {
            this.x = x;
            this.y = y;
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(Virus o) {
            return this.number - o.number;
        }
    }

}
