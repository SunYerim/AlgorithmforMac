import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    // dfs
    static int T, n, cnt, maxNum, minDis; // cnt: 코어 개수, maxNum : 최대 코어 개수, minDis: 최소 전선 길이
    static int[][] map;
    static ArrayList<Core> cores;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            cores = new ArrayList<>();
            cnt = 0;
            maxNum = 0;
            minDis = Integer.MAX_VALUE;

            StringTokenizer st;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if ((i != 0 || j != 0 || i != n - 1 || j != n - 1) && map[i][j] == 1)  {
                        cores.add(new Core(i, j));
                        cnt++;
                    }
                }
            }

            dfs(0, 0, 0);
            sb.append(minDis).append("\n");
        }
        System.out.println(sb);

    }


    // dfs와 백트레킹
    private static void dfs(int index, int coreCnt, int lCnt) { // lCnt : 현재까지 놓은 전선 길이.
        // 장애물 걸리는 거 없이, 엇갈리는 것 없이 각 변의 끝까지 도달하면 그때 전선 길이 누적
        // 양 끝변에 있는 core는 이미 전원 연결되어있다.
        // 기저조건 -> core갯수만큼 다 돌았으면 멈추게끔
        // 현재까지 연결된 코어 개수와 전선의 길이를 비교하여 가지치기
//        if (cnt - index + coreCnt < maxNum) return;
//        if (cnt - index + coreCnt == maxNum && minDis < lCnt) return;
        if (index  == cnt) {
            if (maxNum < coreCnt) {
                maxNum = coreCnt;
                minDis = lCnt;
            } else if (maxNum == coreCnt) {
                if (minDis > lCnt) minDis = lCnt;
            }
            return;
        }

        // 유도
        int x = cores.get(index).x;
        int y = cores.get(index).y;
        for (int i = 0; i < 4; i++) {
            if (isAvailable(x, y, i)) {
                // 전선 놓고
                int len = setWire(x, y, i, 2);
                // 다음 코어로 넘어가고
                dfs(index+1, coreCnt+1, lCnt+len);
                // 치우고 (백트레킹)
                setWire(x, y, i, 0);
            }
        }
        // 코어를 선택하지 않고 다음 코어로
        dfs(index + 1, coreCnt, lCnt);
    }

    // core 기준으로 dir방향으로 전원을 연결 할 수 있는지 없는지 판별하는 메서드
    // false 값을 return하면 고려할 필요가 없다.
    private static boolean isAvailable(int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            // 범위 나가면
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
            // 코어나 전선이 있으면 (전선은 2로 취급)
            if (map[nx][ny] >= 1) return false;
        }
        return true;
    }

    // dir 방향으로 전선을 놓고 치우는 메서드
    private static int setWire(int x, int y, int dir, int status) {
        int cnt = 0;
        int nx = x, ny = y, len1 = 0;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            // 범위 나가면
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
            // 변경
            map[nx][ny] = status;
            len1++;
        }
        return len1;
    }

    static class Core {
        int x, y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}