import java.util.*;

class Solution {
    // 각 자리에 들어가야 하는 수 dfs
    public long[] solution(int n, long k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        
        long[] answer = new long[n];
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        
        k--;
        
        for (int i = 0; i < n; i++) {
            factorial /= (n-i);
            int idx = (int)(k / factorial);
            answer[i] = nums.get(idx);
            nums.remove(idx);
            k %= factorial;
        }
        
        return answer;
    }
    
}