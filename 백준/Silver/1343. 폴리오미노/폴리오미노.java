import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String board = br.readLine();

        int countX = 0;

        for (int i = 0; i < board.length(); i++) {
            char c = board.charAt(i);

            if (c == 'X') {
                countX++;
            } else {
                if (countX > 0) {
                    if (!fillBoard(countX)) {
                        System.out.println(-1);
                        return;
                    }
                    countX = 0;
                }
                sb.append('.');
            }
        }
        // 마지막까지 'X'가 남아있는 경우는 처리
        if (countX > 0) {
            if (!fillBoard(countX)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sb.toString());
    }

    private static boolean fillBoard(int countX) {
        // 4d의 배수
        while (countX >= 4) {
            sb.append("AAAA");
            countX -= 4;
        }
        if (countX == 2) {
            sb.append("BB");
            return true;
        } else if (countX == 0) {
            return true;
        }
        return false; // 안 나눠 떨어짐
    }

}