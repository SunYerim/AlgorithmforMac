import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 원소 개수
        M = Integer.parseInt(st.nextToken()); // 질문 개수

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        // 질문
        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            int ans = bs(num);
            sb.append(ans).append("\n");
        }

        System.out.print(sb.toString());

    }

    private static int bs(int num) {
        // num이 등장한 위치
        int start = 0;
        int end = arr.length - 1;
        int result = -1;

        while (start <= end) {
            int mid = (start + end) / 2;
//            System.out.println(mid);

            if (arr[mid] >= num) {
                if (arr[mid] == num) {
                    result = mid;
                }
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }

        return result;
    }

}
