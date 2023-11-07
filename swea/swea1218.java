import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea1218 {
    public static void main(String[] args) throws IOException {
        // stack을 사용한다.
        // stack에 push 할 때는 여는 것만 넣고 닫는 괄호가 나올때마다 stack에서 pop한 요소와 짝이 맞는지 확인한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //총 10개의 테스트 케이스
        for (int i = 1; i <= 10; i++) {
            int count = Integer.parseInt(br.readLine());
            String input = br.readLine();

            Stack<Character> stack = new Stack<>();

                boolean isValid = true;
                for (char ch : input.toCharArray()) {
                    if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == ']' || ch == '}' || ch == '>') {
                        if (stack.isEmpty()) {
                            isValid = false;
                            break;
                        }
                        char top = stack.pop();
                        if (!((top == '(' && ch == ')') ||
                                (top == '[' && ch == ']') ||
                                (top == '{' && ch == '}') ||
                                (top == '<' && ch == '>'))) {
                            isValid = false;
                            break;
                        }

                }
            }
            if (isValid && stack.isEmpty()) {
                System.out.println("#" + i + " " + "1");
            } else {
                System.out.println("#" + i + " " + "0");
            }

        }

    }
}
