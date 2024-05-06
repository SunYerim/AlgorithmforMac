import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dxx = {-1, -1, 1, 1};
    static int[] dyy = {-1, 1, -1, 1};
    static int[][] map;
    static int[][] status;
    static int n, m;

    public static void main(String[] args) throws IOException {
        // 비바라기는 Easy 해요 ㅎㅋㅎㅋ
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        // map 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 비바라기 초기 위치 설정
        ArrayList<int[]> clouds = new ArrayList<>();
        clouds.add(new int[]{n - 1, 0});
        clouds.add(new int[]{n - 1, 1});
        clouds.add(new int[]{n - 2, 0});
        clouds.add(new int[]{n - 2, 1});
        status = new int[n][n];

        // 구름의 이동 및 비내리기
        for (int k = 0; k < m; k++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 구름의 이동
            while (s-- > 0) {
//                int size = clouds.size();
                for (int i = 0; i < clouds.size(); i++) {
                    int[] curr = clouds.get(i);
                    int nx = curr[0] + dx[d];
                    int ny = curr[1] + dy[d];

                    // 만약 범위를 넘어가면
                    if (nx > n-1) nx = 0;
                    if (ny > n-1) ny = 0;
                    if (nx < 0) nx = n-1;
                    if (ny < 0) ny = n-1;

//                    status[curr[0]][curr[1]] = 2; // 2 -> 구름이 있다가 없어진 칸
//                    status[nx][ny] = 1; // 1 -> 구름이 새로 생기는 칸

                    clouds.set(i, new int[]{nx, ny});
                }
            }

            // clouds 배열에 비 내림
            for (int i = 0; i < clouds.size(); i++) {
                int[] tmp = clouds.get(i);
                map[tmp[0]][tmp[1]]+=1;
                status[tmp[0]][tmp[1]] = 1;
            }

            // 대각선 체크하여 물의 양 증가
            for (int[] cloud : clouds) {
                int x = cloud[0];
                int y = cloud[1];
                int count = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dxx[i];
                    int ny = y + dyy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] > 0) {
                        count++;
                    }
                }
                map[x][y] += count;
            }

            // 구름 제거
            clouds.clear();

            // 비 내린 후 구름 제거 및 대각선 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (status[i][j] == 0 && map[i][j] >= 2) {
                        clouds.add(new int[]{i, j}); // 새로운 구름 생성
                        map[i][j] -= 2; // 비 내린 후 물의 양 감소
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    status[i][j] = 0;
                }
            }

        }

        // 물의 양 합산
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result += map[i][j];
            }
        }

        System.out.println(result);
    }


}