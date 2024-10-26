import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int k;
    static int[] arr, selected;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            arr = new int[k];
            selected = new int[6]; // 선택된 숫자
            visited = new boolean[k];
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            comb(selected, visited, 0, k, 6);
            sb.append("\n");

        }

        System.out.print(sb.toString());

    }

    private static void comb(int[] selected, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    sb.append(arr[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            comb(selected, visited, i+1, n, r-1);
            visited[i] = false;
        }
    }

}
