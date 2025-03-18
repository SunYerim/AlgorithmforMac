import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static String S;
    static Stack<Character> stack;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();

        stack = new Stack<>();

        boolean flag = false;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                flag = true;
                sb.append(c);
            } else if (c == '>') {
                flag = false;
                sb.append(c);
            } else if (flag) {
                sb.append(c);
            }
            else {
                // 공백 단위로 띄워야됨.
                if (c == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(c);
                }
                else {
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

}
