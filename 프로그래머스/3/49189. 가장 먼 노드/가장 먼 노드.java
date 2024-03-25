import java.util.*;

class Solution {
    // 연결관계
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int[] distance;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        // 초기화
        for (int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }
        visited = new boolean[n+1];
        distance = new int[n+1];
        
        // 간선 정보
        for (int[] edges : edge) {
            int a = edges[0];
            int b = edges[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        // bfs이용하여 각 노드로부터 최단거리
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); // 시작노드가 1번임
        visited[1] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : list.get(curr)) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[curr]+1;
                    queue.offer(next);
                }
            }
        }
        
        // 가장 거리가 먼 노드 찾는다.
        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            maxDistance = Math.max(maxDistance, distance[i]);
        }
        
        for (int i = 1; i <= n; i++) {
            if (distance[i] == maxDistance) {
                answer++;
            }
        }
        return answer;
    }
}