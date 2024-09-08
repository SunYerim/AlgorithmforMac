import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, l;
    static int[] cutting;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        cutting = new int[m + 1];

        for (int i = 0; i < m; i++) {
            cutting[i] = Integer.parseInt(br.readLine());
        }
        cutting[m] = l;

        // 잘라야하는 횟수
        for (int i = 0; i < n; i++) {
            int cnt = Integer.parseInt(br.readLine());

            // 실행
            int left = 0;
            int right = l;
            int ans = 0;

            while (left < right) {
                int execution = 0; // 자른 횟수
                int mid = (left + right) / 2;
                int prev = 0; // 이전에 자른 위치, 시작은 케이크의 시작점
                // cutting배열을 순차적으로 탐색
                for (int j = 0; j <= m; j++) {
                    // 자를 수 있는 위치와 이전 위치의 차이가 mid이상이면 자름
                    if (cutting[j] - prev >= mid) {
                        execution++; // 자른 횟수 증가
                        prev = cutting[j]; // 이전 자른 위치 업데이트
                    }
                }
                // 자른 횟수가 주어진 자를 수 있는 횟수보다 작거나 같으면 mid를 반환
                if (execution <= cnt) {
                    right = mid; // 더 짧은 케이크 조각이 가능한지 탐색
                } else {
                    left = mid + 1; // 더 큰 케이크 조각 찾아야하므로 범위를 늘림
                    ans = Math.max(ans, mid); // 현재까지의 최소 길이 중 최댓값을 갱신
                }
            }
            System.out.println(ans);
        }

    }
}