/* logic
    1. edge클래스 선언
    2. kruskal 메서드 정의 => union-find 사용
    3. 로직 수행*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Solution {
    static int T, V, E;
    static int[] parent;
    static ArrayList<Edge> edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());

            parent = new int[1000001];
            edges = new ArrayList<>();
            long result = 0;
            int cnt = 0;

            // 부모 테이블 상에서, 부모 자신을 초기화
            for (int i = 1; i <= V; i++) {
                parent[i] = i;
            }

            // 간선에 대한 정보 입력받기
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(a, b, cost));
            }

            // 간선을 비용순으로 정렬
            Collections.sort(edges);

            // 간선을 하나씩 확인하면서
            for (int i = 0; i < edges.size(); i++) {
                int from = edges.get(i).getFrom();
                int to = edges.get(i).getTo();
                long cost = edges.get(i).getWeight();
                // 사이클이 발생하지 않으면 집합에 포함
                if (findParent(from) != findParent(to)) {
                    unionParent(from, to);
                    result += cost;
                    if (++cnt == V-1) break;
                }
                
            }
            System.out.println("#"+tc+" "+result);
        }

    }

    // edge클래스 정의
    static class Edge implements Comparable<Edge> {

        private int from, to;
        private long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return this.to;
        }

        public int getFrom() {
            return this.from;
        }

        public long getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.weight < o.weight) {
                return -1;
            }
            return 1;
        }
    }

    // 크루스칼 알고리즘 정의

    public static int findParent(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = findParent(parent[x]);
    }

        // union
    public static boolean unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);

        if (a == b) return false;
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
        return true;
    }

}