import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static int[] days;
    static int[][] data;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T =  Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int total = 0;
            N = Integer.parseInt(br.readLine());
            days = new int[N];
            data = new int[3][N]; // a, b, c 회사 n일
            // 입력 받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    data[j][i] = num;
                }
            }
            // 최댓값만 골라서 days 배열에 넣는다.
            for (int i = 0; i < N; i++) {
                int num = Math.max(data[0][i], data[1][i]);
                int num2 = Math.max(num, data[2][i]);
                // 만약 음수이면
                if (num2 <= 0) {
                    days[i] = 0;
                } else {
                    days[i] = num2;
                }
            }

            // days배열의 모든 요소 더하기
            for (int i = 0; i < days.length; i++) {
                total += days[i];
            }
            sb.append(total).append("\n");
        }
        System.out.println(sb.toString());
    }
}