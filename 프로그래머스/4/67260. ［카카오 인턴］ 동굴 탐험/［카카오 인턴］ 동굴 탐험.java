/* logic
    1. 위상정렬 ...? + bfs인가...
        - 위상정렬 : 진입차수 0인것 추가 ..*/

import java.util.*;
class Solution {
    static int[] indegree;
    static boolean[] visited;
    static int[] before;
    static ArrayList<Integer>[] map;
    static ArrayList<Integer>[] link; // order 연결관계
    public boolean solution(int n, int[][] path, int[][] order) {
        indegree = new int[n]; // 진입차수
        visited = new boolean[n];
        map = new ArrayList[n];
        link = new ArrayList[n];
        before = new int[n]; // 이전노드 마킹
        // 입력받기
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
            link[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < path.length; i++) {
            map[path[i][0]].add(path[i][1]);
            map[path[i][1]].add(path[i][0]);
        }
        
        for (int i = 0; i < order.length; i++) {
            link[order[i][0]].add(order[i][1]);
            indegree[order[i][1]]++;
        }
        
        if (indegree[0] > 0) return false;
        Arrays.fill(before, -1);
        before[0] = 0;
        visited[0] = true;
        for (int i = 0; i < link[0].size(); i++) {
            indegree[link[0].get(i)]--;
        }
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < map[0].size(); i++) {
            int next = map[0].get(i);
            before[next] = 0;
            queue.add(next);
        }
        
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            
            if (indegree[tmp] > 0) continue;
            if (before[tmp] == -1 || !visited[before[tmp]]) continue;
            
            if (!visited[tmp]) {
                visited[tmp] = true;
                for (int i = 0; i < link[tmp].size(); i++) {
                    int next = link[tmp].get(i);
                    indegree[next]--;
                    if(indegree[next] == 0) {
                        queue.add(next);
                    }
                }
                for(int i=0; i<map[tmp].size(); i++){
                    int next = map[tmp].get(i);
                    if(before[tmp] == next) continue;
                    before[next] = tmp;
                    if(!visited[next] && indegree[next]==0){
                        queue.add(next);
                    }
                }
            }
            
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) return false;
        }
        return true;
        
        
    }
    
    
}