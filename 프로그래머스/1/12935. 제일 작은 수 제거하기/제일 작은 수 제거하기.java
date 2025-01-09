import java.util.*;

class Solution {
    static int[] answer;
    public int[] solution(int[] arr) {
    
        if (arr.length == 1) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[arr.length - 1];
            // 제일 작은 거 찾고
            // 나머지는 그대로
            int idx = 0;
            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < arr.length; i++) {
                if (minValue >= arr[i]) {
                    minValue = arr[i];
                    idx = i;
                }
            }
            
            int currIdx = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i == idx) continue;
                answer[currIdx++] = arr[i];
            }
            
        }
        return answer;
    }
}