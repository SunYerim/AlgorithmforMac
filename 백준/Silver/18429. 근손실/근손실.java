import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, answer;
    static int[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        answer = 0;

        arr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 항상 500이 되도록 하는 경우의 수 -> dfs (조합 + 조건 부합 여부 추가)
        exercise(0, 500);

        System.out.println(answer);

    }

    private static void exercise(int depth, int weight) {
        // 기저
        if (depth == N) {
            answer++;
            return;
        }
        // 유도
        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (weight - K + arr[i] >= 500) {
                exercise(depth + 1, weight - K + arr[i]);
            }
            visited[i] = false;
        }
    }
}