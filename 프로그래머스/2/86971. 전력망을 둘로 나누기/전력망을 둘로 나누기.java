import java.util.*;

class Solution {
    static int minDiff = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;
    // static ArrayList<Integer> list1;
    // static ArrayList<Integer> list2;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // graph에 연결관계 
        for (int i = 0; i < wires.length; i++) {
            int num1 = wires[i][0];
            int num2 = wires[i][1];
            graph.get(num1).add(num2);
            graph.get(num2).add(num1);
        }
        
        // 2개로 나눌 list
        // list1 = new ArrayList<>();
        // list2 = new ArrayList<>();
        
        // 간선을 하나씩 제거하면서 전력망 크기를 계산
        for (int[] wire : wires) {
            // 제거
            graph.get(wire[0]).remove((Integer) wire[1]);
            graph.get(wire[1]).remove((Integer) wire[0]);
            
            // 전력망의 크기를 계산
            visited = new boolean[n+1];
            int size1 = dfs(n, wire[0]);
            int size2 = n - size1;
            
            answer = Math.min(answer, Math.abs(size1 - size2));
            
            // 다시 복구
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }
//         // 실행
//         dfs(n, 1);
        
//         answer = minDiff == Integer.MAX_VALUE ? 0 : minDiff;
        
        return answer;
    }
    
    // bfs -> 나눈 전력망이 하나가 맞는지
//     private static boolean bfs(int n, ArrayList<Integer> arr) {
//         if (arr.isEmpty()) return false;
        
//         Queue<Integer> queue = new LinkedList<>();
//         boolean[] visited = new boolean[n+1];
        
//         visited[arr.get(0)] = true;
//         queue.offer(arr.get(0));
        
//         int cnt = 1;
        
//         while (!queue.isEmpty()) {
//             int curr = queue.poll();
            
//             // 연결된 요소를 확인합니다.
//             for (int i : graph.get(curr)) {
//                 if (!visited[i] && arr.contains(i)) {
//                     visited[i] = true;
//                     queue.offer(i);
//                     cnt++;
//                 }
//             }
//         }
        
//         if (cnt == arr.size()) {
//             return true;
//         }
        
//         return false;
        
//     }
    
    // dfs -> 전력망 나눔
    private static int dfs(int n, int depth) {
        visited[depth] = true;
        int cnt = 1;
        
        for (int neighbor : graph.get(depth)) {
            if (!visited[neighbor]) {
                cnt += dfs(n, neighbor);
            }
        }
        
        return cnt;
//         // 기저
//         if (depth == n + 1) {
//             if (bfs(n, list1) && bfs(n, list2)) {
//                 // 올바르게 나누어졌을때
//                 int total1 = list1.size();
//                 int total2 = list2.size();
                
//                 minDiff = Math.min(minDiff, Math.abs(total1 - total2));
                
//             }
            
//             return;
//         }
        
//         // 유도
//         list1.add(depth);
//         dfs(n, depth + 1);
//         list1.remove(list1.size() - 1);
        
//         list2.add(depth);
//         dfs(n, depth + 1);
//         list2.remove(list2.size() - 1);
//     }
    }
    
}