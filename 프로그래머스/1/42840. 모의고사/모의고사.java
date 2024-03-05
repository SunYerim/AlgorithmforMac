import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] person1 = {1, 2, 3, 4, 5}; 
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int scores1 = 0; // 1번
        int scores2 = 0; // 2번
        int scores3 = 0; // 3번
        for (int i = 0; i < answers.length; i++) {
            if(answers[i] == person1[(i%person1.length)])
                scores1++;
            if (answers[i] == person2[(i%person2.length)])
                scores2++;
            if (answers[i] == person3[(i%person3.length)])
                scores3++;
        }
        
        int[] tmp = {scores1, scores2, scores3};
        int[] answer = new int[3];
        // max값 뽑아서
        // 해당 인덱스 값 answer에 추가해서 return
        int maxScore = Math.max(scores1, scores2);
        int maxScore2 = Math.max(maxScore, scores3);
        
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            if (maxScore2 == tmp[i]) {
                answer[i] = i+1;
                if (answer[i] !=0)
                    idx++;
            }
        }
        
    
        int[] result = new int[idx];
        
        int idx2 = 0;
        // 0이 아닌 요소만 배열에 넣는다.
        for (int i = 0; i < 3; i++) {
            if (answer[i] > 0) {
                result[idx2++] = answer[i];
            }
        }
            

        System.out.println(Arrays.toString(result));
        return result;
    }
}