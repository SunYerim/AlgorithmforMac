import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/* logic
    1. n개 중에 k를 선택해서 만들 수 있는 정수의 조합 -> 조합 -> 조합*/
public class Main {
    static int n, k, ans;
    static int[] numbers, selected;
    static boolean[] visited;
    static Set<String> set = new HashSet<>(); // 중복되는 숫자 제거하기 위함.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        numbers = new int[n];
        selected = new int[k];
        visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        combination(0, 0);

        System.out.println(set.size());
    }

    private static void combination(int cnt, int start) {
        // 기저
        if (cnt == k) {
            // selected 배열에 담겨있는 숫자들의 조합
            permutation("");
            return;
        }

        // 유도
        for (int i = start; i < n; i++) {
            selected[cnt] = numbers[i];
            combination(cnt+1, i+1);
        }

    }

    // selected배열에 있는 숫자들로 만들 수 있는 모든 숫자의 조합을 찾는 메서드
    private static void permutation(String str) {
        // 기저
        if (str.length() == Arrays.stream(selected).mapToObj(String::valueOf).collect(Collectors.joining()).length()) {
            set.add(str);
            return;
        }
        // 유도
        for (int i = 0; i < k; i++) {
            String tmp = Integer.toString(selected[i]);
            if (!visited[i]) {
                visited[i] = true;
                permutation(str + tmp);
                visited[i] = false; // 백트레킹
            }
        }
    }
}