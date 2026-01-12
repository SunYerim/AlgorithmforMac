import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static String in, out;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        in = br.readLine();
        out = br.readLine();

        int idx = 0;
        int answer = 0;

        while (idx < out.length()) {
            int maxLen = 0;
            // 현재 idx에서 만들 수 있는 가장 긴 부분의 문자열
            for (int j = 1; idx + j <= out.length(); j++) {
                String subString = out.substring(idx, idx + j);

                // 포함이 되어있는가?
                if (in.contains(subString)) {
                    maxLen = j;
                } else {
                    break;
                }
            }
            // 갱신
            idx += maxLen;
            answer++;
        }

        System.out.println(answer);
    }

}
