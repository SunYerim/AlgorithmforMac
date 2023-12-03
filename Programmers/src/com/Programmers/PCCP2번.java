package com.Programmers;

import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] land;
    static int[][] oil;
    static boolean[][] visited;
    static int n, m;

    public int solution(int[][] land) {
        this.land = land;
        this.n = land.length;
        this.m = land[0].length;
        this.oil = new int[n][m];
        this.visited = new boolean[n][m];

        int oilId = 0;
        Map<Integer, Integer> oilSize = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int size = bfs(i, j, oilId);
                    oilSize.put(oilId, size);
                    oilId++;
                }
            }
        }

        int[] oilSum = new int[m]; // 각 열에서 뽑을 수 있는 석유의 총량 저장하는 배열
        // 석유 덩어리 크기 합산
        for (int j = 0; j < m; j++) {
            Set<Integer> oilSet = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (land[i][j] == 1) {
                    oilSet.add(oil[i][j]);
                }
            }

            for (int id: oilSet) {
                oilSum[j] += oilSize.get(id);
            }
        }

        return Arrays.stream(oilSum).max().getAsInt();
    }



    public int bfs(int x, int y, int oilId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        oil[x][y] = oilId;
        int size = 1;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && land[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    oil[nx][ny] = oilId;
                    size++;
                }
            }
        }
        return size;
    }
}