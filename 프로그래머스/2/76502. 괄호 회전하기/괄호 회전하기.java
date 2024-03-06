/* logic
    1. Queue나 Deque로 괄호 회전을 구현하고
    // 횟수는 0 ~ 문자열의 길이만큼
    2. 한 번 옮기고 isValid 메서드로 올바른 괄호 문자열인지 판단한다. -> stack 활용 ==> 여는 괄호 나올때마다 넣고, 닫히는 괄호 나오면 peek해서 짝이 맞는지 확인*/
import java.util.*;

class Solution {
    static int count = 0;
    public int solution(String s) {
        Queue<Character> queue = new LinkedList<>();
        // 넣는다.
        for (int i = 0; i < s.length(); i++) {
            queue.add(s.charAt(i));
        }
        
        // 회전 안 시켰을때 한 번 판단하고
        
        if (isValid(toString(queue)))
            count++;
    
        // 1부터 문자열 길이만큼 회전시키고, 올바른 문자열이면 count++;
        for (int i = 1; i < s.length(); i++) {
            // 앞에꺼 빼서 뒤로 넣는다.
            char tmp2 = queue.poll();
            queue.offer(tmp2);
            // 문자열 변환하고
             
            
            if (isValid(toString(queue))) 
            {
                count++;
            }
        }
        
        
        return count;
    }
    
    // 올바른 문자열인지 확인
    private static boolean isValid(String tmp) {
        Stack<Character> stack = new Stack<>();
        // 돌면서 여는 문자열이면 넣고
        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.charAt(i) == '[' || tmp.charAt(i) == '{' || tmp.charAt(i) == '(') {
                // 넣는다.
                stack.push(tmp.charAt(i));
            }
            // 닫는 괄호 만나면 있는 거 뽑는다. -> 이때 스택 비어있으면 false 반환
            else if (tmp.charAt(i) == ']' || tmp.charAt(i) == '}' || tmp.charAt(i) == ')') {
                if (stack.size() == 0){
                    return false;
                }
                // 그리고 짝이 맞을때만 return true 시켜야함.
                char peekChar = stack.peek();
                if ((tmp.charAt(i) == ']' && peekChar == '[') || (tmp.charAt(i) == '}' && peekChar == '{') || (tmp.charAt(i) == ')' && peekChar == '(')) {
                    stack.pop();
                    
                } else {
                    return false;
                }
            }
            
        }     
        return stack.isEmpty();
        
    }
    
    // string으로 합쳐주는 메서드
    private static String toString(Queue <Character> list) {
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}