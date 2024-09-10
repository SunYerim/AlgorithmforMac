import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2]; // 변환 횟수, 제거된 0의 갯수
        int transformCount = 0;
        int removedZeros = 0;
        
        while (!s.equals("1")) {
            int originalLength = s.length();
            s = s.replace("0", "");
            int newLength = s.length();
            removedZeros += (originalLength - newLength);
            
            s = Integer.toBinaryString(newLength);
            
            transformCount++;
        }
        answer[0] = transformCount;
        answer[1] = removedZeros;
        return answer;
    }
}