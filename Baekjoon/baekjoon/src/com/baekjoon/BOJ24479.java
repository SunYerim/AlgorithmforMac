package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Stack;

public class BOJ24479 {
    static int N, M, R; // 정점, 간선, 시작 정점
    static int from, to, count; // 시작 정점, 끝 정점
    static ArrayList<ArrayList<Integer>> graph; // 연결관계 나타내는 graph
    static int[] visited;
    static ArrayList<Integer> visitOrder; // 방문순서 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        visited = new int[N+1];
        visitOrder = new ArrayList<>();

        // 연결
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 오름차순을 위해 정렬
        for(int i = 1; i < graph.size(); i++) {
            Collections.sort(graph.get(i));
        }

        count = 1;

        dfs(R);

        for (int i = 1; i < visited.length; i++) {
            sb.append(visited[i]).append('\n');
        }
        System.out.println(sb);

    }

    // 스택으로 처리하기
    private static void dfs(int x) {
        // 방문처리
        visited[x] = count;


        for (int i = 0; i < graph.get(x).size(); i++) {
            int newVertex = graph.get(x).get(i);

            if (visited[newVertex] == 0) {
                count++;
                dfs(newVertex);
            }


        }

    }
}
