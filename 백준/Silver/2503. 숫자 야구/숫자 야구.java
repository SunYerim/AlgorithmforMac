import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. 123 ~ 987 배열을 생성해서 스트라이크, 볼 조건이랑 성립하면 해당 배열 값 +1 처리.
    2. arr[i] 가 N이랑 같으면 정답 후보에 들어간다.*/
public class Main {
    static int N, strikeCnt, ballCnt, passCnt, cnt;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[1000];

        for (int i = 123; i <= 987; i++) {
            String str = Integer.toString(i);

            // 미리 조건에 부합하지 않는 건 거른다.
            if (str.charAt(0) == str.charAt(1) || str.charAt(1) == str.charAt(2) || str.charAt(2) == str.charAt(0)
                || str.charAt(0) == '0' || str.charAt(1) == '0' || str.charAt(2) == '0') {
                numbers[i] = -1;
            }
        }
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            isValid(num, strike, ball);

        }

        for (int i = 123; i <= 987; i++) {
            if (numbers[i] == N)
                cnt++;
        }
        System.out.println(cnt);


    }

    private static void isValid(int num, int strike, int ball) {
        // num이 strike와 ball을 만족하는지
        // 123 ~ 987까지 범위 대상이다.
        for (int i = 123; i <= 987; i++) {
            if (numbers[i] == -1) continue;
            strikeCnt = 0;
            ballCnt = 0;


            // 스트라이크인 경우
            String strIdx = Integer.toString(i);
            String ans = Integer.toString(num);
            for (int j = 0; j < 3; j++) {
                // 숫자와 자리까지 똑같으면 ++
                if (ans.charAt(j) == strIdx.charAt(j))
                    strikeCnt++;
            }

            // 볼인 경우 -> 자릿수 다르게
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (ans.charAt(j) == strIdx.charAt(k)) {
                        if (j != k) {
                            ballCnt++;
                        }
                    }

                }
            }
//            System.out.println(strikeCnt +" : "+ballCnt);

            if (strikeCnt == strike && ballCnt == ball) {
                numbers[i]++;
            }

        }

    }
}