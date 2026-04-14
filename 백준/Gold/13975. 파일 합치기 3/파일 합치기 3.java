import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int j = 0; j < K; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();

                long total = a + b;
                ans += total;
                pq.offer(total);
            }
            sb.append(ans).append("\n");
        }

        System.out.print(sb.toString());
        
    }

}
