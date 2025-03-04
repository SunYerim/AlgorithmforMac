import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Character> stack = new Stack<>();
    static String line, bomb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        bomb = br.readLine(); // 폭발 문자열
        int bombSize = bomb.length();

        for (int i = 0; i < line.length(); i++) {
            // push
            stack.push(line.charAt(i));

            // 폭발 문자열과 길이가 같아진다면
            if (stack.size() >= bombSize) {
                boolean flag = true;

                // 탐색합니다.
                for (int j = 0; j < bombSize; j++) {
                    if (stack.get(stack.size() - bombSize + j) != bomb.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    // 다 뺌
                    for (int j = 0; j < bombSize; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }

}
