package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14442 {
    static int N, M, K;
    static int[][] maze;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        // 최단거리 문제 -> bfs
        // 여기서는 벽을 '언제' 부술 건지가 문제임 -> boolean 배열을 3차원으로 확장
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M][K+1];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = row.charAt(j) - '0';
            }
        }

        bfs(new Node(0, 0,   0, 0));
    }


    public static void bfs(Node startNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        int[] dx = {1, -1, 0, 0}; // 동서남북
        int[] dy = {0, 0, -1, 1};

        int result = -1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            int x = current.x;
            int y = current.y;
            int count = current.count; // 몇 번 부쉈는가
            int distance = current.distance;

            // 목적지에 도착하면
            if (x == N - 1 && y == M - 1) {
                result = distance + 1;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                //범위 내에 있으면서
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (maze[nx][ny] == 0 && !visited[nx][ny][count]) {
                        visited[nx][ny][count] = true;
                        queue.add(new Node(nx, ny, count, distance + 1));
                    // ... count < K 조건을 제일 뒤에 넣어둬서 계속 에러가 났는데 .. 순서도 신경써야 된다는 거 배워갑니다..
                    } else if (maze[nx][ny] == 1 && count < K && !visited[nx][ny][count+1]) {
                        visited[nx][ny][count+1] = true;
                        queue.add(new Node(nx, ny, count + 1,distance + 1));
                    }
                }
            }
        }
        System.out.println(result);
    }
}

    class Node {
        int x;
        int y;
        int count; // K번 오버되는가 체크
        int distance; //현재까지 이동한 거리

        public Node(int x, int y, int count, int distance) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.distance = distance;
        }
    }

