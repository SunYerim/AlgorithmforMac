import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static long a, b;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        System.out.println(bfs(a));


    }

    private static int bfs(long x) {
        Queue<Long> queue = new LinkedList<>();
        queue.offer(x);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long curr = queue.poll();
                // 기저
                if (curr == b) {
                    return cnt + 1;
                }
                //조건
                if (curr * 2 <= b) {
                    queue.offer(curr*2);
                }
                if (curr * 10 + 1 <= b) {
                    queue.offer(curr*10+1);
                }

            }
            cnt++;
        }
        return -1;
    }
}