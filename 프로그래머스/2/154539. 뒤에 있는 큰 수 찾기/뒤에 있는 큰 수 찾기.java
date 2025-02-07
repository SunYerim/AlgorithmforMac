import java.util.*;

class Solution {
    static int[] answer;
    static Stack<Integer> stack;
    public int[] solution(int[] numbers) {
        answer = new int[numbers.length];
        stack = new Stack<>();
        stack.push(0); // 첫번째 number idx
        
        // answer[answer.length-1] = -1;
        
        for (int i = 1; i < answer.length; i++) {
            // 스택이 비어있지 않으면서, 현재 스택이 바라보고 있는 값 > number => 뒤에 있는 큰 수
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        return answer;
    }
    
    // private static int calculate(int[] numbers, int idx) {
    //     int number = numbers[idx]; // 기준 위치
    //     int ans = -1;
    //     for (int i = idx + 1; i < answer.length; i++) {
    //         if (number < numbers[i]) {
    //             ans = numbers[i];
    //             break;
    //         }
    //     }
    //     return ans;
    // }
}