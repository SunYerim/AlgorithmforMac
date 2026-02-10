import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<Homework> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        pq = new PriorityQueue<>();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Homework(d, w));
        }

        PriorityQueue<Integer> scorePq = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            Homework curr = pq.poll();
            scorePq.add(curr.w);

            // 현점수 pq에 담긴 과제수가 마감일보다 많아지면
            if (scorePq.size() > curr.d) {
                scorePq.poll();
            }
        }

        int total = 0;
        for (Integer s : scorePq) {
            total += s;
        }

        System.out.println(total);


    }

    static class Homework implements Comparable<Homework> {

        int d, w;

        public Homework(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Homework o) {
            if (this.d == o.d) {
                return this.w - o.w;
            }
            return this.d - o.d;
        }
    }

}
