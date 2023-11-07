import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class swea1231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int k = 1; k <= 10; k++) {
            StringBuilder sb = new StringBuilder(); // 중위순회 후, 정답으로 내보낼 문자열
            Stack<Node> s = new Stack<>(); // 중위순회에 필요한 스택
            int N = Integer.parseInt(br.readLine());
            Node[] node = new Node[N+1];

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken());
                char character = st.nextToken().charAt(0);
                int leftNode = -1; // 초기값 -1
                int rightNode = -1; // 초기값 -1

                if (st.hasMoreTokens()) {
                    leftNode = Integer.parseInt(st.nextToken());

                    if (st.hasMoreTokens()) {
                        rightNode = Integer.parseInt(st.nextToken());
                    }
                }
                node[number] = new Node(number, character, leftNode ,rightNode);
            }

            int j = 1;
            while (!s.isEmpty() || j != -1) {
                while (j != -1) {
                    s.push(node[j]);
                    j = node[j].leftNode;
                }
                Node current = s.pop();
                sb.append(current.character);

                j = current.rightNode;
            }

            System.out.println("#"+(k)+" "+sb.toString());
        }
    }

    static class Node {
        int number;
        char character;
        int leftNode;
        int rightNode;

        public Node(int number, char character, int leftNode, int rightNode) {
            this.number = number;
            this.character = character;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }
}
