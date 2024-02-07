import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] papers;
    static int[][] papers2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int answer = 0;
        papers = new int[N][2];
        papers2 = new int[100][100]; // 색종이 넓이 구하기 위함.

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            papers[i][0] = Integer.parseInt(st.nextToken());
            papers[i][1] = Integer.parseInt(st.nextToken());

            for (int start = papers[i][0]; start < papers[i][0]+10; start++) {
                for (int startY = papers[i][1]; startY < papers[i][1]+10; startY++) {
                    papers2[start][startY] = 1;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (papers2[i][j] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }

}