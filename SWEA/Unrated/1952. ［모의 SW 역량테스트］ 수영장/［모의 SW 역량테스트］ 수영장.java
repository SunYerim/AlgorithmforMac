import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 1. 각 달을 기준으로 dfs를 돌린다.
    // 2. 1일권, 1달권, 3개월권을 섞어서 이용권을 구매하는 경우는 조합을 생성해서 처리한다.
    //      - 백트레킹
    static int T, answer;
    static int[] rates;
    static int[] months;
    static boolean[] buyTickets;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            rates = new int[4];
            months = new int[13]; // 1부터 시작한다.
            buyTickets = new boolean[13];


            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                rates[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < 13; i++) {
                months[i] = Integer.parseInt(st.nextToken());
            }

            answer = rates[3]; // 일단 1년치로 초기화해둔다.

            dfs(1, 0);

            sb.append(answer).append("\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int month, int sum) {
        // 기저
        if (answer <= sum) return;

        if (month > 12) {
            answer = Math.min(answer, sum);
            return;
        }

        // 유도
        // 이번달 계획 없으면
        if (months[month] == 0) {
            dfs(month+1, sum);
        } else {
            // 1일권
            dfs(month+1, sum + rates[0] * months[month]);
            // 1달권
            dfs(month+1, sum + rates[1]);

            // 3달권
            dfs(month+3, sum + rates[2]);
        }
    }

}
