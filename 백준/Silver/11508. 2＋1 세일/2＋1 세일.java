import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int n;
    static int[] milks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        milks = new int[n];
        for (int i = 0; i < n; i++) {
            milks[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Integer[] milks2 = Arrays.stream(milks).boxed().toArray(Integer[]::new); // 박싱
        Arrays.sort(milks2, Collections.reverseOrder());


        // 3개씩 쪼갠다.
        int a = milks2.length / 3;
        int b = milks2.length % 3; // 그냥 지불해야할 금액
        int money = 0;

        for (int i = 0; i < 3*a; i+=3) {
            for (int j = 0; j < 3; j++) {
                if (j == 2) {
                    continue;

                }
                money += milks2[i+j];
            }
        }

        if (b != 0) {
            for (int i = 3*a; i < milks2.length; i++) {
                money += milks2[i];
            }
        }

        System.out.println(money);

    }
}