import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (isGroupWord(sc.next())) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isGroupWord(String word) {
        boolean[] seen = new boolean[26];
        int prev = 0;

        for (int i = 0; i < word.length(); i++) {
            int curr = word.charAt(i);
            if (prev != curr) {
                if (seen[curr - 'a']) {
                    return false;
                }
                seen[curr - 'a'] = true;
            }
            prev = curr;
        }
        return true;
    }
}