import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int totalPeople = 0;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += map[i][j]; // 총인구수
            }
        }

        /* x, y를 임의로 정한 후, 범위 내에서 d1, d2가 될 수 있는 조합을 찾아서 그에 맞춰 선거구를 나누고,
        max-min 인구 차이 최솟값을 갱신시켜 출력*/

        // 그냥 4중 for문을 돌려서 x, y, d1, d2 조합을 다 돌려본다.
        for (int x = 0;  x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n; d1++){
                    for (int d2 = 1; d2 < n; d2++) {
                        // 거를 조건
                        if (x + d1 + d2 >= n) continue;
                        if (y-d1 < 0 || y + d2 >= n) continue;

                        disperate(x, y, d1, d2);
                    }
                }
            }
        }
        System.out.println(min);

    }

    private static void disperate(int x, int y, int d1, int d2) {
        // 경계선
        boolean[][] border = new boolean[n][n];
        for (int i = 0; i <= d1; i++) {
            border[x+i][y-i] = true;
            border[x+d2+i][y+d2-i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            border[x+i][y+i] = true;
            border[x+d1+i][y-d1+i] = true;
        }

        // 5구역 인구수 담을 리스트
        int[] persons = new int[5];

        // 1구역
        for (int i = 0; i < x+d1; i++) {
            for (int j = 0; j <= y; j++) {
                // 경계선 이면
                if (border[i][j]) break;
                persons[0] += map[i][j];
            }
        }


        // 2구역
        for (int i = 0; i <= x + d2; i++) {
            for (int j = n-1; j > y; j--) {
                // 경계선
                if (border[i][j]) break;
                persons[1] += map[i][j];
            }
        }

        // 3구역
        for (int i = x+d1; i < n; i++) {
            for (int j = 0; j < y-d1+d2; j++) {
                if (border[i][j]) break;
                persons[2] += map[i][j];
            }
        }

        // 4구역
        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n-1; j >= y - d1 + d2; j--) {
                if (border[i][j]) break;
                persons[3] += map[i][j];
            }
        }


        // 5구역
        persons[4] = totalPeople;
        for (int i = 0; i < 4; i++) {
            persons[4] -= persons[i];
        }

        Arrays.sort(persons);
        min = Math.min(min, persons[4]-persons[0]);
    }

}