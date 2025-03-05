import java.util.*;

class Solution {
    static List<Node> nodes;
    static int answer;
    public int solution(int[] players, int m, int k) {
        answer = 0;
        nodes = new ArrayList<>();
        
        // players의 길이만큼
        for (int i = 0; i < players.length; i++) {
            // 가용되는 서버 시간 -1 및 상태 업데이트
            for (int j = 0; j < nodes.size(); j++) {
                nodes.get(j).time--;
                if (nodes.get(j).time == 0) nodes.get(j).possible = false;
            }
            
            if (players[i] == 0) continue;
            int count = players[i] / m; // 필요한 서버의 수
            // System.out.println(i + " ------ " + count);
            
            
            // 사용가능한 서버의 갯수
            int cnt = 0;
            for (int j = 0; j < nodes.size(); j++) {
                if (nodes.get(j).possible) cnt++;
            }
//             System.out.println("cnt " + cnt);
            
//             System.out.println("가용되는 서버 " + i + "------" + cnt);
            
            int newServer = Math.max(0, count - cnt); // 추가되어야하는 서버 갯수
            
            for (int j = 0; j < newServer; j++) {
                Node node = new Node(k, true);
                nodes.add(node);
            }
            
            answer += newServer;
            
            
        }
        return answer;
    }
    
    static class Node {
        int time;
        boolean possible;
        
        public Node(int time, boolean possible) {
            this.time = time;
            this.possible = possible;
        }
    }
}