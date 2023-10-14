package com.Programmers;

import java.util.Arrays;
import java.util.ArrayList;
public class PGS68644 {
    public int[] solution(int[] numbers) {
        int[] answer = {};
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {
                int number = numbers[i] + numbers[j];
                if (duplicateCheck(result, number)) {
                    result.add(numbers[i] + numbers[j]);
                }


            }
        }

        // ArrayList를 배열로 변환
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        Arrays.sort(answer);
        return answer;
    }

    private boolean duplicateCheck(ArrayList<Integer> num, int k) {
        for (int i = 0; i < num.size(); i++) {
            if (num.get(i) == k){
                return false;
            }
        }
        return true;
    }
}