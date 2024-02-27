import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic (refactor)
    1. node클래스에 next함수를 추가 -> 세포 상태의 변화를 관리
    2. 해당 함수가 호출되면 1씩 감소하고, 0이 되면 활성화 상태로 바꿔준다음 */
public class Solution {
    static int T, n, m, k;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static final int DEATH = 0, ACTIVE = -1, INACTIVE = -2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[k*2+n][k*2+m];
            visited = new boolean[k*2+n][k*2+m];
            queue.clear();

            int temp1;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    temp1 = Integer.parseInt(st.nextToken());

                    if (temp1!= 0) {
                        map[k+i][k+j] = temp1;
                        visited[k+i][k+j] = true;
                        queue.add(new Node(k+i, k+j, temp1));
                    }

                }
            }

            int ans = bfs();
            sb.append("#"+tc+" "+ans+"\n");
        }
        System.out.println(sb);

    }

    private static void check(Node node) {
        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (visited[nx][ny]) continue;

            // 한 칸으로 두 개의 숫자가 들어오려할때 숫자 정해주는 로직
            if (map[nx][ny] < node.value)
                map[nx][ny] = node.value;
        }
    }

    private static int bfs() {
        int count = k;

        while (count-- > 0) {
            int size = queue.size();
            for (Node n : queue) {
                if (n.status == ACTIVE)
                    check(n); // 나의 주변 세포들의 값을 정해준다.
            }

            for (int i = 0; i < size; i++) {
                Node tmp = queue.poll();

                // 번식부분
                if (tmp.status == ACTIVE) {
                    for (int d = 0; d < 4; d++) {
                        int nx = tmp.x + dx[d];
                        int ny = tmp.y + dy[d];
                        if (visited[nx][ny]) continue;

                        queue.add(new Node(nx, ny, map[nx][ny]));
                        visited[nx][ny] = true;
                    }
                }
                        tmp.next();

                        if (tmp.status == DEATH) continue;
                        queue.add(tmp);

            }
        }
        return queue.size();
    }


    private static class Node {
        private int x, y, value, temp, status;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.temp = value;
            this.status = INACTIVE;
        }
        // next 함수
        public void next() {
            switch (status) {
                // 비활성화 상태일때
                case INACTIVE:
                    if (--temp == 0) status = ACTIVE;
                    break;
                case ACTIVE:
                    if (++temp == value) status = DEATH;
                    break;
            }
        }
    }
}