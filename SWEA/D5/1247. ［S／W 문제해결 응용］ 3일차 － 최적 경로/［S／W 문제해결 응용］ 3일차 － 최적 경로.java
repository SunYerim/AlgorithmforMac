import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int T, N;
    static Node company, home;
    static List<Node> customers;
    static boolean[] visited;
    static int minDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            customers = new ArrayList<>();
            visited = new boolean[N];
            for (int i = 0; i < N; i++)
                customers.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

            minDist = Integer.MAX_VALUE;
            dfs(company, 0, 0);

            System.out.println("#" + tc + " " + minDist);
        }
    }

    static void dfs(Node curr, int count, int distance) {
        if (distance >= minDist) return;

        if (count == N) {
            minDist = Math.min(minDist, distance + getDistance(curr, home));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(customers.get(i), count + 1, distance + getDistance(curr, customers.get(i)));
                visited[i] = false;
            }
        }
    }

    static int getDistance(Node n1, Node n2) {
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