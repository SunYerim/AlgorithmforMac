import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, ans = 0;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new Node(a, b));
            ans = Math.max(ans, b);
        }

        while (!pq.isEmpty() && ans >= 0) {
            Node curr = pq.poll();
            if (curr.s < ans) {
                ans = curr.s - curr.t;
            } else {
                ans -= curr.t;
            }
        }

        System.out.println(ans < 0 ? -1 : ans);
    }

    static class Node implements Comparable<Node> {

        int t, s;

        public Node(int t, int s) {
            this.t = t;
            this.s = s;
        }

        @Override
        public int compareTo(Node o) {
            return o.s - this.s;
        }
    }

}
