import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            // 0이 입력으로 들어오면 가장 작은 값 출력하고 값을 배열에서 제거한다.
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                queue.offer(num);
            } else if (num == 0) {
                // 이때 큐가 비어있지 않을때
                if (!queue.isEmpty()) {
                    System.out.println(queue.poll());
                } else {
                    System.out.println(0);
                }
            }
        }

    }
}