import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int[] alphabet = new int[26];
    static int length, ans = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        length = s.length();

        for (int i = 0; i < length; i++) {
            alphabet[s.charAt(i) - 'a']++;
        }

        // backtraking
        backtraking("", -1);

        System.out.println(ans);


    }

    public static void backtraking(String currentStr, int lastIdx) {
        // 기저
        if (currentStr.length() == length) {
            ans++;
            return;
        }

        // 유도
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] > 0 && i != lastIdx) {
                alphabet[i]--;
                backtraking(currentStr + (char) (i + 'a'), i);
                alphabet[i]++;
            }
        }
    }

}
