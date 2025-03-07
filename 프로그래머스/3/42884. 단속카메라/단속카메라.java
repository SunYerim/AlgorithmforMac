import java.util.*;

class Solution {
    static List<Node> nodes;
    public int solution(int[][] routes) {
        int answer = 0;
        int lastCameraPosition = Integer.MIN_VALUE;
        nodes = new ArrayList<>();
        
        for (int i = 0; i < routes.length; i++) {
            nodes.add(new Node(routes[i][0], routes[i][1]));
        }
        
        Collections.sort(nodes);
        
        // greedy
        for (Node node : nodes) {
            int start = node.start;
            int end = node.end;
            
            // 현재 차량이 기존 카메라로 커버되지 않는다면
            if (start > lastCameraPosition) {
                lastCameraPosition = end;
                answer++;
            }
        }
//         for (int i = 0; i < nodes.size(); i++) {
//             int start = nodes.get(i).start;
//             int end = nodes.get(i).end;
            
//             // 이미 설치되어있는지 확인
//             boolean alreadyInstalled = false;
//             for (int j = start; j <= end; j++) {
//                 if (cameras[j] == 1) {
//                     alreadyInstalled = true;
//                     break;
//                 }
//             }
            
//             if (alreadyInstalled) continue;
            
//             cameras[end] = 1;
//             answer++;
//         }
        
        
        return answer;
    }
    
    static class Node implements Comparable<Node>{
        int start, end;
        
        public Node (int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.end - o.end;
        }
    }
}