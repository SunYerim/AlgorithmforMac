import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    static int[] answer, dist;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        answer = new int[sources.length];
        // 연결관계 표시
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        bfs(n, destination);
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
    
    public static void bfs(int n, int destination) {
        // dest -> 모든 지점
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        dist[destination] = 0;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int next : graph.get(curr)) {
                // 방문 안 했으면
                if (dist[next] == -1) {
                    dist[next] = dist[curr] + 1;
                    queue.add(next);
                }
            }
        }
    }
}