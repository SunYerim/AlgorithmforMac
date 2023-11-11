import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea1215 {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            int testcaseLong = Integer.parseInt(br.readLine());
            map = new char[8][8];
            // 입력받기
            for (int i = 0; i < 8; i++) {
                String string = br.readLine();
                for (int j = 0; j < 8; j++) {
                    map[i][j] = string.charAt(j);
                }
            }

            int count = 0;

            // 가로부터 -> 글자길이 제한
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l <= 8-testcaseLong; l++) {
                    if (isPalindrome(map[k], l, l+testcaseLong-1)) {
                        count++;
                    }
                }
            }

            // 세로
            for (int k = 0; k < 8; k++) {
                for (int l = 0; l <= 8-testcaseLong; l++) {
                    if (isPalindrome(getColumn(k), l, l+testcaseLong-1)) {
                        count++;
                    }
                }
            }

            System.out.println("#"+T+" "+count);
        }
    }

    public static boolean isPalindrome(char[] value, int start, int end) {
        while (start <= end) {
            if (value[start++] != value[end--]) {
                return false;
            }
        }
        return true;
    }

    // 세로
    public static char[] getColumn(int col) {
        char[] column = new char[8];
        for (int i = 0; i < 8; i++) {
            column[i] = map[i][col];
        }
        return column;
    }
}
