import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long a, b, c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());


        // 시간제한 -> 분할정복문제
        System.out.println((int)(pow(a, b) % c));

    }

    private static long pow(long a, long b) {
        if (b == 1) {
            return a % c;
        }

        // 반으로 나눈 거듭제곱 계산
        long res = pow(a, b / 2);

        // 지수가 짝수면
        if (b % 2 == 0) {
            return res * res % c;
            // 지수가 홀수면
        } else {
            return (res * res % c) * a % c;
        }
    }
}