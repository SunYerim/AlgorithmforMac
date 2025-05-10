// pq -> 시간초과
import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int len = cores.length;
        
        if (n <= len) return n;
        
        // bs
        int start = 1;
        int end = 10000 * n;
        int time = 0;
        int work = 0;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            int cnt = calc(mid, cores);
            
            if (cnt >= n) {
                end = mid - 1;
                time = mid;
                work = cnt;
            } else {
                start = mid + 1;
            }
        }
        
        work -= n; // 0h
        
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (work == 0) {
                    answer = i + 1;
                    break;
                }
                work--;
            }
        }
        return answer;
    }
    
    public int calc(int mid, int[] cores) {
        int cnt = cores.length;
        
        for (int i = 0; i < cores.length; i++) {
            cnt += mid/cores[i];
        }
        return cnt;
    }
}