import java.util.Scanner;

public class Main {
    static int n;
    static boolean[] count;
    static int[] numbers;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        numbers = new int[n]; // 숫자 o
        count = new boolean[n];

        permutation(0);
        System.out.println(sb);

    }

    private static void permutation(int depth) {
        if (depth == n) {
            for (int i = 0; i < n; i++) {
                sb.append(numbers[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 유도
        for (int i = 0; i < n; i++) {
            if (count[i]) continue;

            numbers[depth] = i+1;
            count[i] = true;
            permutation(depth+1);
            count[i] = false;
        }
    }
}