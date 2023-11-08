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
    static int[][] graph; // 연락망(인접리스트)
    static boolean[] visited; // 연락받은 유무 체크
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph = new int[size][size];
            visited = new boolean[size];
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
        visited[x] = true; // 방문처리
        int max = 0; // 나중에 return하기 위함. 갱신되는 값
        ArrayList<Integer> list = new ArrayList<>(); // max값 저장 list

        while (!q.isEmpty()) {
            int queueSize = q.size();
            max = 0;

            for (int t = 0; t < queueSize; t++) {
                int cur = q.poll();
                for (int i = 1; i < size; i++) {
                    //cur -> i 가능하고 아직 연락 안 돌렸으면,
                    if (graph[cur][i] == 1 && visited[i] == false) {
                        q.offer(i);
                        visited[i] = true;
                        max = Math.max(max, i);
                    }
                }
            }
            list.add(max);
        }
        return list.get(list.size()-2);
    }


}
