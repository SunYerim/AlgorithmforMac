import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // triangle[n][k] = triangle[n-1][k-1] + triangle[n-1][k];
    static int[][] pascal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // n번째까지 파스칼 삼각형을 완성해야 한다.
        pascal = new int[n+1][n+1];

        // 베이스값
        // 양 끝 칸에는 1이 들어간다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j==1 || i == j)
                    pascal[i][j] = 1;
            }
        }

        for (int i = 3; i <= n; i++) {
            // n행까지 로직 실행
            for (int r = 2; r < i; r++) {
                pascal[i][r] = pascal[i-1][r-1] + pascal[i-1][r];
            }
        }

        System.out.println(pascal[n][k]);


    }
}