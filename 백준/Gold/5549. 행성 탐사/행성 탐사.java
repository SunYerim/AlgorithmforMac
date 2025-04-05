import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 바보멍청이 -> 시간초과
    // 객체 사용해서 prefix
    static int M, N, K;
    static char[][] board;
    static Planet[][] prefix;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        board = new char[M][N];
        prefix = new Planet[M][N];

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                prefix[i][j] = new Planet();
            }
        }

        // 첫 행과 열을 초기화
        char first = board[0][0];
        if (first == 'J') {
            prefix[0][0].jungle++;
        } else if (first == 'I') {
            prefix[0][0].ice++;
        } else {
            prefix[0][0].sea++;
        }

        for (int i = 1; i < M; i++) {
            char target = board[i][0];
            Planet prev = prefix[i - 1][0];
            prefix[i][0] = new Planet(prev.jungle, prev.sea, prev.ice);
            switch (target) {
                case 'J':
                    prefix[i][0].jungle++;
                    break;
                case 'I':
                    prefix[i][0].ice++;
                    break;
                case 'O':
                    prefix[i][0].sea++;
                    break;
            }
        }

        for (int i = 1; i < N; i++) {
            char target = board[0][i];
            Planet prev = prefix[0][i - 1];
            prefix[0][i] = new Planet(prev.jungle, prev.sea, prev.ice);
            switch (target) {
                case 'J':
                    prefix[0][i].jungle++;
                    break;
                case 'I':
                    prefix[0][i].ice++;
                    break;
                case 'O':
                    prefix[0][i].sea++;
                    break;

            }
        }

        // 나머지
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                Planet a = prefix[i - 1][j];
                Planet b = prefix[i][j - 1];
                Planet c = prefix[i - 1][j - 1];

                int jungle = a.jungle + b.jungle - c.jungle;
                int sea = a.sea + b.sea - c.sea;
                int ice = a.ice + b.ice - c.ice;

                char curr = board[i][j];
                if (curr == 'J') {
                    jungle++;
                } else if (curr == 'O') {
                    sea++;
                } else if (curr == 'I') {
                    ice++;
                }

                prefix[i][j] = new Planet(jungle, sea, ice);
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken()) - 1;
            int startX = Integer.parseInt(st.nextToken()) - 1;
            int endY = Integer.parseInt(st.nextToken()) - 1;
            int endX = Integer.parseInt(st.nextToken()) - 1;

            int jungle = prefix[endY][endX].jungle;
            int sea = prefix[endY][endX].sea;
            int ice = prefix[endY][endX].ice;

            if (startY > 0) {
                jungle -= prefix[startY - 1][endX].jungle;
                sea -= prefix[startY - 1][endX].sea;
                ice -= prefix[startY - 1][endX].ice;
            }

            if (startX > 0) {
                jungle -= prefix[endY][startX - 1].jungle;
                sea -= prefix[endY][startX - 1].sea;
                ice -= prefix[endY][startX - 1].ice;
            }

            if (startY > 0 && startX > 0) {
                jungle += prefix[startY - 1][startX - 1].jungle;
                sea += prefix[startY - 1][startX - 1].sea;
                ice += prefix[startY - 1][startX - 1].ice;
            }

            sb.append(jungle).append(" ").append(sea).append(" ").append(ice).append("\n");
        }

        System.out.print(sb.toString());

    }

    static class Planet {

        int jungle, sea, ice;

        public Planet() {

        }

        public Planet(int jungle, int sea, int ice) {
            this.jungle = jungle;
            this.sea = sea;
            this.ice = ice;
        }
    }

}
