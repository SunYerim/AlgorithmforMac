package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ1764 {
    static int N, M, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> names = new ArrayList<>();
        HashSet<String> listen = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 듣도 못한 사람
        for (int i = 0; i < N; i++) {
            listen.add(br.readLine());
        }

        // 보도 못한 사람
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if(listen.contains(name)){
                names.add(name);
            }
        }

        Collections.sort(names);

        // 듣도보도 못한 사람 출력
        System.out.println(names.size());
        for(String name: names){
            System.out.println(name);
        }

    }
}