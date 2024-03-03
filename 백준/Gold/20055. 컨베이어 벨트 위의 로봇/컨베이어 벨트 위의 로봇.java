import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static int[] map;
    static boolean[] robot;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[2*n];
        robot = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < map.length; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        // 회전 시키는 메서드
        int ans = runBelt();

        System.out.println(ans);

    }

    private static int runBelt() {
        int cnt = 0;
        // 이동하려는 칸에
        while (isGo()) {
            // 회전 -> 로봇이랑 같이 회전
            int tmp = map[map.length-1];
            for (int i = map.length-1; i > 0; i--) {
                map[i] = map[i-1];
            }
            map[0] = tmp;

            // 로봇도 같이 회전
            for (int i = robot.length-1; i > 0; i--) {
                robot[i] = robot[i-1];
            }
            robot[0] = false; // 태우는 자리
            robot[n-1] = false; // 즉시 내림

            // 가장 먼저 올라간 로봇 부터 한칸 이동 -> 내구도가 1이상
            for (int i = robot.length-1; i > 0; i--) {
                if (robot[i-1] && !robot[i] && map[i] >= 1) {
                    map[i]--;
                    robot[i] = true;
                    robot[i-1] = false;
                }
            }

            // 내구도 0 아니면 로못 올리는 위치에 로봇을 올림.
            if (map[0] > 0) {
                robot[0] = true;
                map[0]--;
            }

            cnt++;
        }
        return cnt;
    }

    private static boolean isGo() {
        int num = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0) {
                num++;
            }
        }
        if (num >= k) {
            return false;
        }

        return true;
    }

    // 벨트 클래스에 올라감.
    static class Robot {
        int num;
        boolean isRobot;

        public Robot(int num, boolean isRobot) {
            this.num = num;
            this.isRobot = isRobot;
        }
    }
}