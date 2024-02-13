import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int bag = 0;

        while (n >= 0) {
            // 5로 나누어 떨어지면
            if (n % 5 == 0) {
                bag += (n / 5);
                System.out.println(bag);
                return;
            }
            // 그렇지 않은 경우라면
            n -= 3;
            bag += 1;


        }

        if (n < 0) {
            System.out.println(-1);
        }



    }
}