package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940 {
    static int n, m, count;
    static int[][] map;
    static int[][] map2;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        map2 = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2){
                    startX = i;
                    startY = j;
                    map2[startX][startY] = 0;
                }
            }
        }

        bfs(startX, startY);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map2[i][j]+" ");
            }
            System.out.println();
        }

    }


    // 2가 목표지점 -> 출발지점으로 생각하고 count늘려서 가면 될 거 같음.
    // 갈 수 없는 땅인 위치는 0을 출력하고, 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1
    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        map2[x][y] = 0;

        while (!q.isEmpty()){
            int[] tmp = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + tmp[0];
                int ny = dy[i] + tmp[1];

                // 범위 내에서
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                   if (map[nx][ny] == 1 && !visited[nx][ny]) {
                       q.offer(new int[]{nx, ny});
                       map2[nx][ny] = map2[tmp[0]][tmp[1]] + 1;
                       visited[nx][ny] = true;
                   } else if (map[nx][ny] == 0 && !visited[nx][ny]) {
                       map2[nx][ny] = 0;
                       visited[nx][ny] = true;
                   }
                }
            }
        }

        // while문 다 돌고 갈 수 있는데 못 간 땅이면 -1처리
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 1이어야 조건에 걸리는건데 0으로 해둬서 무한삽질
                if (!visited[i][j] && map[i][j] == 1 ) {
                    map2[i][j] = -1;
                    visited[i][j] = true;
                }
            }
        }
    }
}
