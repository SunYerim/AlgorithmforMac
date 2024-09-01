import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> serials = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            serials.add(br.readLine());
        }

        // Comparator를 구현한 객체 사용하여 정렬
        Collections.sort(serials, new SerialComparator());

        StringBuilder sb = new StringBuilder();
        for(String serial : serials) {
            sb.append(serial).append("\n");
        }
        System.out.println(sb.toString());
    }
    
    static class SerialComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() != o2.length()) {
                return o1.length() - o2.length();
            }

            int sum1 = getDigitSum(o1);
            int sum2 = getDigitSum(o2);
            if (sum1 != sum2) {
                return sum1 - sum2;
            }

            return o1.compareTo(o2);

        }

        private int getDigitSum(String s) {
            int sum = 0;
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    sum += c - '0';
                }
            }
            return sum;
        }
    }
}