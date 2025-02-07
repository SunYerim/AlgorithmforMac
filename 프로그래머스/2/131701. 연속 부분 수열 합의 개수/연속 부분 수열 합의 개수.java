import java.util.*;

class Solution {
    static int answer;
    static List<Integer> answers;
    static Set<Integer> numList = new HashSet<>();
    public int solution(int[] elements) {
        answer = 0;
        for (int i = 1; i <= elements.length; i++) {
            calculate(elements, i);
        }
        
        List<Integer> answers = new ArrayList<>(numList);
        
        return answers.size();
    }
    
    private static void calculate(int[] elements, int n) {
        // n만큼 요소 더 추가해서 계산
        int[] tmpArr = new int[elements.length + n];
        
        for (int i = 0; i < tmpArr.length; i++) {
            tmpArr[i] = elements[i % elements.length];
        }
        
        // 계산
        for (int i = 0; i < tmpArr.length - n + 1; i++) {
            int tmp = 0;
            for (int j = i; j < i + n; j++) {
                tmp += tmpArr[j];
            }
            // System.out.println(tmp);
            numList.add(tmp);
        }
        
    }
}