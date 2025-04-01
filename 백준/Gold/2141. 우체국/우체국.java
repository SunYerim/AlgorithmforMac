import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList<>();
        long total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            nodes.add(new Node(x, a));
            total += a;
        }

        Collections.sort(nodes);

        // 전체 사람수의 절반을 넘어서는 첫 위치
        long sum = 0;
        for (int i = 0; i < N; i++) {
            Node curr = nodes.get(i);
            sum += curr.a;
            if (sum >= (total+1) / 2) {
                System.out.println(curr.x);
                break;
            }
        }
    }

    static class Node implements Comparable<Node> {

        long x, a;

        public Node(long x, long a) {
            this.x = x;
            this.a = a;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.x - o.x);
        }
    }

}
