import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea2805 {
    static int[][] farm;
    public static void main(String[] args) throws IOException {
        // N*N 크기의 농장
        // 중간 줄 기준으로 끊는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int count = 0;
            int N = Integer.parseInt(br.readLine());
            farm = new int[N][N];
            for (int i = 0; i < N; i++) {
                String string = br.readLine();
                for (int j = 0; j < N; j++) {
                    farm[i][j] = string.charAt(j) - '0';
                }
            }

            int start = N / 2;
            int end = N / 2;

            for (int k = 0; k < N; k++) {
                for (int l = start; l <= end; l++) {
                    count += farm[k][l];
                }
                if (k < N/2){
                    start--;
                    end++;
                } else {
                    start++;
                    end--;
                }
            }
            System.out.println("#"+tc+" "+count);
        }
    }

    // 가운데 줄을 기준으로 위 아래 요소 값들 합을 return 해주는 메소드

}
