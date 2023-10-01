import java.util.*;

public class parenthesesConversion {
    public String solution(String p) {
        if (check(p)) {
            return p;
        }

        return divide(p);
    }

    // 올바른 괄호 문자열 판단 메소드
    public static boolean check(String str) {
        int open = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                open++;
            } else if (c == ')') {
                open--;
                if (open < 0) {
                    return false;
                }
            }
        }
        return open == 0;
    }

    // 문자열 divide 메소드
    public static String divide(String w) {
        if (w.isEmpty()) {
            return "";
        }

        int open = 0;
        int close = 0;
        int index = 0;

        do {
            char c = w.charAt(index);
            if (c == '(') {
                open++;
            } else {
                close++;
            }
            index++;
        } while (open != close);

        String u = w.substring(0, index);
        String v = w.substring(index);

        if (check(u)) {
            return u + divide(v);
        } else {
            StringBuilder sb = new StringBuilder("(");
            sb.append(divide(v));
            sb.append(")");
            sb.append(reverse(u.substring(1, u.length() - 1)));
            return sb.toString();
        }
    }

    // 문자열을 뒤집는 메소드
    public static String reverse(String str) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') {
                s.append(')');
            } else {
                s.append('(');
            }
        }
        return s.toString();
    }
}
