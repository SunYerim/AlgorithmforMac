import java.util.*;

class Solution {
    static int[] newRocks;
    static int answer;
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        // 새로운 배열
        newRocks = new int[rocks.length + 2];
        newRocks[0] = 0;
        newRocks[newRocks.length - 1] = distance;
        
        for (int i = 1; i < newRocks.length - 1; i++) {
            newRocks[i] = rocks[i - 1];
        }
        
        int start = 0;
        int end = distance;
        answer = Integer.MIN_VALUE;
        
        while (start <= end) {
            // 거리
            int mid = (start + end) / 2;
            int cnt = 0; // n번 제거해야함
            int before = 0; // 이전
            int minDis = distance; // 최소거리
            
            for (int i = 1; i < newRocks.length; i++) {
                int curr = newRocks[i];
                // 현재 위치 - 이전 위치 < mid -> 제거
                if (curr - before < mid) cnt++;
                else {
                    minDis = Math.min(minDis, curr - before);
                    before = curr;
                }
            }
            
            if (cnt <= n) {
                answer = Math.max(answer, minDis);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return answer;
    }
}