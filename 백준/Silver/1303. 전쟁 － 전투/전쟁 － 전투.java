import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, me, you;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 가로
        m = Integer.parseInt(st.nextToken()); // 세로

        map = new char[m][n];
        visited = new boolean[m][n];
        me = 0;
        you = 0;

        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // bfs시작
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int num = bfs(i, j);
                    if (map[i][j] == 'W')
                        me += num;
                    else if (map[i][j] == 'B')
                        you += num;
                }
            }
        }

        System.out.print(me + " " + you);

    }

    private static int bfs(int x, int y) {
        // 해당좌표부터
        int answer = 1;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            char color = map[curr[0]][curr[1]];
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) continue;
                if (map[nx][ny] == color) {
//                    System.out.println("nx: " + nx + ", ny: " + ny + "  : " + map[nx][ny]); // 디버깅용 출력
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    answer++;
                }
            }
        }
        return answer * answer;
    }

}
