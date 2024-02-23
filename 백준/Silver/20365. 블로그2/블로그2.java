import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* logic
    1. 구간의 수를 찾는 메서드를 정의
    2. 구간의 갯수가 더 많은 것으로 초기화를 시키고
    3. 구간의 갯수가 적은 것에 1을 더해줘서 return 시킨다.*/
public class Main {
    static int n;
    static char[] colors;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        colors = new char[n];
        String tmp = br.readLine();
        for (int i = 0; i < n; i++) {
            colors[i] = tmp.charAt(i);
        }

        ans = new int[2];

        int[] res = isSection(colors);

        int ans = Math.min(res[0],res[1]) + 1;
        System.out.println(ans);

    }

    // 구간을 판단하는 메서드
    private static int[] isSection(char[] color) {
        int ans[] = new int[2]; // 0: blue, 1: red
        int tmp = 0; // while문 종료시키기 위한 변수
        int blue = 0, red = 0;
        while (tmp < n) {
//            if (tmp >= n+1) {
//                break;
//            }
            char first = color[tmp];
            // 파란색이면서
            if (first == 'B') {
                while (tmp < n && first == color[tmp]) {
                    tmp++;
                }
//                System.out.println("blue "+tmp);
                blue++;
            }
            else if (first == 'R') {
                while (tmp < n && first == color[tmp]) {
                    tmp++;
                }
//                System.out.println("red "+tmp);
                red++;
            }
        }
        return new int[] {blue, red};
    }
}