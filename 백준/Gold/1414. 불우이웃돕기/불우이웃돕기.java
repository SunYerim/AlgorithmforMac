import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[] parents;
    static ArrayList<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int total = 0;
        map = new int[n+1][n+1];
        parents = new int[n+1];
        list = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            String tmp = br.readLine();
            for (int j = 1; j <= n; j++) {
                char curr = tmp.charAt(j-1);
                int len = 0;
                if (curr >= 'a' && curr <= 'z') {
                    len = curr-'a'+1;
                    //list.add(new Node(curr-'a'+1, i, j));
                } else if (curr >= 'A' && curr <= 'Z') {
                    len = curr-'A'+27;
                    //list.add(new Node(curr-'A'+27, i, j));
                }
                if (len == 0) continue;

                list.add(new Node(len, i, j));
                total += len;
            }

        }

        Collections.sort(list);

        // union - find
        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i).start;
            int b = list.get(i).end;
            int len = list.get(i).len;

            int pa = find(a);
            int pb = find(b);

            if (pa != pb) {
                union(pa, pb);
                total -= len;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (find(i) != 1) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(total);
    }

    private static int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    static class Node implements Comparable<Node>{
        private int len, start, end;
        public Node(int len, int start, int end) {
            this.len = len;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }
}