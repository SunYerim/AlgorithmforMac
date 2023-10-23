package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] answer = new int[N]; // answer 배열
        Stack<Integer> stack = new Stack<>(); // 문제풀이시 사용될 stack

        // N개만큼 숫자를 받는 배열.
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            // stack이 비어있지 않으면서, 현재 값이 stack 최상단 값보다 크다면
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                // 스택에서 pop 시키면서 오큰수를 찾는다.
                answer[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        // 스택이 비어있지 않으면서 현 원소보다 큰 원소가 없다면
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        for (int i = 0; i < N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
