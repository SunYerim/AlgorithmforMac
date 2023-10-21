package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11758 {
    static int[][] lineDirection;
    public static void main(String[] args) throws IOException {
        // idea는 x 좌표의 차이로 방향 판단하면 될 거 같음.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        lineDirection = new int[3][2]; // 입력 숫자 담을 배열

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                lineDirection[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x1 = lineDirection[0][0];
        int y1 = lineDirection[0][1];
        int x2 = lineDirection[1][0];
        int y2 = lineDirection[1][1];
        int x3 = lineDirection[2][0];
        int y3 = lineDirection[2][1];

        int crossProduct = (x2 - x1) * (y3 - y2) - (y2 - y1) * (x3 - x2);


        if (crossProduct == 0) {
            System.out.println(0); // 일직선
        } else if (crossProduct > 0) {
            System.out.println(1); // 반시계 방향
        } else {
            System.out.println(-1); // 시계 방향
        }


    }



}
