package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ4949 {
    // 여는 괄호를 만나면 push
    // 닫는 괄호를 만나면 stack의 요소를 pop해서 짝이 맞으면 yes, 아니면 no 반환
    // 괄호 없이 . 있으면 yes
    public static void main(String[] args) throws IOException {
        // 괄호 -> stack 이용해서 매칭
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while (!(s = br.readLine()).equals(".")) {
            sb.append(isEqual(s)).append('\n');
        }
        System.out.println(sb.toString());

    }

    public static String isEqual(String s) {
        // 괄호가 균형을 이루고 있는 문자열인가 s
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "no";
                }
            } else if (ch == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return "no";
                }
            }
        }
        return stack.isEmpty() ? "yes" : "no";
    }
}
