/* logic
    1. *에는 어떠한 문자가 들어와도 상관이 없다.
    2. 가능한 조합 -> dfs*/
import java.util.*;
class Solution {
    String[] userIds;
    String[] bannedIds;
    boolean[] visited;
    HashSet<HashSet<String>> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;
        visited = new boolean[userIds.length];
        
        dfs(new HashSet<>(), 0);
        
        return result.size();
    }
    
    // 조합
    private void dfs(HashSet<String> set, int depth) {
        // 기저
        if (depth == bannedIds.length) {
            result.add(set);
            return;
        }
        
        for (int i = 0; i < userIds.length; i++) {
            // 이미 포함하고 있으면
            if (set.contains(userIds[i])) {
                continue;
            }
            
            if (check(userIds[i], bannedIds[depth])) {
                set.add(userIds[i]);
                dfs(new HashSet<>(set), depth + 1);
                set.remove(userIds[i]);
            }
        }
    }
    
    private boolean check(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) {
            return false;
        }
        
        boolean flag = true;
        
        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}