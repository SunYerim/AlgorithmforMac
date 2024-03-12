import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, m, count;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n+1];

        // 연결관계 표시
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        wedding(1, 0);

        for (int i = 2; i <= n; i++) {
            if (visited[i])
                count++;
        }
        System.out.println(count);


    }

    // depth가 2일때까지가 최대 실행할 수 있다.
    private static void wedding(int x, int depth) {
        if (depth == 2)
            return;
        for (int i = 0; i < graph.get(x).size(); i++) {
            int next = graph.get(x).get(i);
            visited[next] = true;
            wedding(next, depth+1);
        }


    }
}