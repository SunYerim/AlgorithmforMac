import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[] arr;
    static PriorityQueue<Node> node;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        node = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.absNum == o2.absNum) {
                    // 가장 작은 수 출력
                    return o1.num - o2.num;
                }
                return o1.absNum - o2.absNum;
            }
        });

        int index = 0;
        // 힙큐 사용
        for (int i = 0; i < N; i++) {
            // 숫자를 넣으면서.
            int num = Integer.parseInt(br.readLine());
            // 넣으려는 숫자가 0이면
            if (num == 0) {
                // 큐가 비어있지 않을 때만 poll하고, 비어있으면 0을 저장
                if (!node.isEmpty()) {
                    arr[index++] = node.poll().num;
                } else {
                    arr[index++] = 0;
                }
            } else {
                node.offer(new Node(num, Math.abs(num)));
            }

        }

        for (int i = 0; i < index; i++) {
            System.out.println(arr[i]);
        }
    }

    static class Node {
        int num;
        int absNum;
        public Node(int num, int absNum) {
            this.num = num;
            this.absNum = absNum;
        }
    }
}