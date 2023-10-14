package com.Programmers;

public class PGS86051 {
    public int solution(int[] numbers) {
        int answer = 0;
        // 숫자 포함되어있나 안되있나 판단하기 위한 boolean 배열
        boolean visited[] = new boolean[10];
        for (int i = 0; i < numbers.length; i++){
            visited[numbers[i]] = true;
        }

        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                answer += i;
            }
        }
        return answer;
    }
}