import java.util.*;
class Solution {
    static int[] parents;
    static ArrayList<Node> islands;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        
        // cost에 따른 가중치 정렬
        Arrays.sort(costs, (int[] c1, int[] c2) -> c1[2] - c2[2]);
        
        // 초기화
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            
            int fromParent = find(from);
            int toParent = find(to);
            
            // 만약 같으면
            if (fromParent == toParent) continue;
            
            answer += cost;
            parents[toParent] = fromParent;
        }
        return answer;
    }
    
    private static int find(int x) {
        if (parents[x] == x) return x;
        return (parents[x] = find(parents[x]));
    }
    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a != b) {
            parents[b] = a;
        }
    }
    
    
    static class Node implements Comparable<Node> {
        private int from, to, cost;
        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}