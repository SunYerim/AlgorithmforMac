package com.baekjoon;

import java.util.Scanner;

public class BOJ20444 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long k = sc.nextLong();

        // 색종이 조각의 개수를 구하는 식
        // 가로로 자르는 횟수 row
        // 세로로 자르는 횟수 col 라 한다면
        // (row + 1) * (col + 1) 이다. (row + col == N)

        long left = 0;
        long right = n / 2;
        while (left <= right) {
            long row = (left + right) / 2;
            long col = n - row;

            long pieces = (row + 1) * (col + 1);
            if (pieces == k) {
                System.out.println("YES");
                return;
            }
            // 조각 수가 더 많으므로 row 와 col 사이의 값 차이 벌림.
            else if (pieces > k) {
                right = row - 1;
            } else {
                left = row + 1;
            }

        }
        System.out.println("NO");
    }
}
