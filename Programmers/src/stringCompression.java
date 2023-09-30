import java.util.*;

class stringCompression {
    public int solution(String s) {
        int answer = s.length();
        for (int i = 1; i <= s.length() / 2; i++) {
            String compressed = splitText(i, s);
            // 문자열의 길이와 splitText메소드 실행했을때 return 값 비교해서 작은 값을 answer로 갱신
            answer = Math.min(answer, compressed.length());
        }
        return answer;
    }

    // 단위에 맞게 문자열 split하는 메소드 따로 뺌.
    public static String splitText(int i, String s) {
        String newText = "";
        int num = 0;
        while (num < s.length()-i-i+1) {
            int count = 1;
            while (num + i <= s.length() - i && s.substring(num, num + i).equals(s.substring(num + i, num + i + i))) {
                count++;
                num += i;
            }
            if (count > 1) {
                newText += count + s.substring(num, num + i);
            } else {
                newText += s.substring(num, num + i);
            }
            num += i;
        }
        return newText + s.substring(num); // 남은 부분 추가
    }
}
