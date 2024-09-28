
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 배열 두 개로 문제 풀이 (원배열, 1년 뒤의 빙하 모양 배열)
 *  - 동서남북 기준 0인 것의 개수만큼 --
 * 2. 이 때, 1년 뒤 빙하 배열에서 빙하 갯수를 세아리는 메서드
 * 3. 빙하 개수가 쪼개지지 않으면 0 return
 * */
public class Main {

    static int n, m, answer;
    static boolean year;
    static int[][] map1, map2;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map1 = new int[n][m];
        visited = new boolean[n][m];
        map2 = new int[n][m];

        // 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 1;
        year = true;
        while (year) {
            visited = new boolean[n][m];
            glacierCutting(); // 빙하깎고

            int count2 = 0;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (!visited[i][j] && map2[i][j] > 0) {
                        bfs(i, j);
                        count2++;
                    }
                }
            }

            // 한덩이가 아니면
            if (count2 > 1) {
                year = false;
                System.out.println(answer++);
                return;
            }

            boolean isAllMelted = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map2[i][j] > 0) {
                        isAllMelted = false;
                        break;
                    }
                }
                if (!isAllMelted) break;
            }
            // 모든 빙하가 녹은 경우
            if (isAllMelted) {
                System.out.println(0);
                return;
            }
            answer++;
            map1 = copyMap(map2);
        }

    }

    // 빙하 깎기
    private static void glacierCutting() {
        map2 = new int[n][m];  // map2를 새로운 배열로 초기화
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (map1[i][j] > 0) {
                    int glacier = 0;
                    // 4방 탐색
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (map1[nx][ny] == 0) {
                            glacier++;
                        }
                    }
                    // 현재 위치의 빙하 깎기
                    map2[i][j] = Math.max(0, map1[i][j] - glacier);
                }
            }
        }
    }

    // map2 기준으로 계산
    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            // 4방향
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map2[nx][ny] > 0) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    // 배열 복사
    private static int[][] copyMap(int[][] map) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }
}
