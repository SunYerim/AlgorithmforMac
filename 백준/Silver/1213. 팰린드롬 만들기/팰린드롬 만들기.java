import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        TreeMap<Character, Integer> countMap = new TreeMap<>();

        // 문자의 빈도수 계산
        for (char ch : input.toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }

        // 홀수 번 나타나는 문자가 두 개 이상 있으면 팰린드롬 생성 불가
        int oddCount = 0;
        StringBuilder first = new StringBuilder();
        StringBuilder middle = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char ch = entry.getKey();
            int count = entry.getValue();

            if (count % 2 != 0) {
                oddCount++;
                middle.append(ch);
            }

            for (int i = 0; i < count / 2; i++) {
                first.append(ch);
            }
        }

        if (oddCount > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder secondHalf = new StringBuilder(first).reverse();
        StringBuilder result = new StringBuilder(first);

        if (middle.length() > 0) {
            result.append(middle.charAt(0));
        }

        result.append(secondHalf);

        System.out.println(result.toString());

    }
}