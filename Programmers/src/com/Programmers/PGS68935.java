package com.Programmers;

import java.util.*;

public class PGS68935 {
    public int solution(int n) {
        int answer = 0;
        String reversedTenary = reverseString(tentothree(n));
        answer = threetoten(reversedTenary);
        return answer;
    }

    private String tentothree(int n) {
        // 문자열 수정가능
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n % 3;
            sb.append(remainder);
            n /= 3;
        }
        // 아래에서부터 올라가야되므로 reverse처리
        return sb.reverse().toString();
    }

    private int threetoten(String geul) {
        int result = 0;
        int power = 0;
        for (int i = geul.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(String.valueOf(geul.charAt(i)));
            result += digit * Math.pow(3, power);
            power++;
        }
        return result;

    }

    // 문자열을 반전시키는 역할을 한다.
    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}