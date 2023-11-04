package com.baekjoon;

import java.util.Scanner;

public class BOJ1188 {
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // N % M이 0일때와 0이 아닐때로 나눠서 구현한다.
        if (N % M == 0) {
            System.out.println(0);
        // 안 나눠떨어질때 -> M - gcd(M, N)
        } else {
            System.out.println(M - gcd(M, N));
        }

    }

    public static int gcd(int n, int m) {
        // n과 m의 최소공배수를 return하는 메소드
        if (n % m == 0) {
            return m;
        }
        return gcd(m, n%m);
    }
}
