import java.util.*;
import java.io.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long number = find(numbers[i]);
            answer[i] = number;
        }
        
        return answer;
    }
    
    private static long find(long number) {
        // number보다 크고 number과 비트가 1~2개 다른 수들 중에서 제일 작은 수
        String now = Long.toString(number, 2); // 2진수 문자로 변환
        String next = Long.toString(number + 1, 2);
        
        // 두 숫자 길이가 같을때
        if (now.length() == next.length()) {
            // 마지막 값이 0이라면 -> 현재 숫자 + 1
            if (now.charAt(now.length() - 1) == '0') {
                return (number + 1) * 1L;
            }
            // 마지막 값이 0이 아니면 (01 -> 10)
            else {
                int idx = now.lastIndexOf("01");
                String tmp = now.substring(0, idx) + "10";
                
                // 나머지
                if (idx + 2 < now.length()) {
                    tmp += now.substring(idx + 2);
                }
                return Long.parseLong(tmp, 2);
            }
        }
        // 두 숫자 길이가 다르면
        // now가 1로만 채워져있으면 next의 길이가 증가하게됨.
        // 111 -> 1000
        else {
            long pow = (long) Math.pow(2, now.length() - 1);
            return (number + pow) * 1L;
        }
    }
}