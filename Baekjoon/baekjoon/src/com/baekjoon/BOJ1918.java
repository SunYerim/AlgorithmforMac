package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1918 {
    static char[] c;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder(); // append로 문자열을 연결 String str = stringBuilder.toString();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string; // 수식 문자열
        string = br.readLine();
        c = new char[string.length()];
        // 스택 -> 괄호, 기호 우선순위는 괄호가 제일 높다.
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < string.length(); i++) {
            c[i] = string.charAt(i);
        }

        for (int j = 0; j < string.length(); j++) {
            int p = priority(c[j]);
            switch(c[j]) {
                case'+':
                case'-':
                case'*':
                case'/':
                    while(!stack.isEmpty() && priority(stack.peek()) >= p) {
                        sb.append(stack.pop());
                    }
                    stack.push(c[j]);
                    break;
                case')':
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop(); // 남은 여는 괄호
                    break;
                case'(':
                    stack.push('(');
                    break;
                default:
                    sb.append(c[j]);
            }
        }
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }

    // 최우선적으로 가장 우선순위가 높은 것은 괄호이다.
    static int priority(char ch) { // 연산 우선순위 반환
        switch(ch) {
            case '*':
            case'/':
                return 2;
            case '-':
            case '+':
                return 1;
            default:
                return 0;
        }
    }
}
