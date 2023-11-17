package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21736 {
    static int N, M;
    static char[][] campus;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int c;
    static int d;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        campus = new char[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < M; j++) {
                campus[i][j] = string.charAt(j);
            }
        }

        // 도연이가 나온 시점에서 bfs 출발
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (campus[i][j] == 'I'){
                    c = i;
                    d = j;
                    break;
                }

            }
        }
        int num = bfs(c, d);
        if (bfs(c, d) > 0 ){
            System.out.println(num);
        } else {
            System.out.println("TT");
        }


    }
    public static int bfs(int x, int y) {

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] answer = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + answer[0];
                int ny = dy[i] + answer[1];

                // 범위 내에 있으면
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (campus[nx][ny] == 'P' && !visited[nx][ny]){
                        count++;
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                    else if (campus[nx][ny] == 'O' && !visited[nx][ny]) {
                        q.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    } else {
                        // 벽이다.
                        continue;
                    }
                }
            }
        }
        return count;
    }
}
