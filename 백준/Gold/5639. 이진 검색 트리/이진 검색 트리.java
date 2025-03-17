import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine()));

        while (true) {
            String num = br.readLine();
            if (num == null) break;

            root.insert(Integer.parseInt(num));

        }

        postOrder(root);
    }

    // 후위
    private static void postOrder(Node curr) {
        if (curr == null) return;

        postOrder(curr.left);
        postOrder(curr.right);
        System.out.println(curr.num);
    }

    static class Node {
        int num;
        Node left, right;

        public Node (int num) {
            this.num = num;
        }

        public Node (int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        // 삽입
        void insert(int number) {
            if (number < this.num) {
                if (this.left == null) {
                    this.left = new Node(number);
                } else {
                    this.left.insert(number);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(number);
                } else {
                    this.right.insert(number);
                }
            }
        }
    }
}
