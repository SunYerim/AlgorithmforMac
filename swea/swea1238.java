import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea1238 {
    // 인접행렬로
    static int l; //데이터의 길이
    static int start; // 시작점
    static int size = 100 + 1; // 연락인원
    static int[][] graph; // 연락망
    static int[] visited; // 연락받은 인원 체크
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph = new int[size][size];
            visited = new int[size];
            q = new LinkedList<>();

            l = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            // 연락망 입력
            for (int i = 0; i < l/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = 1;
            }

            int answer = bfs(start);
            System.out.println("#"+T+" "+answer);
        }

    }


    public static int bfs(int x) {
        q.offer(x);
        visited[x] = 1;
        int max = 0;
        ArrayList<Integer> list = new ArrayList<>(); // max값 저장

        while (!q.isEmpty()) {
            int queueSize = q.size();
            max = 0;

            for (int t = 0; t < queueSize; t++) {
                int cur = q.poll();
                for (int i = 1; i < size; i++) {
                    if (graph[cur][i] == 1 && visited[i] == 0) {
                        q.offer(i);
                        visited[i] = 1;
                        max = Math.max(max, i);
                    }
                }
            }
            list.add(max);
        }
        return list.get(list.size()-2);
    }


}
