import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    // deque
    static Deque<Ballon> deque;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            deque.add(new Ballon(i+1, num));
        }

        Ballon first = deque.poll();
        sb.append(first.idx).append(" ");

        int n = first.num;

        int idx = 1;
        while (!deque.isEmpty()) {

            if (n > 0) {
                // 앞에서 빼고 뒤로 넣음
                for (int i = 1; i < n; i++) {
                    deque.add(deque.poll());
                }
                Ballon next = deque.poll();
                sb.append(next.idx).append(" ");
                n = next.num;
            } else {
                for (int i = 1; i < -n; i++) {
                    deque.addFirst(deque.pollLast());
                }
                Ballon next = deque.pollLast();
                sb.append(next.idx).append(" ");
                n = next.num;
            }
        }

        System.out.print(sb.toString());

    }

    static class Ballon {
        int idx, num;

        public Ballon(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

}
