import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int t, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<Node> arr = new ArrayList<Node>();
            int[][] map = new int[n+2][n+2];
            for (int j = 0; j < n+2; j++) {
                st = new StringTokenizer(br.readLine());
                arr.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            for (int k = 0; k < n+2; k++) {
                for (int h = k+1; h < n+2; h++) {
                    map[k][h] = menhaten(arr.get(k).x, arr.get(k).y, arr.get(h).x, arr.get(h).y);
                    if (map[k][h] > 1000) {
                        map[k][h] = -1;
                        map[h][k] = -1;
                    }
                }
            }
            for (int k = 0; k < n+2; k++) {
                for (int h = 0; h < n+2; h++) {
                    if (k == h) continue;
                    for (int l = 0; l < n+2; l++) {
                        if (l == h || l == k) continue;
                        if (map[h][k] == -1 || map[k][l] == -1) continue;
                        map[h][l] = 1;
                    }
                }
            }
            if (map[0][n+1] == -1) {
                System.out.println("sad");
            } else {
                System.out.println("happy");
            }

        }
    }
    private static int menhaten(int x1, int y1, int x2, int y2) {
        return (Math.abs(x2-x1) + Math.abs(y2-y1));
    }


    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}