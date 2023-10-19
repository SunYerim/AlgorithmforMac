package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11724 {
    static int countOfArea; // 영역의 개수
    static int N, M; // 정점, 간선
    static int from, to; // 시작점, 끝점
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        // 그래프 초기화
        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            // 무방향그래프라서 두쪽 다 연결 해줘야함 .....ㄹ
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        countOfArea = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                countOfArea++;
                route(i);
            }
        }
        System.out.println(countOfArea);


    }

    public static void route(int startNode) {
        visited[startNode] = true; // 방문처리 해주고

        for (int neighbor: graph.get(startNode)) { // 연결된 노드들 검사
            if (!visited[neighbor]) {
                route(neighbor);

            }
        }

    }


}
