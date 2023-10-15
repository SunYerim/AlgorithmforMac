package com.baekjoon;

import java.util.*;
import java.io.*;

public class BOJ16940 {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();;
    static Queue<Integer> q;
    static boolean[] visited;
    static int[] answer;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        q = new LinkedList<>();
        visited = new boolean[N + 1];
        answer = new int[N];

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        // 주어진 정보로 정점 연결 작업
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 입력된 답안
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            answer[i] = Integer.parseInt(st.nextToken());
        }

        if (answer[0] != 1) {
            System.out.println(0);
            return;
        }

        // bfs 함수 실행

        q.offer(1);
        visited[1] = true;

        if (bfs()) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    // answer 배열에 넣어서 마지막 줄에 나와있는 거랑 일치하는지 안하는지 flag 처리
    public static boolean bfs() {
        int idx = 1;
        HashSet<Integer> set = new HashSet<>();

        // 큐가 빌 때까지 일단 반복
        while (!q.isEmpty()) {
            set.clear();
            int x = q.poll();

            for(int next : graph.get(x)) {
                if(visited[next]) continue;
                set.add(next);
                visited[next] = true;
            }

            int size = set.size();

            // x와 연결되어 있으나 방문하지 않은 원소들을 큐에 삽입한다.
            for (int i = idx; i < idx + size; ++i) {
                if (set.contains(answer[i]))
                    q.offer(answer[i]);
                else
                    return false;
            }
            idx += size;
        }
        return true;
    }
}
