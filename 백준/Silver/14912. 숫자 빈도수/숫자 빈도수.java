import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 숫자 빈도수
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int d = sc.nextInt();
        int total = 0;

        // 1부터 n까지 순회하면서
        for (int i = 1; i <= n; i++) {
            total += countNum(i, d);
        }

        System.out.println(total);
    }

    private static int countNum(int x, int target) {
        String tmp = String.valueOf(x);
        int ans = 0;
        int len = tmp.length();
        for (int i = 0; i < len; i++) {
            if (Integer.parseInt(String.valueOf(tmp.charAt(i))) == target)
                ans++;
        }
        return ans;
    }
}