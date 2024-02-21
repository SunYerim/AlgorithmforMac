/* logic
    1. cctvDirection 메서드를 완성시키고 -> cctv 번호에 따라 방향을 return해주는 메서드
    2. map에서 cctv가 나올때마다 해당 메서드를 호출 => 여기서 사각 지대의 영역을 최소로 해주는 방향은 어떤식으로 판별할건지
    3. 진행 방향에서 벽이 나오면 진행 못하게끔 처리*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int minBlindSpot = Integer.MAX_VALUE;
    static int n, m;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0}; // 동-서-남-북
    static int[] dy = {0, -1, 0, 1};
    static ArrayList<int[]> cctvs; // cctv의 좌표와 유형
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cctvs = new ArrayList<>(); // cctv 목록을 담기 위함.

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new int[]{i, j, map[i][j]});
                }
            }
        }
//        System.out.println(Arrays.toString(cctvs.get(0)));

        // cctvDirections 실행 -> 어차피 모든 cctv를 모든 방향으로 돌려야하므로 4방향 탐색을 진행하면 됨.
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (map[i][j] != 0 || map[i][j] != 6) {
//                    cctvDirections(map[i][j], i, j,  , map);
//                    // 방향이 결정되고 checkArea 메서드에서 map에 경로의 길에 -1 처리 해줌.
//                }
//            }
//        }

        dfs(0, map);
        System.out.println(minBlindSpot);


    }

    // dfs 4방향
    static void dfs(int idx, int[][] temp) {
        // 기저
        if (idx == cctvs.size()) {
            // 사각 지대의 크기 계산하고 최솟값을 갱신
            int blindSpot = getBlindSpot(temp);
            minBlindSpot = Math.min(minBlindSpot, blindSpot);
            return;
        }

        int[] cctv = cctvs.get(idx);
        int x = cctv[0];
        int y = cctv[1];


        for (int dir = 0; dir < 4; dir++) {
            // 현재 cctv의 방향을 dir로 설정하고 감시 영역을 표시
            int[][] newTemp = copyMap(temp);
            cctvDirections(cctv[2], x, y, dir, newTemp);
            dfs(idx + 1, newTemp);
        }
    }

    // 최소영역
    private static int getBlindSpot(int[][] temp) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) count++;
            }
        }
        return count;
    }

    private static int[][] copyMap(int[][] original) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    // cctv의 번호에따라 뻗어나가는 방향을 표시하는 메서드
    private static void cctvDirections(int cctv, int cctvX, int cctvY, int dir, int[][] temp) {
        switch(cctv) {
            case 1:
                checkArea(cctvX, cctvY, dir, temp);
                break;
            case 2:
                checkArea(cctvX, cctvY, dir, temp);
                checkArea(cctvX, cctvY, dir+2, temp);
                break;
            case 3:
                checkArea(cctvX, cctvY, dir, temp);
                checkArea(cctvX, cctvY, dir+1, temp);
                break;
            case 4:
                checkArea(cctvX, cctvY, dir, temp);
                checkArea(cctvX, cctvY, dir+1, temp);
                checkArea(cctvX, cctvY, dir+2, temp);
                break;
            case 5:
                for (int i = 0; i < 4; i++) {
                    checkArea(cctvX, cctvY, i, temp);
                }
                break;
        }

    }

    // 주어진 방향에서 감시할 수 있는 영역을 표시하는 메서드
    private static void checkArea(int x, int y, int dir, int[][] temp) {
        dir %= 4;
        while (true) {
            x += dx[dir];
            y += dy[dir];
            // 범위 밖이거나 벽이면
            if (x < 0 || y < 0 || x >= n || y >= m || temp[x][y] == 6) break;
            if (temp[x][y] != 0) continue;
            temp[x][y] = -1; // 감시가능한 영역임을 표시
        }
    }
}