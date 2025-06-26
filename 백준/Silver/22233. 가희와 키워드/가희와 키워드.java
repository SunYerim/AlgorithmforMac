import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        int currAvailableKeywords = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map.put(line, 1);
            currAvailableKeywords++;
        }

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            String[] arr = line.split(",");

            // 돌면서
            for (String keyword : arr) {
                if (map.containsKey(keyword) && map.get(keyword) == 1) {
                    map.put(keyword, 0);
                    currAvailableKeywords--;
                }
            }

            sb.append(currAvailableKeywords).append("\n");

        }
        System.out.print(sb.toString());
    }

}
