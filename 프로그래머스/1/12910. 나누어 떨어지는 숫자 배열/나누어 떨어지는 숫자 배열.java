import java.util.*;

class Solution {
    static ArrayList<Integer> numList;
    static int[] answer;
    public int[] solution(int[] arr, int divisor) {
        numList = new ArrayList<>();
        for (int num : arr) {
            if (num % divisor == 0) {
                numList.add(num);
            }
        }
        
        if (numList.size() == 0) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[numList.size()];
            // 넣기
            for (int i = 0; i < numList.size(); i++) {
                answer[i] = numList.get(i);
            }
            Arrays.sort(answer);
        }
        

        return answer;
    }
}