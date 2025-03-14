import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int T, n;
    static StringBuilder sb = new StringBuilder();
    static List<Node> nodes;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            nodes = new ArrayList<>();
            for (int j = 0; j < n + 2; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                nodes.add(new Node(a, b));
            }

            if (bfs()) sb.append("happy").append("\n");
            else sb.append("sad").append("\n");
        }

        System.out.print(sb.toString());

    }

    private static boolean bfs() {
        Queue<Integer> queue = new LinkedList<>();
        visited = new boolean[n+2];
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            Node curNode = nodes.get(cur);

            // 도착지라면
            if (cur == n + 1) return true;

            for (int i = 0; i < n + 2; i++) {
                if (!visited[i] && menhaten(curNode, nodes.get(i))) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    // menhaten
    private static boolean menhaten(Node a, Node b) {
        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y)) <= 1000;
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
