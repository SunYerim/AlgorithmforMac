import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int n, m;
    static int[] counts;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        counts = new int[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // m번 입력받기
        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[b].add(a);
        }

        // 그리고 각 노트북 돌면서 탐색 시작
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n+1];
            int tmp = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;

            int count = 0;
            while(!queue.isEmpty()){
                tmp = queue.poll();
                // 2차원 배열 y축 기준으로
                for (int next : graph[tmp]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                        count++;
                    }
                }
            }

            counts[i] = count;
        }

        int maxCount = Arrays.stream(counts).max().getAsInt();

        for (int i = 1; i <= n; i++) {
            if (maxCount == counts[i]) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());

}
}