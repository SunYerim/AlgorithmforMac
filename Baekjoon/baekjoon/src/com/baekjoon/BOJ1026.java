package com.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ1026 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        // a 배열을 오름차순으로 정렬
        Arrays.sort(a);

        int result = 0;

        for (int i = 0; i < n; i++) {
            // b 배열에서 최대값을 찾음
            int maxB = Arrays.stream(b).max().getAsInt();

            // a 배열의 최소값과 b 배열에서 찾은 최대값을 곱함
            result += a[i] * maxB;

            // b 배열에서 최대값을 제거
            for (int j = 0; j < n; j++) {
                if (b[j] == maxB) {
                    b[j] = Integer.MIN_VALUE; // 임의의 작은 값으로 대체
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
