import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1974 {
    static int[][] sdoqu;
    public static void main(String[] args) throws IOException {
        // 스토쿠 검증은 가로, 세로, 3*3칸 -> 총 3가지
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            boolean flag = true;
            sdoqu = new int[9][9];

            for (int i = 0; i < 9; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    sdoqu[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < 9; k++) {
                //System.out.println(flag);
                if (!sdoqugaro(sdoqu[k]) || !sdoqusero(getColumn(sdoqu, k))) {
                    flag = false;
                    break;
                }

            }
            // 가로검증, 세로검증, 각칸 검증 다 되어야 return 1
            // 하나라도 안 되면 return 0
            //System.out.println("3*3:"+sdoquthreebythree());
            if (flag && sdoquthreebythree()){
                System.out.println("#"+tc+" "+1);
            } else {
                System.out.println("#"+tc+" "+0);
            }
        }
    }
    public static boolean sdoqugaro(int[] garo) {
        boolean[] check = new boolean[10]; // 1부터 9까지의 숫자 사용 여부를 체크하는 배열

        for (int num : garo) {
            if (check[num]) {
                return false; // 중복된 숫자가 있으면 false 반환
            }
            check[num] = true;
        }
        return true;
    }

    public static boolean sdoqusero(int[] sero) {
        boolean[] check = new boolean[10]; // 1부터 9까지의 숫자 사용 여부를 체크하는 배열

        for (int num : sero) {
            if (check[num]) {
                return false; // 중복된 숫자가 있으면 false 반환
            }
            check[num] = true;
        }
        return true;
    }


    public static int[] getColumn(int[][] map, int x) {
        int[] returnSero = new int[9];
        for (int i = 0; i < 9; i++) {
            returnSero[i] = map[i][x];
        }
        return returnSero;
    }
    public static boolean sdoquthreebythree() {
        boolean[] check = new boolean[10]; // 1부터 9까지의 숫자 사용 여부를 체크하는 배열

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Arrays.fill(check, false); // 각 3x3 부분 그리드마다 체크 배열의 값을 false로 초기화하고 들어감.

                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        int num = sdoqu[x][y];

                        if (check[num]) {
                            return false; // 중복된 숫자가 있으면 false 반환
                        }
                        check[num] = true;
                    }
                }
            }
        }
        return true;
    }

}
