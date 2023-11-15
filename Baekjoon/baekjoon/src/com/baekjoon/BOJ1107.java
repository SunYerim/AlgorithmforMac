package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1107 {
    static boolean[] workButton; // 나중에 고장난 번호 들어오면 false처리 시킨다.
    public static void main(String[] args) throws IOException {
        // 고장난 채널 입력 받을때, boolean배열 10칸 만들어서 false처리 시켜준다.
        // 100번에서 시작
        // '최소 몇 번' =>
        // 0번에서 -를 계속 누르게 되면, 동작하지 않는다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        workButton = new boolean[10];
        // 고장난 채널 입력 받는다.
        if (M != 0) { // 이거 없으면 왜 nullpointer...?
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int tmp = Integer.parseInt(st.nextToken());
                workButton[tmp] = true; // 고장났음.
            }
        }

        int answer = Math.abs(N-100);
        for (int i = 0; i <= 1000000; i++) {
            int len = moveChannel(i);
            if (len > 0) {
                int press = Math.abs(i-N);
                answer = Math.min(answer, press + len);
            }
        }
        System.out.println(answer);
    }

    // 주어진 채널로 이동하기 위해서 필요한 버튼의 개수를 반환하는 함수2
    public static int moveChannel(int channel) {
        if (channel == 0) {
            return workButton[0] ? 0 : 1;
        }

        int len = 0;
        while (channel > 0) {
            int digit = channel % 10;
            if (workButton[digit]) {
                return 0; // workButton이 null이거나 digit이 범위를 벗어나면 0 반환
            }
            len++;
            channel /= 10;
        }
        return len;
    }
}
