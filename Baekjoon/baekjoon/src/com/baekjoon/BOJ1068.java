package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 1 -> 배열, 2 -> linkedlist
// 노드 하나 삭제시키고 나면, 연달아 있는 칸의 값을 -2로 바꿔준다.
// 아니 자손의 부모노드를 -2
// 예를 들어, 4번 노드를 삭제한다고 하면. 4번 노드를 부모로 갖고 있는 노드 + 해당 노드의 자손들의 부모 노드 값을 -2로 변경

public class BOJ1068 {
    static int N, target;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }

        target = Integer.parseInt(br.readLine());

        deleteNode(target);

        System.out.println(countLeaf());
    }

    public static void deleteNode(int node) {
        for (int i = 0; i < N; i++) {
            if (tree[i] == node) {
                deleteNode(i); // 재귀
            }
        }
        tree[node] = -2;
    }

    public static int countLeaf() {
        int leafCount = 0;
        for (int i = 0; i < N; i++) {
            if (tree[i] != -2 && !hasChild(i)) {
                leafCount++;
            }
        }
        return leafCount;
    }

    public static boolean hasChild(int node) {
        for (int i = 0; i < N; i++) {
            if (tree[i] == node) {
                return true;
            }
        }
        return false;
    }
}
