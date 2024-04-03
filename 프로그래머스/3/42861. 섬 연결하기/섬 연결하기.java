import java.util.*;
class Solution {
    static int[] parents;
    static ArrayList<Node> islands;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n];
        islands = new ArrayList<>();
        
        // 초기화
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            islands.add(new Node(from, to, cost));
        }
        
        Collections.sort(islands);
        
        for (int i = 0; i < islands.size(); i++) {
            int a = islands.get(i).from;
            int b = islands.get(i).to;
            int cost = islands.get(i).cost;
            
            // 사이클이 발생하지 않는 경우만
            if (find(a) != find(b)) {
                union(a, b);
                answer += cost;
            }
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
        
        if (a < b)
            parents[b] = a;
        else
            parents[a] = b;
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