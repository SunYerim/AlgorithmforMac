package com.Programmers;

/*logic
    1. 각 손님이 주문한 메뉴 string을 분리해 course에 나와있는 갯수들만큼 조합 생성
    2. Hashmap처리
    => 주요 로직은 combination, hashmap사용*/
import java.util.*;

class Solution {
    Map<String, Integer> combiMap;
    public String[] solution(String[] orders, int[] course) {
        List<String> answerList = new ArrayList<>();

        for (int c : course) {
            combiMap = new HashMap<>();
            for (String order : orders) {
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combination(arr, "", 0, c);
            }

            if (combiMap.size() != 0) {
                int max = Collections.max(combiMap.values());
                if (max >= 2) {
                    for (String key : combiMap.keySet()) {
                        if (combiMap.get(key) == max)
                            answerList.add(key);
                    }
                }
            }
        }
        String[] answer = new String[answerList.size()];
        answerList.toArray(answer);
        Arrays.sort(answer);

        return answer;
    }

    public void combination(char[] arr, String combi, int start, int r) {
        if (r == 0) {
            combiMap.put(combi, combiMap.getOrDefault(combi, 0) + 1);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            combination(arr, combi + arr[i], i+1, r-1);
        }
    }
}