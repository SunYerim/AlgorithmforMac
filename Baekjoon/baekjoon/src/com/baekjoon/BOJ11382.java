package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11382 {
    static int returnNumber;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        returnNumber = 0;

        for (int i = 0; i < 3; i++) {
            returnNumber += Integer.parseInt(st.nextToken());
        }

        System.out.println(returnNumber);



    }
}
