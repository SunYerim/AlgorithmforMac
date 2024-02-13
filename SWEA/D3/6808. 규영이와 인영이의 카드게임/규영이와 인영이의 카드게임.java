import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int T, win, lose, N = 9;
    static int[] input = new int[19]; // 0 dummy, 규영이의 카드 입력을 여기에 표시, => 인영이의 카드를 구성
    static int[] guCard = new int[9]; // 0
    static int[] inCard = new int[9]; // 0 dummy <= 다양한 순열
    static int[] tgt = new int[9]; // inCard => tgt 순열 완탐 결과 저장
//    static boolean[] select = new boolean[N];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 변수 초기화
            win = lose = 0;
            Arrays.fill(input, 0);
//            Arrays.fill(select, false);

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 규영이 카드 처리
            int num = 0;
            for (int i = 0; i < N; i++) {
                num = Integer.parseInt(st.nextToken());
                input[num] = 1;
                guCard[i] = num;
            }

            // 인영이 카드 처리
            num = 0;
            for (int i = 1; i <= 18; i++) {
                if (input[i] == 0) {
                    inCard[num++] = i;
//                    num++;
                }
            }

            perm(0, 0);

            sb.append("#").append(t).append(" ").append(win).append(" ").append(lose).append("\n");
        }
        System.out.println(sb);
    }

    static void perm(int tgtIdx, int mask) { // mask는 정수이지만, bit로 표현하면 어느 자리가 선택/비선택 표현
        // 기저 조건
        if (tgtIdx == N) {
            // 순열이 하나 완성
            check();
            return;
        }

        // for문을 이용한 순열 구현
        for (int i = 0; i < N; i++) {
            // 이전에 사용된 카드(i)는 거른다.
//            if (select[i]) continue;
            if ( (mask & 1 << i) != 0 ) continue;

            tgt[tgtIdx] = inCard[i];
            perm(tgtIdx + 1, mask | 1 << i);

        }
    }

    static void check() {
        int guSum = 0;
        int inSum = 0;

        // 카드 전체 비교
        for (int i = 0; i < N; i++) {
            if (guCard[i] > tgt[i]) guSum += guCard[i] + tgt[i];
            else inSum += guCard[i] + tgt[i];
        }
        if (guSum > inSum) win++;
        else if (guSum < inSum) lose++;
    }
}