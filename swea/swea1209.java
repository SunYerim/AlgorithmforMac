import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1209 {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        // 메소드 분리 -> 가로들만 다 합 구해서 제일 큰 값 return해주는 메소드
        // 세로들만 합 구해서 제일 큰 값 return 해주는 메소드
        // 대각선 두개는 따로
        // 4개중에 max인 값 return
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();
            map = new int[100][100];
            // 지도 저장
            for (int i = 0; i < 100; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int finalMax = Math.max(Math.max(returnGaromax(map), returnSeromax(map)), Math.max(cross1(map), cross2(map)));
            System.out.println("#" + tc + " " + finalMax);

        }
    }

    public static int cross1(int[][] map) {
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            answer += map[i][i];
        }
        return answer;
    }
    public static int cross2(int[][] map) {
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            //(0, 99) -> (1, 98) -> (2, 97) -> .... -> (99, 0)
            answer += map[i][99-i];
        }
        return answer;
    }
    public static int returnGaromax(int[][] map) {
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < 100; i++) {
            int total = 0;
            for (int j = 0; j < 100; j++){
                total += map[i][j];
            }
            if (maxNum <= total) {
                maxNum = total;
            }
        }
        return maxNum;
    }

    public static int returnSeromax(int[][] map) {
        int maxNum = Integer.MIN_VALUE;

        for (int i = 0; i < 100; i++) {
            int total = 0;
            int[] tmp = getColumn(map, i);
            for (int j = 0; j < 100; j++) {
                total += tmp[j];
            }

            if (maxNum <= total) {
                maxNum = total;
            }
        }
        return maxNum;
    }


    public static int[] getColumn(int[][] map, int x) {
        int[] returnSero = new int[100];
        for (int i = 0; i < 100; i++) {
            returnSero[i] = map[i][x];
        }
        return returnSero;
    }

}
