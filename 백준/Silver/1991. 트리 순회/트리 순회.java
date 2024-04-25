import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] tree;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 입력 받기
        n = Integer.parseInt(br.readLine());
        tree = new int[26][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int c = st.nextToken().charAt(0) - 'A';

            // 비어있다는 표시인 .이 나오면
            tree[a][0] = (b == -19) ? -1 : b;
            tree[a][1] = (c == -19) ? -1 : c;
        }
        // 전위
        preOrder(0);
        sb.append("\n");
        // 중위
        inOrder(0);
        sb.append("\n");
        // 후위
        PostOrder(0);
        sb.append("\n");
        System.out.println(sb.toString());

    }

    private static void PostOrder(int x) {
        if (x == -1) return;
        PostOrder(tree[x][0]);
        PostOrder(tree[x][1]);
        sb.append((char)(x + 'A'));
    }

    private static void inOrder(int x) {
        if (x == -1) return;
        // 왼쪽
        inOrder(tree[x][0]);
        sb.append((char) (x + 'A'));
        // 오른쪽
        inOrder(tree[x][1]);
    }

    private static void preOrder(int x) {
        if (x == -1) return;
        // 현재
        sb.append((char)(x + 'A'));
        // 왼쪽
        preOrder(tree[x][0]);
        // 오른쪽
        preOrder(tree[x][1]);
    }

    static class Node {
        private char value;
        private Node left, right;
        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}