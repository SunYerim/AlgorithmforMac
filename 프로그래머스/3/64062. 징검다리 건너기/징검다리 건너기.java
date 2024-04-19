import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // 최대, 최솟값 갱신
        for (int stone : stones) {
            min = Math.min(min, stone);
            max = Math.max(max, stone);
        }
        
        // 이분탐색
        while (min < max) {
            int mid = (min + max + 1) / 2;
            
            // 조건 만족 메서드 
            if (isValid(mid, k, stones)) {
                min = mid;
            } else {
                max = mid - 1;
            }
        }
        return max;
    }
    
    private static boolean isValid(int num ,int k, int[] stones) {
        int count = 0;
        // 징검다리를 건너는 메서드
        // count가 k 이상이 되버리면 k칸 이상 이동을 못한다는 의미이므로 false처리
        for (int stone : stones) {
            if (stone - num < 0) {
                count++;
            } 
            // 이동 못 함
            else {
                count = 0;
            }
            
            if (count == k) return false;
        }
        return true;
    }
}