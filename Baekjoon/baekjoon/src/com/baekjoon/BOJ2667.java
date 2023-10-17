package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ2667 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, numberOfArea; // 지도의 크기, 구역의 개수
    static int[][] maze; // 지도 입력받을 그래프
    static boolean[][] visited; // 방문 표시 그래프
    static ArrayList<Integer> areaSizes = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];
        visited = new boolean[N][N];


        // maze에 저장 -> 한 줄이 붙어서 문자열로 나오는 경우일경우. 숫자는 tokenizer
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                maze[i][j] = line.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && maze[i][j] != 0) {
                    numberOfArea++;
                    int size = dfs(N, maze, visited, i, j);
                    areaSizes.add(size);

                }
            }
        }

        Collections.sort(areaSizes); // 오름차순으로 정렬


        System.out.println(numberOfArea); // 구역 개수 정렬

        for (int size: areaSizes) {
            System.out.println(size);
        }


    }
    private static int dfs(int N, int[][] maze, boolean[][] visited, int x, int y) {
        // 현재 구역 크기 저장하는 변수
        int size = 1;
        visited[x][y] = true; // 방문 처리

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내에 있으면서,
            if (nx >= 0 && nx < N && ny >= 0 && ny < N){
                if (!visited[nx][ny] && maze[nx][ny] == 1) {
                    size += dfs(N, maze, visited, nx, ny);
                }
            }
        }
        return size;
    }



}

