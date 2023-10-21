package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1182 {
    static int N, S, count;
    static int[] num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        num = new int[N];
        count = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i ++){
            for (int j = i+1; j < N; j++) {
                if (partialSum(i, j, S)){

                    count++;
                } else {
                    continue;
                }
            }
        }

        System.out.println(count);
    }


    private static boolean partialSum(int start, int end, int n) {
        int sum = 0;
        for (int i = start; i < end; i++) {
            System.out.println(start+","+end+","+num[i]);
            sum += num[i];
        }

        return sum == n;
    }
}
