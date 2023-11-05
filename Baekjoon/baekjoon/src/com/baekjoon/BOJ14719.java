package com.baekjoon;

import java.util.Scanner;

public class BOJ14719 {
    static int H, W, count;
    static int[] rain;
    public static void main(String[] args) {
        // 의문 -> 4-3-4 이렇게 쌓인경우는 고인 빗물이 0인가?
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();

        rain = new int[W];
        for (int i = 0; i < W; i++) {
            rain[i] = sc.nextInt();
        }
        // 배열의 처음부터 순회할때, 높이가 0이 아닌 시점부터 출발한다.
        // 물이 고일 수 있는 경우 -> 현재 위치의 높이보다 더 높은 벽이 왼쪽과 오른쪽에 있어야함.
        // 최대로 고일 수 있는 높이 => 양 옆의 높이 중 작은 것을 기준으로 한다.
        int count = 0;

        for (int i = 0; i < W - 1; i++) {
            int leftMax = findLeftMax(i);
            int rightMax = findRightMax(i);

            if (leftMax > rain[i] && rightMax > rain[i]) {
                int trappedWater = Math.min(leftMax, rightMax) - rain[i];
                count += trappedWater;
            }
        }
        System.out.println(count);
    }

    static int findLeftMax(int idx) {
        int leftMax = 0;
        for (int i = 0; i < idx; i++) {
            leftMax = Math.max(leftMax, rain[i]);
        }
        return leftMax;
    }

    static int findRightMax(int idx) {
        int rightMax = 0;
        for (int i = idx + 1; i < W; i++) {
            rightMax = Math.max(rightMax, rain[i]);
        }
        return rightMax;
    }
}
