import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea1216 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            br.readLine(); // 테스트 케이스 번호를 읽고 무시
            char[][] map = new char[100][100];

            // 입력 받기
            for (int i = 0; i < 100; i++) {
                String line = br.readLine();
                for (int j = 0; j < 100; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            int maxPalindrome = 0;

            // 가로 방향에서 최대 회문 길이 찾기
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    for (int k = j; k < 100; k++) {
                        if (isPalindrome(map[i], j, k)) {
                            int length = k - j + 1;
                            maxPalindrome = Math.max(maxPalindrome, length);
                        }
                    }
                }
            }

            // 세로 방향에서 최대 회문 길이 찾기
            for (int j = 0; j < 100; j++) {
                for (int i = 0; i < 100; i++) {
                    for (int k = i; k < 100; k++) {
                        if (isPalindrome(getColumn(map, j), i, k)) {
                            int length = k - i + 1;
                            maxPalindrome = Math.max(maxPalindrome, length);
                        }
                    }
                }
            }

            System.out.println("#" + T + " " + maxPalindrome);
        }
    }

    // 팰린드롬 판별 함수
    public static boolean isPalindrome(char[] value, int start, int end) {
        while (start <= end) {
            if (value[start++] != value[end--]) {
                return false;
            }
        }
        return true;
    }

    // 열을 추출하는 함수
    public static char[] getColumn(char[][] map, int col) {
        char[] column = new char[map.length];
        for (int i = 0; i < map.length; i++) {
            column[i] = map[i][col];
        }
        return column;
    }
}
