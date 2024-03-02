import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, maxRain, numberOfArea;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> rains; // 잠기는 높이에 따른 영역 개수 저장
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        rains = new ArrayList<>();
        StringTokenizer st;

        maxRain = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > maxRain)
                    maxRain = map[i][j];
            }
        }

        for (int i = 0; i <= maxRain; i++) {
            numberOfArea = 0;
            visited = new boolean[n][n]; // 탐색에 사용
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!visited[j][k] && map[j][k] > i) {
                        numberOfArea++;
                        dfs(j, k, i);
                    }

                }
            }
            rains.add(numberOfArea);
        }

        Collections.sort(rains);

        System.out.println(rains.get(rains.size()-1));


    }

    // dfs메서드
    private static void dfs(int x, int y, int rain) {
        // 기저
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (!visited[nx][ny] && map[nx][ny] > rain) {
                    dfs(nx, ny, rain);
                }
            }
        }
    }

}