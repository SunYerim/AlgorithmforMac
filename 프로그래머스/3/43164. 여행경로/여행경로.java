// dfs , 순열 문제
import java.util.*;
class Solution {
    static boolean[] used;
    static ArrayList<String> list;
    static String[] answer;
    public String[] solution(String[][] tickets) {
        list = new ArrayList<>();
        used = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        
        Collections.sort(list);
    
        // 0번째 인덱스 list
        answer = list.get(0).split(" ");
        
        
        return answer;
    }
    
    private static void dfs(int depth, String now, String path, String[][] tickets)     {
        // 기저 -> ticket을 다 쓴 경우
        if (depth == tickets.length) {
            list.add(path);
            return;
        }
        // 유도
        for (int i = 0; i < used.length; i++) {
            // 방문하지 않고, 출발지와 동일
            if (!used[i] && now.equals(tickets[i][0])) {
                used[i] = true;
                dfs(depth+1, tickets[i][1], path + " " + tickets[i][1], tickets);
                used[i] = false;
            }
        }
    }
}