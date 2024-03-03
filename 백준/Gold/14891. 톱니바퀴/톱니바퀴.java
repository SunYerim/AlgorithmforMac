import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayDeque[] gears;

    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gears = new ArrayDeque[4];

        for (int i = 0; i < 4; i++) {
            String tmp = br.readLine();
            gears[i] = new ArrayDeque<Integer>();
            for (int j = 0; j < tmp.length(); j++) {
                gears[i].add(tmp.charAt(j)-'0');
            }
        }

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            round(num-1, dir);
        }

        int ans = 0;
        if (gears[0].getFirst().equals(1)) ans += 1;
        if (gears[1].getFirst().equals(1)) ans += 2;
        if (gears[2].getFirst().equals(1)) ans += 4;
        if (gears[3].getFirst().equals(1)) ans += 8;

        System.out.println(ans);

    }
    private static void round(int num, int dir) {

        int[] direction = new int[4]; // 각 자석의 회전방향 저장
        direction[num] = dir;

        // 나를 기준으로 왼쪽
        for (int i = num; i > 0; i--) {
            if (!(gears[i].toArray()[6].equals(gears[i-1].toArray()[2]))) {
                direction[i-1] = -direction[i];
            } else {
                break;
            }
        }

        // 나를 기준으로 오른쪽
        for (int i = num+1; i < 4; i++) {
            if (!(gears[i-1].toArray()[2].equals(gears[i].toArray()[6]))) {
                direction[i] = -direction[i-1];
            } else {
                break;
            }
        }


        // 자석 회전
        for (int i = 0; i < 4; i++) {
            if (direction[i] == 1) {
                // 시계
                gears[i].addFirst(gears[i].removeLast());
            } else if (direction[i] == -1) {
                // 반시계
                gears[i].addLast(gears[i].removeFirst());
            }
        }

    }
}