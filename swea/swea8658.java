import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class swea8658 {
    static int[] number;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for (int i = 1; i <= T; i++) {
            number = new int[10];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                String string = st.nextToken();
                int tmp = 0;
                for (int k = 0; k < string.length(); k++) {
                    tmp += string.charAt(k) -'0';
                }
                number[j] = tmp;
            }
            Arrays.sort(number);
            System.out.println("#"+i+" "+number[9]+" "+number[0]);
        }

    }
}
