package com.Programmers;

import java.util.*;

public class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] redVisited, blueVisited;
    static int[][] maze;
    static int rLen, cLen;
    static int[] redCood, blueCood, redDest, blueDest;
    static int ret = Integer.MAX_VALUE;

    public int solution(int[][] inputMaze) {
        maze = inputMaze;
        rLen = maze.length;
        cLen = maze[0].length;
        redVisited = new boolean[rLen][cLen];
        blueVisited = new boolean[rLen][cLen];

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (maze[i][j] == 1) {
                    redCood = new int[]{i, j};
                    redVisited[i][j] = true;
                }
                if (maze[i][j] == 2) {
                    blueCood = new int[]{i, j};
                    blueVisited[i][j] = true;
                }
                if (maze[i][j] == 3)
                    redDest = new int[]{i, j};
                if (maze[i][j] == 4)
                    blueDest = new int[]{i, j};
            }
        }

        dfs(redCood, blueCood, 0);

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }

    public void dfs(int[] redCood, int[] blueCood, int cnt) {
        // 둘 다 도착지 도달했을때
        if (Arrays.equals(redCood, redDest) && Arrays.equals(blueCood, blueDest)) {
            ret = Math.min(ret, cnt);
            return;
        }

        // 빨간색만
        if (Arrays.equals(redCood, redDest)) {
            // 4방향 탐색
            for (int bd = 0; bd < 4; bd++) {
                int[] nBlueCood = {blueCood[0] + dr[bd], blueCood[1] + dc[bd]};
                if (isCanMove(nBlueCood, blueVisited, redCood)) {
                    blueVisited[nBlueCood[0]][nBlueCood[1]] = true;
                    dfs(redCood, nBlueCood, cnt + 1);
                    blueVisited[nBlueCood[0]][nBlueCood[1]] = false;
                }
            }
        }
        // 파란색만
        else if (Arrays.equals(blueCood, blueDest)) {
            for (int rd = 0; rd < 4; rd++) {
                int[] nRedCood = {redCood[0] + dr[rd], redCood[1] + dc[rd]};
                if (isCanMove(nRedCood, redVisited, blueCood)) {
                    redVisited[nRedCood[0]][nRedCood[1]] = true;
                    dfs(nRedCood, blueCood, cnt + 1);
                    redVisited[nRedCood[0]][nRedCood[1]] = false;
                }
            }
        }
        // 나머지
        else {
            for (int rd = 0; rd < 4; rd++) {
                int[] nRedCood = {redCood[0] + dr[rd], redCood[1] + dc[rd]};
                if (isCanMove(nRedCood, redVisited, new int[] {-1, -1})) {
                    redVisited[nRedCood[0]][nRedCood[1]] = true;
                    for (int bd = 0; bd < 4; bd++) {
                        int[] nBlueCood = {blueCood[0] + dr[bd], blueCood[1] + dc[bd]};
                        if (isCanMove(nBlueCood, blueVisited, nRedCood) &&
                                !(Arrays.equals(nBlueCood, redCood) && Arrays.equals(blueCood, nRedCood))) {
                            blueVisited[nBlueCood[0]][nBlueCood[1]] = true;
                            dfs(nRedCood, nBlueCood, cnt + 1);
                            blueVisited[nBlueCood[0]][nBlueCood[1]] = false;
                        }
                    }
                    redVisited[nRedCood[0]][nRedCood[1]] = false;
                }
            }
        }
    }

    // 움직일 수 있는가 없는가
    public boolean isCanMove(int[] cood, boolean[][] visited, int[] otherCood) {
        int r = cood[0], c = cood[1];
        if (0 <= r && r < rLen && 0 <= c && c < cLen && !visited[r][c] && maze[r][c] != 5 && (otherCood[0] != r || otherCood[1] != c)) return true;
        return false;
    }
}
