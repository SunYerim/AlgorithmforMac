package com.baekjoon;

import java.util.Scanner;

public class BOJ2877 {
    public static void main(String[] args) {
        // 4와 7로만 이루어진 수 -> 4와 7로 이진수
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        K = K+1;

        StringBuilder sb = new StringBuilder();
        int[] arr = new int[]{4, 7}; // 0과 1 -> 인덱스
        // 4(0)-7(1)-44(11)-47(01)-74(10)-77-444-447-474-477-744-747-774-777-... 반복문 순회하면서 return 시키는 방법
        // K+1를 2진수로 만들어준다.
        int num = 0;
        while (K != 0) {
            num = K % 2;
            sb.append(num);
            K = K/2;
        }

        StringBuilder result = new StringBuilder();

        for (int i = sb.toString().length() - 2 ; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                result.append(arr[0]);
            }
            else{
                result.append(arr[1]);
                }
        }
        System.out.println(result.toString());
    }
}
