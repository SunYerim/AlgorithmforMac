import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static String line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        line = br.readLine();
        int len = line.length();

        int[] counts = new int[26];
        int distinctCount = 0;
        int maxLength = 0;
        
        int left = 0;

        for (int right = 0; right < len; right++) {
            char curr = line.charAt(right);

            if (counts[curr - 'a'] == 0) {
                distinctCount++;
            }

            counts[curr - 'a']++;

            // 초과
            while (distinctCount > N) {
                char leftChar = line.charAt(left);
                counts[leftChar - 'a']--;

                if (counts[leftChar - 'a'] == 0) {
                    distinctCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }

}
