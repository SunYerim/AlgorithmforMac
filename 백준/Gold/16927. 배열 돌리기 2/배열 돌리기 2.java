import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, R;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int rowStart = 0;
        int rowEnd = N-1;
        int colStart = 0;
        int colEnd = M-1;

        while (true) {
            int size = (rowEnd - rowStart + 1) * 2 + (colEnd - colStart + 1) * 2 - 4;
            roundMap(rowStart, rowEnd, colStart, colEnd, R % size);
            rowStart+=1;
            rowEnd-=1;
            colStart+=1;
            colEnd-=1;
            if (rowStart > rowEnd || colStart > colEnd) break;
        }

        // 메서드 실행



        // 출력
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    private static void roundMap(int rowStart, int rowEnd, int colStart, int colEnd, int cnt) {

        // 횟수만큼 돌린다.
        for (int k = 0; k < cnt; k++){
            int tmp = map[rowStart][colStart];

            for (int j = colStart; j < colEnd; j++) {
                map[rowStart][j] = map[rowStart][j+1];
            }

            for (int i = rowStart; i < rowEnd; i++) {
                map[i][colEnd] = map[i+1][colEnd];
            }

            for (int j = colEnd; j > colStart; j--) {
                map[rowEnd][j] = map[rowEnd][j-1];
            }

            for (int i = rowEnd; i > rowStart; i--) {
                map[i][colStart] = map[i-1][colStart];
            }

            map[rowStart+1][colStart] = tmp;
        }



    }

}