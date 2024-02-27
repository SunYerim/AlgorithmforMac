import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static HashSet<Integer> numbers;
    static int T;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            String tmp = br.readLine();

            numbers = new HashSet<>();
            ArrayDeque<Integer> deque = new ArrayDeque<>();

            // 회전하는 수
            for (int i = 0; i < n/4; i++) {
                for (int j = 0; j < 4; j++) {
                    int startIndex = j * n / 4;
                    int endIndex = (j+1) * n / 4;
                    numbers.add(Integer.parseInt(tmp.substring(startIndex, endIndex), 16));
                }
                char last = tmp.charAt(tmp.length() - 1);
                tmp = last + tmp.substring(0, tmp.length() - 1);

                //deque.addFirst(deque.removeLast());

            }

            List<Integer> sortedNumbers = new ArrayList<>(numbers);
            Collections.sort(sortedNumbers, Collections.reverseOrder());

            sb.append(sortedNumbers.get(k-1)).append("\n");


        }
        System.out.println(sb);
    }

    private static String rotateString(String str) {
        char last = str.charAt(str.length() - 1);
        return last + str.substring(0, str.length() - 1);
    }
}