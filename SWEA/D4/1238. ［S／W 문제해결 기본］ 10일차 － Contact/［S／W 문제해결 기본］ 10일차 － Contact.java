import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int dataLen, start, size = 101;
    static int[][] graph; // 연락망 관계 나타내기 위함
    static boolean[] visited; // 연락받은 유무를 체크하기 위함
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph = new int[size][size];
            visited = new boolean[size];
            queue = new LinkedList<>();

            dataLen = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < dataLen/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from][to] = 1;
            }

            int ans = bfs(start);
            System.out.println("#"+tc+" "+ans);

        }

    }

    private static int bfs(int x) {
        queue.offer(x);
        visited[x] = true; // 방문 처리
        int maxNum = 0;
        ArrayList<Integer> list = new ArrayList<>();

        // 큐가 비지 않는 동안
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            maxNum = 0;
            for (int t = 0; t < qSize; t++) {
                int curr = queue.poll();
                for (int i = 1; i < size; i++) {
                    // cur -> i 가능하고 연락을 아직 안 했다면
                    if (graph[curr][i] == 1 && !visited[i]) {
                        queue.offer(i);
                        visited[i] = true;
                        maxNum = Math.max(maxNum, i);
                    }
                }
            }
            list.add(maxNum);
        }
        return list.get(list.size()-2);
    }
}