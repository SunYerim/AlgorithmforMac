package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// T -> S로 줄여간다. S -> T 로 가면 메모리 초과 뜰 수도 있을 확률.
// 맨 뒤 문자열이 A인 경우 -> A 제거 후 재귀.
// 맨 앞 문자열이 B인 경우 -> B 제거 후 reverse해주고 재귀.
// 길이 똑같을때, 한가지라도 일치하면 return true, 나머지 return false
// dfs 재귀 방식으로 푼다.

public class BOJ12919 {
    static String inputString, outputString;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
        outputString = br.readLine();
        System.out.println(dfs(inputString, outputString));
    }

   public static int dfs(String input, String output) {
        if (input.length() == output.length()) {
            if (input.equals(output)) {
                return 1;
            }
            return 0;
        }
        int answer = 0;
        if (output.charAt(output.length()-1) == 'A') {
            answer += dfs(input, output.substring(0, output.length()-1));
        }
        if (output.charAt(0) == 'B') {
            String temporary = output.substring(1);
            StringBuilder sb = new StringBuilder(temporary);
            String string = sb.reverse(). toString();
            answer += dfs(input, string);
        }

        return answer > 0 ? 1 : 0;
   }

}
