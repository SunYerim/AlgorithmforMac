import java.util.*;

class Solution {
    static double[] areas, answer;
    static List<int[]> heights;
    public double[] solution(int k, int[][] ranges) {
        answer = new double[ranges.length];
        heights = new ArrayList<>();
    
        // 1. 입력된 수 1이 될때까지 명령 수행
        heights.add(new int[]{0, k});
        int idx = 1;
        while (k > 1) {
            if (k == 1) {
                heights.add(new int[]{idx, k});
                break;
            }
            
            // 짝수일때
            if (k % 2 == 0) {
                k /= 2;
                heights.add(new int[]{idx, k});
            } else {
                k = (k * 3) + 1;
                heights.add(new int[]{idx, k});
            }
            idx++;
        }
        
        // 2. 각 구간별로 넓이 계산
        areas = new double[heights.size() + 1];
        for (int i = 1; i < heights.size(); i++) {
            int[] curr = heights.get(i);
            int[] prev = heights.get(i-1);
            areas[i] = calculateArea(curr[1], prev[1]);
        }
        
        // return
        for (int i = 0; i < ranges.length; i++) {
            int[] curr = ranges[i];
            int start = curr[0];
            int end = (idx - 1) + curr[1];
            answer[i] = addArea(start, end);
        }
        return answer;
    }
    
    public static double addArea(int start, int end) {
        double ans = 0;
        // 똑같은 경우
        if (start == end) ans = 0;
        else if (start > end) ans = -1;
        else {
            for (int i = start + 1; i <= end; i++) {
                ans += areas[i];
            }
        }
        
        return ans;
    }
    
    public static double calculateArea(int b, int d) {
        // 사다리꼴
        return ((double)(b + d) / 2);
    }
}