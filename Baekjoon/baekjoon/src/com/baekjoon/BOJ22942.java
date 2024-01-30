package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* logic
    1. Node 구조를 생성하여 각 원의 중심점과 반지름 길이를 저장
    2. 원들을 중심점 기준으로 정렬하되, 중심이 같다면, 반지름이 더 긴 원을 고려하도록 정렬
    3. 스택에 Node 하나씩 넣으면서 현재 원과 스택의 top에 있는 원이 겹치는지 확인
        - 겹치면 -> 현재 원을 스택에 넣지 않아야 함.
        - 안 겹치면 -> 현재 원을 스택에 넣으면 됨.
     4. 모든 원 순회 후에도 스택에 원이 남아있으면 ? -> "YES"
     5. 중간에 겹치는 원이 있어서 스택에 못 들어간다면 ? -> "NO"*/
public class BOJ22942 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // Node 클래스들을 저장할 배열
        List<Node> nodes = new ArrayList<Node>();
        // 나중에 사용할 스택
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            Node node = new Node(x, r);
            nodes.add(node);
        }

        // 중심점 기준으로 정렬하되, 중심이 같다면 반지름이 더 긴 원이 앞쪽으로


        // 스택에 node push하면서 현재 원과 스택의 top에 있는 원이 겹치는가 확인
            //  겹치면 -> 스택에 넣지말고, 안 겹치면 -> 스택에 넣고


        // 모든 원 순회 후에도 스택에 원이 남아있다면 -> yes, 아니면 -> no
        if (stack.isEmpty()) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }


    }

    static class Node {
        int middle;
        int radius;
        public Node(int middle, int radius) {
            this.middle = middle;
            this.radius = radius;
        }
    }
}
