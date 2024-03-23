import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SFT6247 {
    static int n, q;
    static int[] cars;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        cars = new int[n];
        ans = new int[q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cars[i] = Integer.parseInt(st.nextToken());
        }
        // 배열에 입력을 받은 후 sort처리한다.
        Arrays.sort(cars);

        for (int i = 0; i < q; i++) {
            int num = Integer.parseInt(br.readLine());
            // 로직을 수행한다.
            binarySerarch(num, i);

        }

        for (int i = 0; i < q; i++) {
            System.out.println(ans[i]);
        }

    }
    private static void binarySerarch(int target, int seq) {
        int start = 0;
        int end = cars.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (cars[mid] == target) {
                ans[seq] = mid * ((cars.length-1)-mid);
                return;
            }
            else if (cars[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

    }

}
