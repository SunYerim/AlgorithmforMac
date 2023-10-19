package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1759 {
    static int L,C; // 받아오는 변수

    static char[] code; // 만들어질 수 있는 code
    static char[] alpha; // 문자 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        alpha = new char[C];
        code = new char[L];
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpha); // 입력받은 글자들을 모두 정렬시킨다.

        // 여기서 조합으로 나올 수 있는 모든 경우의 수 구한 다음  -> 모음 없는 건 delete처리 시켜서 return

        makePassword(0, 0);

    }

    // 가능한 password 만드는 메소드
    public static void makePassword(int x, int idx) {
        //
        if (idx == L) {
            if (isValid()) {
                System.out.println(code);
            }
            return;
        }

        // 아직 길이 L의 코드 길이를 만들지 않았을때,
        for (int i = x; i < C; i++) {
            code[idx] = alpha[i];
            makePassword(i+1, idx+1);
        }

    }


    // password가 최소 한 개의 모음과 두 개의 자음으로 구성된 게 맞는지 판별하는 메소드
    public static boolean isValid() {
        int moeum = 0;
        int jaeum = 0;

        // a,e,i,o,u
        for (char c : code) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                moeum++;
            } else {
                jaeum++;
            }
        }

        if (moeum >= 1 && jaeum >= 2) {
            return true;
        } else {
            return false;
        }
    }


}
