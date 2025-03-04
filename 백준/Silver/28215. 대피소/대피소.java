import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K, answer = Integer.MAX_VALUE;
    static List<Node> houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N개의 집
        K = Integer.parseInt(st.nextToken()); // K개의 대피소 선택

        houses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            houses.add(new Node(x, y));
        }

        if (K == 1){
            for (int i = 0; i < N; i++) {
                answer = Math.min(answer, calc(i, i, i));
            }
        } else if (K == 2) {
            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    answer = Math.min(answer, calc(i, j, j));
                }
            }
        } else if (K == 3) {
            for (int i = 0; i < N - 2; i++) {
                for (int j = i + 1; j < N - 1; j++) {
                    for (int k = j + 1; k < N; k++) {
                        answer = Math.min(answer, calc(i, j, k));
                    }
                }
            }
        }


        System.out.println(answer);


    }

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int calc(int a, int b, int c) {
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            int tmp1 = menhaten(houses.get(i).x, houses.get(a).x, houses.get(i).y, houses.get(a).y);
            int tmp2 = menhaten(houses.get(i).x, houses.get(b).x, houses.get(i).y, houses.get(b).y);
            int tmp3 = menhaten(houses.get(i).x, houses.get(c).x, houses.get(i).y, houses.get(c).y);
            ans = Math.max(ans, Math.min(Math.min(tmp1, tmp2), tmp3));
        }

        return ans;
    }

    private static int menhaten(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}
