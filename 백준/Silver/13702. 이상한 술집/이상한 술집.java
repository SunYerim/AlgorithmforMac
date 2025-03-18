import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 주전자 개수
        K = Integer.parseInt(st.nextToken()); // 친구들 명수

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long start = 1;
        long end = arr[arr.length - 1];
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            // mid값 기준으로 막걸리 양 나눠서 나온 몫
            if (calc(mid)) {
                answer = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean calc(long mid) {
        long cnt = 0;

        for (int i = 0; i < N; i++) {
            cnt += (arr[i] / mid);
        }

        // k보다 크다면
        if (cnt >= K) return true;
        else return false;
    }

}
