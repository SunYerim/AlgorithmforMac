import java.util.*;

/* logic
    1. (가로 >= 세로) yellow를 1부터 값의 절반까지 나누면서 나머지가 0이 될때, 몫: 가로, 나누는 수 : 세로
    2. 이때 나오는 가로, 세로 값이 조건에 부합하는지 확인 => (가로)*2 + 세로*2 + 4 == brown
    3. 부합하면 바로 answer return*/
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int garo1 = 0;
        int sero1 = 0;
        // yellow가 1인 경우
        if (yellow == 1) {
            sero1 = 1;
            garo1 = 1;
            if (isCorrect(garo1, sero1, brown)) {
                answer[0] = garo1+2;
                answer[1] = sero1+2;

            }
        } else if (yellow >= 2) {
            // 2 이상인 경우
            for (int i = 1; i <= yellow/2; i++) {
                if (yellow % i == 0) {
                    sero1 = i;
                    garo1 = yellow / i;
                    if (isCorrect(garo1, sero1, brown)) {
                        answer[0] = garo1+2;
                        answer[1] = sero1+2;
                        break;
                    }
                    
                }
            }
        }
        
        
        return answer;
    }
    
    private static boolean isCorrect(int garo, int sero, int brown) {
        if ((garo * 2) + (sero * 2) + 4 == brown){ 
            return true;
        }
            
        return false;
    }
}