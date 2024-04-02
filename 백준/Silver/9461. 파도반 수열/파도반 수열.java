
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, n;
    static long[] squares;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        // 배열 채워놓기
        squares = new long[101];
        squares[0] = 1;
        squares[1] = 1;
        squares[2] = 1;
        for (int i = 3; i <= 100; i++) {
            squares[i] = squares[i-2] + squares[i-3];
        }


        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            System.out.println(squares[n-1]);

        }
    }
}
