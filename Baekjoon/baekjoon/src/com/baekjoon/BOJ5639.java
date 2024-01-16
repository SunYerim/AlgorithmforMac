package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5639 {
    public static void main(String[] args) throws IOException {
        // 입력 -> 전위순회, 출력 -> 후위 순회
        // 재귀로 접근
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node(Integer.parseInt(br.readLine())); // 첫 줄은 무조건 root

        while (true) {
            // 종료시키기 위함
            String number = br.readLine();
            if (number == null || number.equals(""))
                break;

            root.insert(Integer.parseInt(number));

        }

        postOrder(root);


    }

    static class Node {
        int num;
        Node left, right;

        // 생성자
        Node(int num) {
            this.num = num;
        }

        Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n) {
            if (n < this.num) {
                if (this.left == null) {
                    this.left = new Node(n);
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(n);
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    // postOrder (좌 -> 우 -> 루트)
    static void postOrder(Node curr) {
        if (curr == null) {
            return;
        }
        postOrder(curr.left);
        postOrder(curr.right);
        System.out.println(curr.num);
    }
}
