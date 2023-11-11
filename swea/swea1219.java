import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1219 {
    static int[][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int T = 1; T <= 10; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int route = Integer.parseInt(st.nextToken());
            map = new int[100][2];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < route; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                if (map[from][0] != 0) {
                    map[from][1] = to;
                } else {
                    map[from][0] = to;
                }
            }

//            System.out.println(Arrays.toString(map1));
//            System.out.println(Arrays.toString(map2));
//            System.out.println();

            isRoute(map[0][0]);
            isRoute(map[0][1]);

            System.out.println("#"+T+" "+result);

        }
        // 출발점 -> 0, 도착점 -> 99
    }

    public static void isRoute(int start) {

        if (start == 0) {
            return;
        }

        if (start == 99) {
            result = 1;
            return;
        }
        // 두방향 탐색
        isRoute(map[start][0]);
        isRoute(map[start][1]);

    }
}
