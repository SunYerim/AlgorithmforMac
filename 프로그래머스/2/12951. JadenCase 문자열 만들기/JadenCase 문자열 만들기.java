import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                sb.append(c); // 공백은 그대로 추가
                capitalizeNext = true; // 다음 글자는 대문자로 변환해야 함을 표시
            } else {
                if (capitalizeNext) {
                    sb.append(Character.toUpperCase(c)); // 첫 글자는 대문자로 변환
                    capitalizeNext = false;
                } else {
                    sb.append(Character.toLowerCase(c)); // 나머지 글자는 소문자로 변환
                }
            }
        }

        return sb.toString();
    }
}
