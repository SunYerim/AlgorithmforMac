import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int targetNum = Integer.parseInt(br.readLine());
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (num[i] == targetNum){
                ans++;
            }
        }
        System.out.println(ans);
    }
}