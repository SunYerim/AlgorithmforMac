import java.util.*;
// 단일 for & 정렬

class Solution {
    public int solution(int[][] scores) {
        int wonA = scores[0][0]; // 근무 태도 점수
        int wonB = scores[0][1]; // 동료 평가 점수
        
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1]; // 오름차순
            return o2[0] - o1[0]; // 내림차순
        });
        
        int maxScore = scores[0][1];
        
        // 제외 대상 고르기
        for (int i = 1; i < scores.length; i++) {
            if (scores[i][1] < maxScore) {
                // 원효 점수라면 바로 -1
                if (scores[i][0] == wonA && scores[i][1] == wonB) {
                    return -1;
                }
                
                scores[i][0] = -1;
                scores[i][1] = -1;
            } else {
                maxScore = scores[i][1];
            }
        }
        
        // 합산 점수 정렬로 완호 등수 return
        Arrays.sort(scores, (o1, o2) -> {
            return (o2[0] + o2[1]) - (o1[0] + o1[1]);
        });
        
        int answer = 1;
        
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][0] + scores[i][1] > wonA + wonB) answer++;
            else break;
        }
        
        return answer;
    }
}