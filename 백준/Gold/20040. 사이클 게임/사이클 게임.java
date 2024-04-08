import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* union-find*/
public class Main {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n];
        graph = new ArrayList<ArrayList<Integer>>();
        // 초기화
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // parents배열 자기 자신으로 초기화
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        int count = 0; // 사이클이 생겼을 경우 catch하기 위한 용도
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());
            // 사이클이 생기지 않으면
            if (find(one) != find(two) && !flag) {
                union(one, two);
                count++;
            }
            // 사이클이 딱 생겼다면
            else {
//                System.out.println(count+1);
                flag = true;
            }
        }

        if (!flag) {
            System.out.println(0);
        } else {
            System.out.println(count+1);
        }


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

    static class Node {
        private int from, to;
        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
}