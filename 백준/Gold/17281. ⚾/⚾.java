import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] ining; // 각 이닝별로 선수들의 타격 결과를 받아오는 배열
    static int n, ans;
    static int[] order; // 1~9 타순이 저장되어있음.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ining = new int[51][10];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < 10; j++) {
                ining[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        order = new int[11];
        for (int i = 1; i < 10; i++) {
            order[i] = i; // 초기화
        }

        // 타순을 찾고 -> 득점을 구한다. 조합
        permutation(1, 9);

        System.out.println(ans);
    }

    // 점수 계산
    public static int calculate() {
        // out 횟수, cur 현위치, point 점수
        int out, cur = 1, point = 0;
        for (int i = 0; i < n; i++) {
            out = 0;
            boolean[] base = {false, false, false, false};
            while (out < 3) {
                int b = order[cur];
                base[0] = true;
                if (ining[i][b] == 0) out++; // 아웃 증가
                else {
                    //3루 주자부터 타자까지 전개
                    for (int j = 3; j >= 0; j--) {
                        if (base[j] == false) continue; // 없으면
                        int k = ining[i][b] + j; // 베이스 번호 + 타격 결과
                        if (k > 3) point++; // 홈으로 들어옴
                        else {
                            base[k] = true;
                        }
                        base[j] = false; //원래 있던 곳은 이제 사람 없음
                    }
                }
                cur++;
                if (cur==10)
                    cur = 1;// 9 -> 1
            }
        }
        return point;
    }

    // 타순을 찾고
    private static void permutation(int start, int end) {
        // 기저
        if (start == end) {
            if (order[4] != 1) return; // 4번 선수가 1등이 아닌 경우는 건너뛴다.
            int tmp = calculate();
            if (tmp > ans) ans = tmp; // tmp 점수가 더 높으면 갱신한다.
            return;
        }
        // 유도
        for (int i = start; i <= end; i++) {
            // swap (order start, order i)
            swap(start, i);
            // permutaion(start+1, end);
            permutation(start+1, end);
            // swap(order start, order i);
            swap(start, i);
        }

    }

    private static void swap(int a, int b) {
        int tmp = order[a];
        order[a] = order[b];
        order[b] = tmp;
    }
}