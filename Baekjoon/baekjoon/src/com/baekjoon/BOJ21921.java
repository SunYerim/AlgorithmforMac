package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] visited = new int[N];

        for (int i = 0; i < N; i++) {
            visited[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = 0;
        int currentSum = 0;
        int maxDays = 0;
        int currentDays = 0;

        // 초기값 계산
        for (int i = 0; i < X; i++) {
            maxSum += visited[i];
            currentSum += visited[i];
        }
        maxDays++;

        for (int i = X; i < N; i++) {
            currentSum += visited[i] - visited[i - X];

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxDays = 1;
            } else if (currentSum == maxSum) {
                maxDays++;
            }
        }

        if (maxSum == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(maxSum);
            System.out.println(maxDays);
        }
    }
}
