import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, N, M, answer;
    static boolean[] visited;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N + 1][N + 1];


            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                board[a][b] = 1;
                board[b][a] = 1;
            }

            answer = N - 1;
//            visited = new boolean[N + 1];
//            bfs(1);
            sb.append(answer).append("\n");
        }
        System.out.print(sb.toString());
    }

//    public static void bfs(int start) {
//        Queue<int[]> q = new LinkedList<>();
//        q.add(new int[]{start, 1}); // cnt
//        visited[start] = true;
//        while (!q.isEmpty()) {
//            int[] cur = q.poll();
//            int pos = cur[0];
//            int cnt = cur[1]; // 땅 다 돌았는지
//            if (check()) {
//                answer = Math.min(answer, cnt);
//            }
//
//            // 연결된 곳 모두
//            for (int i = 1; i <= N; i++) {
//                if (!visited[i] && board[i][pos] == 1) {
//                    q.add(new int[]{i, cnt + 1});
//                    visited[i] = true;
//                }
//            }
//        }
//
//    }
//
//    public static boolean check() {
//        boolean flag = true;
//
//        for (int i = 1; i <= N; i++) {
//            if (!visited[i]) {
//                flag = false;
//                break;
//            }
//        }
//
//        return flag;
//    }

}
