import java.util.*;

public class validParentheses {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                if (st.isEmpty() || st.pop() != '(') {
                    answer = false;
                    break;
                }
            }
        }

        if (!st.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}
