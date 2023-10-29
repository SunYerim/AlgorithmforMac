package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
    1. bfs를 사용하여 해결하는 문제
    2. 만약 그람을 만나게되면 그람까지 이동거리 + 그람의 좌표와 최종 목적지까지의 x, y 좌표값의 차이만큼 더해서 결과값으로 return해주면 된다.
    */
public class BOJ17836 {
    static int N, M, T;
    static boolean[][][] visited;
    static int[][] maze;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        maze = new int[N][M];

        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M][2]; // 0 -> 그람이 없을 때, 1 -> 그람이 있을 때.
        int result = bfs(0, 0);
        if (result == -1)
            System.out.println("Fail");
        else
            System.out.println(result);
    }

    public static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0, false));
        visited[x][y][0] = true;

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (current.count > T)
                break;
            if (current.x == N - 1 && current.y == M - 1)
                return current.count;

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    // 그람이 없는 경우
                    if (!current.isGram) {
                        if (!visited[nx][ny][0] && maze[nx][ny] == 0){
                            q.offer(new Node(nx, ny, current.count + 1, current.isGram));
                            visited[nx][ny][0] = true;
                        } else if (!visited[nx][ny][0] && maze[nx][ny] == 2) {
                            q.offer(new Node(nx, ny, current.count + 1, true));
                            visited[nx][ny][0] = true;
                        }
                    } else { // 그람이 있는 경우
                        if (!visited[nx][ny][1]) {
                            q.offer(new Node(nx, ny, current.count + 1, current.isGram));
                            visited[nx][ny][1] = true;
                        }
                    }

                }
            }
        }
        return -1;
    }

    public static class Node {
        // x, y좌표, 현재 탐색 깊이, 그람의 유무
        int x;
        int y;
        int count;
        boolean isGram;

        public Node(int x, int y, int count, boolean isGram) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.isGram = isGram;
        }

    }

}
