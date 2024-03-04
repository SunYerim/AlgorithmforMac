import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] scores;
    static int[] selected;
    static int total, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        scores = new int[10];
        selected = new int[10]; // 1~5번까지 뭐가

        for (int i = 0; i < 10; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);

        System.out.println(total);

    }

    private static void dfs(int cnt) {
        // 기저 조건
        if (cnt == 10){
            int sum=0;
            for(int i=0;i<10;i++) {
                if(scores[i]==selected[i])
                    sum++;
            }
            if (sum >= 5) {
                total++;
            }
            return;
        }

        // 유도 조건 -> 고르는 경우
        for (int i = 1; i <= 5; i++) {
            if (cnt >= 2)
                if (i == selected[cnt-1] && i == selected[cnt-2]) continue;
            selected[cnt] = i; // 고르고
            dfs(cnt+1);
//            selected[cnt] = 0; // 초기화
        }
    }
}