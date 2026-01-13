import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long minTime = Long.MAX_VALUE;
    static Point start, end;
    static Teleport[] teleports;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        teleports = new Teleport[3];
        visited = new boolean[3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            teleports[i] = new Teleport(new Point(startX, startY), new Point(endX, endY));
        }

        dfs(start, 0);

        System.out.println(minTime);
    }

    public static void dfs(Point curr, long time) {
        // 기저
        if (curr.x == end.x && curr.y == end.y) {
            minTime = Math.min(minTime, time);
            return;
        }

        // 유도
        for (int i = 0; i < 3; i++) {
            long timeToHome = time + menhaten(curr, end);
            minTime = Math.min(minTime, timeToHome);

            if (!visited[i]) {
                visited[i] = true;
                Teleport t = teleports[i];

                long costA = menhaten(curr, t.start) + 10;
                dfs(t.end, time + costA);

                long costB = menhaten(curr, t.end) + 10;
                dfs(t.start, time + costB);

                visited[i] = false;

            }

        }

    }

    public static long menhaten(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    static class Point {

        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Teleport {

        Point start, end;

        public Teleport(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    }

}
