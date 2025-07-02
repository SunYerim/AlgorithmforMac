import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long[] road, cost;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int total = 0;
        road = new long[N - 1];
        cost = new long[N];

        // 거리
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            road[i] = Long.parseLong(st.nextToken());
        }

        // 리터당 가격
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cost[i] = Long.parseLong(st.nextToken());
        }

        long totalCost = 0;
        long minCostPerLiter = cost[0];

        for (int i = 0; i < N - 1; i++) {
            // 주유할양
            if (cost[i] < minCostPerLiter) {
                minCostPerLiter = cost[i];
            }

            totalCost += (minCostPerLiter * road[i]);
        }

        System.out.println(totalCost);
    }

}
