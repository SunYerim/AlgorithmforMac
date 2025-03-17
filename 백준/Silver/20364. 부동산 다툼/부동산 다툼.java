import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static boolean[] visited;
    static int[] land;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        land = new int[Q];

        for (int i = 0; i < Q; i++) {
            land[i] = Integer.parseInt(br.readLine());
        }

        // 1번 오리부터
        for (int i = 0; i < Q; i++) {
            int target = land[i];
            go(target, 1);
        }

        System.out.print(sb.toString());

    }

    private static void go(int start, int end) {
        // 중간에 가는 길에 막히면 break
//        System.out.println(start + " " + end);

        // 거꾸로
        boolean flag = true;
        int firstMeetPosition = 1;
        for (int i = start; i >= 2; i /= 2) {
            if (visited[i]) {
                flag = false;
                firstMeetPosition = i;
            }
        }

        if (!flag) {
            sb.append(firstMeetPosition).append("\n");
        }
        if (flag) {
            visited[start] = true;
            sb.append(0).append("\n");
        }

    }
}
