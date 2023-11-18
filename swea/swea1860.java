import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class swea1860 {
    static int T, N, M, K;
    static int bbung; // M초 지날때마다 K개 만큼의 붕어빵이 늘어나는 개수 표시 위함.
    static int[] time;
    public static void main(String[] args) throws IOException {
        // 손님이 0초에 도착할 수도 있다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 손님 수
            M = Integer.parseInt(st.nextToken()); // 붕어빵 제조 시간
            K = Integer.parseInt(st.nextToken()); // 제조 시간동안 만들 수 있는 붕어빵 개수

            time = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                time[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(time);

            bbung = 0;
            boolean possible = true;

            // 붕어빵 살 수 있나 없나 판별
            for (int i = 0; i < N; i++) {
                int currentCustomerTime = time[i];

                int madeBbung = (currentCustomerTime / M) * K;

                if (madeBbung > bbung){
                    bbung++;
                } else {
                    possible = false;
                    break;
                }

            }
            System.out.println("#"+tc+" "+(possible ? "Possible" : "Impossible"));
        }

    }
}
