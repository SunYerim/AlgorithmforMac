import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        int totalDistance = 0;
        for (int i = 0; i < N - 1; i++) {
            totalDistance += getDistance(nodes[i], nodes[i + 1]);
        }

        // 하나씩 건너뛰면서 비교
        int maxSkipSave = 0;

        for (int i = 1; i < N - 1; i++) {
            int originalDistance =
                getDistance(nodes[i - 1], nodes[i]) + getDistance(nodes[i], nodes[i + 1]);

            int skipDistance = getDistance(nodes[i - 1], nodes[i + 1]);

            int saveDistance = originalDistance - skipDistance;

            maxSkipSave = Math.max(maxSkipSave, saveDistance);
        }

        System.out.println(totalDistance - maxSkipSave);
    }

    public static int getDistance(Node n1, Node n2) {
        return Math.abs(n1.x - n2.x) + Math.abs(n1.y - n2.y);
    }

    static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
