import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        // 기지국 설치 기준 구간별로 계산
        
        for (int i = 0; i <= stations.length; i++) {
            int len = 0;
            // 시작
            if (i == 0) {
                // System.out.println("0 :" + (stations[i] - 1 - w));
                len = stations[i] - 1 - w;
            } else if (i == stations.length) {
                // System.out.println("final " + (n - stations[i-1] - w));
                len = n - stations[i-1] - w;
            } else {
                // System.out.println("else " + (stations[i] - stations[i-1] - (w * 2) - 1));
                len = stations[i] - stations[i-1] - (w * 2) - 1;
            }
            
            if (len >= 0) {
                int num = (len / (w * 2 + 1));
                int remain = (len % (w * 2 + 1));
            
                answer += num;
                if (remain > 0) answer++;
            }
        }
        return answer;
    }
}