package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928 {
    static int[][] ladders; // 사다리 -> 2차원 배열로 출발지 목적지 담아둠.
    static int[][] snakes; // 뱀 -> 2차원 배열로 출발지 목적지 담아둠.
    static int N, M;
    static int[] diceNumber = {1, 2, 3, 4, 5, 6};
    public static void main(String[] args) throws IOException {
        // 100번 칸을 넘어간다면 이동 불가능. -> 100번 칸에 도착을 해야함. 최소 횟수로 갈 수 있는 걸 모색중이므로 bfs 사용
        // 사다리 칸이라면 사다리를 타고 '위로' 올라감.
        // 뱀이 있는 칸이라면 뱀을 따라서 '내려가게'됨.
        // 위의 3가지 경우를 제외하고는 주사위를 굴려서 나온 수만큼 더한 칸으로 이동시키면 됨.

        // 사다리판을 생성한다.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ladders = new int[N][2];
        // 사다리 정보르 받아온다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladders[i][0] = Integer.parseInt(st.nextToken()); // 출발
            ladders[i][1] = Integer.parseInt(st.nextToken()); // 도착
        }

        snakes = new int[M][2];
        // 뱀의 정보를 입력해온다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snakes[i][0] = Integer.parseInt(st.nextToken());
            snakes[i][1] = Integer.parseInt(st.nextToken());
        }

        bfs(new Node(1, 0));

    }

    // 내가 현재 있는 칸을 넣어서 돌린다.
    public static void bfs(Node node) {
        // 100번 칸을 넘어간다면 이동 불가능. -> 100번 칸에 도착을 해야함. 최소 횟수로 갈 수 있는 걸 모색중이므로 bfs 사용
        // 사다리 칸이라면 사다리를 타고 '위로' 올라감.
        // 뱀이 있는 칸이라면 뱀을 따라서 '내려가게'됨.
        // 위의 3가지 경우를 제외하고는 주사위를 굴려서 나온 수만큼 더한 칸으로 이동시키면 됨.
        // 생각해보니까 좌 아니면 우 두 방향밖에 이동이 안 됨.
        // 주사위는 ...?
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[101];
        q.offer(node);

        while (!q.isEmpty()){
            Node current = q.poll();
            for (int i = 0; i < 6; i++) {
                int nx = current.x + diceNumber[i];

                if (nx <= 100 && !visited[nx]) {
                    visited[nx] = true;
                    // 사다리칸이면
                    for (int j = 0; j < N; j++) {
                        if (ladders[j][0] == nx) {
                            nx = ladders[j][1];
                            break;
                        }
                    }
                    // 뱀이 있는 칸이라면
                    for (int k = 0; k < M; k++) {
                        if (snakes[k][0] == nx) {
                            nx = snakes[k][1];
                            break;
                        }
                    }
                    // 사다리도 없고 뱀도 없는 칸이라면 -> 사다리 있는 칸까지 가는 것이 우선 목표가 됨.
                    q.offer(new Node(nx, current.count+1));


                    if (nx == 100) {
                        System.out.println(current.count + 1);
                        return;
                    }

                    // 이거 처리 안하니까 메모리 초과뜸.
                    if (nx > 100){
                        return;
                    }
                }
            }
        }

    }

    static class Node {
        int x;
        int count;

        public Node(int x, int count){
            this.x = x;

            this.count = count;
        }
    }
}
