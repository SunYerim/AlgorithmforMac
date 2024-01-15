package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class newNode {
    ArrayList<newNode> friends = new ArrayList<>();
    boolean visited = false;
    int index;
}

public class BOJ13023 {
    static int N, M;
    static newNode[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new newNode[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new newNode();
            graph[i].index = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].friends.add(graph[v]);
            graph[v].friends.add(graph[u]);
        }

        // 모든 노드에서 DFS를 시작하여 탐색
        boolean result = false;
        for (int i = 0; i < N; i++) {
            result = dfs(i, 0);
            if (result) {
                break;  // 이미 조건을 만족하는 경우 더 이상 확인할 필요 없음
            }
        }

        System.out.println(result ? 1 : 0);
    }

    private static boolean dfs(int current, int depth) {
        if (depth == 4) {
            return true;  // A, B, C, D, E와 같은 친구 관계가 만족하는 경우
        }

        graph[current].visited = true;
        for (newNode friend : graph[current].friends) {
            if (!friend.visited) {
                if (dfs(friend.index, depth + 1)) {
                    return true;
                }
            }
        }

        graph[current].visited = false;
        return false;
    }
}
