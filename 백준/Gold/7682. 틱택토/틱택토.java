import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // o, x 갯수가 다르면
    // 가로, 세로, 대각선 check
    static StringBuilder sb = new StringBuilder();
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (true) {
            command = br.readLine();
            if (command.equals("end")) {
                break;
            }

            board = new char[3][3];
            int cntO = 0;
            int cntX = 0;
            int empty = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char c = command.charAt((i * 3) + j);
                    board[i][j] = c;
                    switch (c) {
                        case 'O':
                            cntO++;
                            break;
                        case 'X':
                            cntX++;
                            break;
                        case '.':
                            empty++;
                            break;
                    }
                }
            }

            boolean isO = check('O');
            boolean isX = check('X');

            // 다 찼을때
            if (empty == 0) {
                if (!isO && cntO + 1 == cntX) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }
            // 비었을 때
            else {
                if (isO && !isX && cntO == cntX) {
                    sb.append("valid").append("\n");
                } else if (!isO && isX && cntO + 1 == cntX) {
                    sb.append("valid").append("\n");
                } else {
                    sb.append("invalid").append("\n");
                }
            }
        }
        System.out.print(sb.toString());
    }

    public static boolean check(char c) {
        // 가로, 세로
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == c && board[i][1] == c && board[i][2] == c) {
                return true;
            }
            if (board[0][i] == c && board[1][i] == c && board[2][i] == c) {
                return true;
            }
        }

        // 대각선
        if (board[0][0] == c && board[1][1] == c && board[2][2] == c) {
            return true;
        }
        if (board[0][2] == c && board[1][1] == c && board[2][0] == c) {
            return true;
        }

        return false;
    }
}
