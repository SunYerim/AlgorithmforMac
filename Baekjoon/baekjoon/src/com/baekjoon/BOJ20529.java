package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ20529 {
    static String[] studentMbti;
    static int[] answer; // 나중에 출력하기 위함.
    public static void main(String[] args) throws IOException {
        // 일단 무식하게 다 돌아
        // 같은 자리에 있는 문자열이 차이가 나면 +1씩 처리해주면 됨.
        // 그리고 '세 명'을 뽑아야 한다. -> String배열을 만들어서 처리하자.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        answer = new int[T];
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            studentMbti = new String[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                studentMbti[i] = st.nextToken();
            }

            if (checkDuplicates()) {
                answer [tc-1] = 0;
            }
            else {
                int minNumber = Integer.MAX_VALUE;
                for (int j = 0; j < n-2; j++) {
                    for (int k = j+1; k < n-1; k++) {
                        for (int l = k+1; l < n; l++) {
                            int score = calculateDistance(studentMbti[j], studentMbti[k], studentMbti[l]);

                            if (minNumber > score) {
                                minNumber = score;
                            }
                        }
                    }
                }
                answer[tc-1] = minNumber;
            }
        }
        for (int i = 0; i < T; i++) {
            System.out.println(answer[i]);
        }
    }

    public static boolean checkDuplicates() {
        Map<String, Integer> countMap = new HashMap<>();
        for (String mbti : studentMbti) {
            countMap.put(mbti, countMap.getOrDefault(mbti, 0) + 1);
            if (countMap.get(mbti) >= 3) {
                return true; // 같은 MBTI 값이 3개 이상인 경우 true 반환
            }
        }
        return false; // 같은 MBTI 값이 3개 이상이 없는 경우 false 반환
    }

    // mbti두 개씩 들고가서 두 사람의 심리적 거리 계산하는 메소드
    public static int calculateDistance (String stu1, String stu2, String stu3) {
        int count = 0;
        if (stu1.equals(stu2) && stu2.equals(stu3)) {
            count = 0;
        }
        else {
            for (int i = 0; i < 4; i++) {
                if (stu1.charAt(i) != stu2.charAt(i)) {
                    count++;
                }
                if (stu1.charAt(i) != stu3.charAt(i)){
                    count++;
                }
                if (stu2.charAt(i) != stu3.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }
}
