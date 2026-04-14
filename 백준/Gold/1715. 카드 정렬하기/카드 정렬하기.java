import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        long totalCost = 0;

        while (pq.size() > 1) {
            long a = pq.poll();
            long b = pq.poll();

            long sum = a + b;
            totalCost += sum;
            pq.offer(sum);
        }

        System.out.println(totalCost);
        
    }

}
