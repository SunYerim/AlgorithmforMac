import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1208 {
    static int dump;
    static int[] boxes;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int T = 1; T <= 10; T++) {
            dump = Integer.parseInt(br.readLine());
            boxes = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= 100; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                boxes[i] = tmp; // sort하기 위함.
            }

            for (int i = 1; i <= dump; i++) {
                Arrays.sort(boxes);
                boxes[1]++;
                boxes[100]--;
            }
            Arrays.sort(boxes);
            int result = boxes[100] - boxes[1];
            System.out.println("#"+T+" "+result);
        }

    }
}
