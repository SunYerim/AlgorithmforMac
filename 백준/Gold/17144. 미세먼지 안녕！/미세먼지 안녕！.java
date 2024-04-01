import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* logic
    1. 일단 4방향으로 확산(vistied배열은 필요없을 거 같음)
    2. 공기청정기 작동 (공기 청정기가 두 행을 차지하므로 나누어서 반시계, 시계방향 작동로직 구현
        - 이는 테두리 별로 나눠서 로직 처리를 한다.*/
public class Main {
    static int r, c, t, ans;
    static ArrayList<int[]> gong;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        gong = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 공기청정기의 위치 update
                if (map[i][j] == -1) {
                    gong.add(new int[]{i, j}); // 공기 청정기 위치 update
                }
            }
        }

        // T초동안 로직 진행
        for (int i = 1; i <= t; i++) {
            // 확산
            spread();

            // 작동
            // 반시계
            dir1(gong.get(0)[0], gong.get(0)[1]);

            // 시계
            dir2(gong.get(1)[0], gong.get(1)[1]);



        }


        // 먼지 총량을 더한다.
        for (int j = 0; j < r; j++) {
            for (int k = 0; k < c; k++) {
                if (map[j][k] != -1) {
                    ans += map[j][k];
                }
            }
        }

        System.out.println(ans);

    }

    // 확산을 시킨다.
    private static void spread() {
        // 바이러스 있는 부분은 제외, 범위 넘어가는 부분도 제외
        // 우선, 미세먼지 확산 출발 지점을 list에 담고? -> 빼면서 로직 실행
        // 임시 배열 생성
        int[][] temp = new int[r][c]; // 0으로 초기화된 배열

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    int changeCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // 범위 밖이면
                        if (nx < 0 || ny < 0 || nx >= r || ny >= c || map[nx][ny] == -1) continue;
                        temp[nx][ny] += map[i][j] / 5;
                        changeCnt++;
                    }
                    temp[i][j] -= map[i][j] / 5 * changeCnt;
                }
            }
        }

        // 다시 map 배열에 반영
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += temp[i][j];
            }
        }


    }

    // 반시계 방향으로 이동
    private static void dir1(int x, int y) {
        // x, y -> 공기청정기 윗쪽 부분의 위치.
        // 오른쪽
        int num = map[x][c-1]; // 저장
        for (int i = c-1; i > 0; i--) {
            map[x][i] = map[x][i-1];
        }
        map[x][1] = 0;

        // 위쪽
        int numTop = map[0][c-1]; // 맨 상단 숫자
        for (int i = 0; i < x; i++) {
            map[i][c-1] = map[i+1][c-1];
        }
        map[x-1][c-1] = num;

        // 왼쪽 상단
        int num2 = map[0][0];
        for (int i = 0; i < c-1; i++) {
            map[0][i] = map[0][i+1];
        }
        map[0][c-2] = numTop;


        // 아래쪽
        // 공기청정기쪽으로 가니까 따로 좌표 저장할 필요 없음.
        for (int i = x-1; i > 0; i--) {
            map[i][0] = map[i-1][0];
        }
        map[1][0] = num2;


    }

    // 시계 방향으로 이동
    private static void dir2(int x, int y) {
        // x, y -> 공기 청정기 아래쪽 부분의 위치
        // 공기 청정기 좌표로 들어오게되면 정화된다.
        // 오른쪽
        int num1 = map[x][c-1];
        for (int i = c-1; i > 0; i-- ){
            map[x][i] = map[x][i-1];
        }
        map[x][1] = 0;

        // 아래쪽
        int num2 = map[r-1][c-1];
        for (int i = r-1; i > x; i--) {
            map[i][c-1] = map[i-1][c-1];
        }
        map[x+1][c-1] = num1;

        //  왼쪽
        int num3 = map[r-1][0];

        for (int i = 0; i < c-1; i++) {
            map[r-1][i] = map[r-1][i+1];
        }
        map[r-1][c-2] = num2;

        // 윗쪽
        for (int i = x+1; i < r-1; i++) {
            map[i][0] = map[i+1][0];
        }
        map[r-2][0] = num3;


    }
}