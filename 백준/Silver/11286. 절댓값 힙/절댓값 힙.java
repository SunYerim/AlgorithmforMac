import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N;
    static PriorityQueue<Node> pq;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                pq.add(new Node(num, Math.abs(num)));
            } else {
                if (pq.isEmpty()) sb.append(0).append("\n");
                else {
                    Node curr = pq.poll();
                    sb.append(curr.num).append("\n");
                }
            }
        }

        System.out.print(sb.toString());
    }

    // 객체 생성
    static class Node implements Comparable<Node>{
        int num, absNum;
        public Node(int num, int absNum) {
            this.num = num; // 실제값
            this.absNum = absNum; // 절댓값
        }

        @Override
        public int compareTo(Node o) {
            if (this.absNum == o.absNum) {
                return this.num - o.num;
            }
            return this.absNum - o.absNum;
        }
    }

}
