import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N, dis = Integer.MIN_VALUE;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        int left = 0, right = 1; // 지점
        int num2 = arr[left]; // 시계
        int num1 = total - num2; // 반시계

        while (left < right && right < N) {
            dis = Math.max(dis, Math.min(num1, num2));

            if (num2 < num1) {
                num2 += arr[right];
                num1 -= arr[right];
                right++;
            } else {
                num2 -= arr[left];
                num1 += arr[left];
                left++;
            }
            
        }

        System.out.println(dis);


    }

}
