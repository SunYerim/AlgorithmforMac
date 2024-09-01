import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// deque자료구조를 사용하자.
public class Main {
    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String command = br.readLine();
            int len = Integer.parseInt(br.readLine());
            String tmp = br.readLine();
            // 이때, 덱을 구현하여 숫자만 넣고, command char마다의 명령을 실행한다.
            Deque<Integer> deque = new LinkedList<>();
            if (len > 0) {
                String[] elements = tmp.substring(1, tmp.length() - 1).split(",");
                for (String element : elements) {
                    deque.add(Integer.parseInt(element));
                }
            }

            processCommands(command, deque);
        }
        System.out.println(sb.toString());


    }

    private static void processCommands(String command, Deque<Integer> deque) {
        boolean isReversed = false;

        for (char cmd : command.toCharArray()) {
            if (cmd == 'R') {
                isReversed = !isReversed;
            } else if (cmd == 'D') {
                if (deque.isEmpty()) {
                    sb.append("error").append("\n");
                    return;
                }
                if (isReversed) {
                    deque.removeLast();
                } else {
                    deque.removeFirst();
                }
            }
        }
        sb.append("[");
        while (!deque.isEmpty()) {
            sb.append(isReversed ? deque.removeLast() : deque.removeFirst());
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]").append("\n");
    }
}