package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1092 {
    static int N, M;
    public static void main(String[] args) throws IOException {
        // 한 회차를 끊는 기준
        //  -크레인 모든 칸에 박스를 실은 상태이거나 (두 배열 모두 오름차순으로 sort해둔 상태이기때문에, 맨 뒤에서부터 확인 들어감.)
        //  -
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 크레인
        N = Integer.parseInt(br.readLine());
        ArrayList<Integer> crain = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            crain.add(Integer.parseInt(st.nextToken()));
        }
        crain.sort(Collections.reverseOrder());

        // 박스
        M = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < M; j++) {
            box.add(Integer.parseInt(st.nextToken()));
        }
        box.sort(Collections.reverseOrder());


        if (box.get(0) > crain.get(0)) {
            System.out.println(-1);
        } else {
            int time = 0;
            while (!box.isEmpty()) {
                int startIdx = 0;
                for (int i = 0; i < crain.size(); ) {
                    if (startIdx == box.size())
                        break;
                    else if (crain.get(i) >= box.get(startIdx)) {
                        box.remove(startIdx);
                        i++;
                    } else {
                        startIdx++;
                    }

                }
                time++;

            }
            System.out.println(time);
        }

    }

}
