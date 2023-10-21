package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ2357 {
    static int N, M;
    static int[] number;
    //static int[][] ranking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        number = new int[N];

        for (int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            number[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(minNum(slicingArr(a, b, number))+ " " + maxNum(slicingArr(a, b, number)));
        }

    }

    private static int[] slicingArr(int start, int end, int[] number) {
        int[] slicing = new int[end-start+1];
        for (int i = 0; i < end-start+1; i++) {
            slicing[i] = number[start+i];
        }

        return slicing;
    }

    private static int maxNum(int[] slicingArr) {
        Arrays.sort(slicingArr);
        int length = slicingArr.length - 1;
        return slicingArr[length];
    }

    private static int minNum(int[] slicingArr) {
        Arrays.sort(slicingArr);
        return slicingArr[0];
    }
}
