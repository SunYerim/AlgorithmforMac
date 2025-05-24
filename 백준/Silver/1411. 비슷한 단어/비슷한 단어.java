import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    static int N, ans = 0;
    static String[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new String[N];

        for (int i = 0; i < N; i++) {
            list[i] = br.readLine();
        }

        // 비교
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                boolean flag = judge(list[i], list[j]);

                if (flag) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    public static boolean judge(String a, String b) {
        // a <-> b 가능한지
        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            char o = a.charAt(i);
            char c = b.charAt(i);
            // 이미 원본 문자열 존재한다면
            if (map.containsKey(o)) {
                if (map.get(o) != c) {
                    return false;
                }
            }
            // 다른 알파벳 대체문자인지 확인
            else {
                List<Character> keys = new ArrayList<>(map.keySet());
                for (Character key : keys) {
                    if (map.get(key) == c) {
                        return false;
                    }
                }
                map.put(o, c);
            }
        }

        return true;
    }
}
