
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
        } else {
            System.out.println(fibonacci(n));
        }


    }

    private static BigInteger fibonacci(int num) {
        BigInteger a = BigInteger.valueOf(0);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger c = BigInteger.valueOf(1);
        // 2부터
        for (int i = 2; i <= num; i++) {
            c = a.add(b);
            a = b;
            b = c;
        }
        return b;
    }
}
