package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1244 {
    static int num;
    static int[] switches;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());
        switches = new int[num+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= num; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int students = Integer.parseInt(br.readLine());

        for (int s = 0; s < students; s++) {
            st = new StringTokenizer(br.readLine());
            int man = Integer.parseInt(st.nextToken());
            int button = Integer.parseInt(st.nextToken());

            // 들어온 정보를 토대로 로직 수행
            // 남자인 경우
            if (man == 1) {
                for (int i = 1; i <= num; i++) {
                    if (i % button == 0 && switches[i] == 0) {
                        switches[i] = 1;
                    } else if (i % button == 0 && switches[i] == 1) {
                        switches[i] = 0;
                    }
                }
                // 여자인 경우
            } else if (man == 2) {
                // 대칭 관계 표시
                int start=button;
                int end=button;
//                if (switches[button] == 0) {
//                    switches[button] = 1;
//                } else if (switches[button] == 1) {
//                    switches[button] = 0;
//                }
                // start가 일단 0보다 클때
                while (start > 0 && end <= num && switches[start] == switches[end]) {

                    if (switches[start] == 0) {
                        switches[start] = 1;
                        switches[end] = 1;
                    } else if (switches[start] == 1) {
                        switches[start] = 0;
                        switches[end] = 0;
                    }
                    start--;
                    end++;
                }

            }

        }
        // 출력
        for (int i = 1; i < switches.length; i++) {
            System.out.print(switches[i]+ " ");

            if (i % 20 == 0) {
                System.out.println();
            }
        }

    }
}
