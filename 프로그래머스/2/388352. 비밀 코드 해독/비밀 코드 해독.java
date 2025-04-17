import java.util.*;

// 조합
// 조건 체크
class Solution {
    private static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        answer = 0;
        dfs(1, n, 0, new ArrayList<>(), q, ans);
        return answer;
    }
    
    public static void dfs(int start, int n, int count, List<Integer> current, int[][] q, int[] ans) {
        // 기저
        if (count == 5) {
            checkAns(current, q, ans);
            return;
        }
        
        // 유도
        for (int i = start; i <= n; i++) {
            current.add(i);
            dfs(i + 1, n, count + 1, current, q, ans);
            current.remove(current.size() - 1);
        }
        
    }
    
    public static void checkAns(List<Integer> current, int[][] q, int[] ans) {
        Set<Integer> set = new HashSet<>(current);
        
        for (int i = 0; i < ans.length; i++) {
            int[] curr = q[i];
            int cnt = 0;
            
            for (int c : curr) {
                if (set.contains(c)) cnt++;
            }
            
            if (cnt != ans[i]) return;
        }
        answer++;
    }
}