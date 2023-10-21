package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ11659 {
    static int N, M;

    static int[] numArr;

    static ArrayList<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numArr = new int[N];
        answer = new ArrayList<Integer>(); // 정답 연결 리스트

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            answer.add(returnSum(a, b, numArr));
        }

        for (int i = 0; i < answer.size(); i++){
            System.out.println(answer.get(i));
        }


    }

    private static int returnSum(int start, int end, int[] arr) {
        int sum = 0;
        for (int i = start - 1; i < end ; i++) {
            sum += arr[i];
        }

        return sum;
    }
}
