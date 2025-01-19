import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken()); // 최소 갯수

        numbers = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터
        int left = 0, right = 0;
        int currentSum = 0;
        int minLen = Integer.MAX_VALUE;
        while (right < N) {
            // 오른쪽
            currentSum += numbers[right++];


            while (currentSum >= S) {
                minLen = Math.min(minLen, right - left);
//                System.out.println(minLen + " " + right + ", " + left);
                currentSum -= numbers[left++];
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minLen);
        }
    }

}