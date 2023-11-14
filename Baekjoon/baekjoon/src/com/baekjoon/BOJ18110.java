package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ18110 {
    static int[] opinion;
    public static void main(String[] args) throws IOException {
        // 절사평균 문제
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        opinion = new int[T];
        for (int i = 0; i < T; i++) {
            opinion[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(opinion);
        int cutAmount = (int)Math.round(T * 0.15);

        int sum = 0;
        for (int i = 0; i < T; i++) {
            if (i < cutAmount || i > T-1-cutAmount){
                continue;
            }
            sum += opinion[i];
        }
        // 평균
        int result = (int)Math.round((double) sum / (T - cutAmount * 2));
        System.out.println(result);

    }
}
