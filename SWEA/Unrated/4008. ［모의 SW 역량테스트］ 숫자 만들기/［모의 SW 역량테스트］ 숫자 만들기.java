import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* logic
    1. 연산자를 하나씩 추가하면서 재귀호출
    2. 기저조건 -> n-1개를 모두 추가한 후 수식 값 계산
    3. 최대, 최소 갱신
    4. 정답 출력*/
public class Solution {
    static int n, ans, minN, maxN;
    static int[] operator, numbers, makeNum;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            minN = Integer.MAX_VALUE;
            maxN = Integer.MIN_VALUE;
            n = Integer.parseInt(br.readLine());

            operator = new int[4];
            numbers = new int[n];
            makeNum = new int[n-1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                operator[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            check(0); // 로직 실행

            ans = maxN - minN;
            sb.append("#").append(tc).append(" ").append(ans).append('\n');
        }
        System.out.print(sb);
    }

    private static void check(int idx) {
        // 기저
        if (idx == n-1)
            calcNum();
        for (int i = 0; i < 4; i++) {
            // 연산자를 다 사용하면
            if (operator[i] == 0)
                continue;
            operator[i]--;
            makeNum[idx] = i;
            check(idx+1);
            operator[i]++;
        }
    }

    private static void calcNum() {
        int num = numbers[0];
        for (int i = 0; i < n-1; i++) {
            // +
            if (makeNum[i] == 0) {
                num += numbers[i+1];
            }
            // -
            else if (makeNum[i] == 1) {
                num -= numbers[i+1];
            }
            // *
            else if (makeNum[i] == 2) {
                num *= numbers[i+1];
            }
            // /
            else if (makeNum[i] == 3) {
                num /= numbers[i+1];
            }
        }
        if (num > maxN)
            maxN = num;
        if (num < minN)
            minN = num;
    }
}