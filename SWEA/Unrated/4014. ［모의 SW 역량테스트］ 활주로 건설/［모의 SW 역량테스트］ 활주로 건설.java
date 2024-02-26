import java.util.Scanner;

public class Solution {
    static int N, X;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            X = sc.nextInt();
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                if (check(i, 0, 0)) count++; 
                if (check(0, i, 1)) count++; 
            }
            System.out.println("#" + tc + " " + count);
        }
        sc.close();
    }

    static boolean check(int x, int y, int dir) {
        int[] height = new int[N];
        boolean[] slope = new boolean[N];
        for (int i = 0; i < N; i++) {
            height[i] = (dir == 0) ? map[x][y + i] : map[x + i][y];
        }

        for (int i = 0; i < N - 1; i++) {
            if (height[i] == height[i + 1]) continue;

            if (Math.abs(height[i] - height[i + 1]) > 1) {
                return false;
            }

            if (height[i] - 1 == height[i + 1]) {
                for (int j = i + 1; j <= i + X; j++) {
                    if (j >= N || height[j] != height[i + 1] || slope[j]) {
                        return false;
                    }
                    slope[j] = true;
                }
            } else {
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || height[j] != height[i] || slope[j]) {
                        return false;
                    }
                    slope[j] = true;
                }
            }
        }
        return true;
    }
}