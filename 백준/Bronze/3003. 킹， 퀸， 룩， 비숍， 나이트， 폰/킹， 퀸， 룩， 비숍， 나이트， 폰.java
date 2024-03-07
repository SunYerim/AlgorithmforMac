import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] tmp1;
    static int[] tmp2;
    static int[] ans = new int[]{1, 1, 2, 2, 2, 8};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        tmp1 = new int[6];
        tmp2 = new int[6];
        for (int i = 0; i < 6; i++) {
            tmp1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            tmp2[i] = ans[i]-tmp1[i];
        }

        for (int i = 0; i < 6; i++) {
            System.out.print(tmp2[i]+" ");
        }
    }
}