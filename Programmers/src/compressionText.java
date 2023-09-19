/*logic
    1. 우선 사전은 초기화
    2. while문 두 번 사용해서 조건에 맞게 코드 돌려주고, 마지막에 w + 1 길이만큼해서 dictionary에 추가해준다.
    3. 그리고, hashmap에 특정 key 존재 여부 확인 -> containsKey()*/

import java.util.*;
class compressionText {
    public int[] solution(String msg) {
        // 사전
        HashMap<String, Integer> dictionary = new HashMap<>();
        // 영문대문자 사전에 추가
        int dictIndex = 1;
        for (int i = 'A'; i <= 'Z'; i++) {
            dictionary.put(String.valueOf((char)i), dictIndex++);
        }

        //  answer
        int[] answer;
        ArrayList<Integer> ans = new ArrayList<>();

        // 첫 문자부터 시작
        int index = 0;
        while (index < msg.length()){
            String w = "";
            while (index < msg.length()){
                // 한글자씩 늘려가면서 확인.
                if (!dictionary.containsKey(w+msg.charAt(index))){
                    break;
                } else {
                    w += msg.charAt(index);
                }
                index++;
            }
            ans.add(dictionary.get(w));
            // 뒷 글자 더해서 사전에 추가
            if (index < msg.length()) {
                dictionary.put(w+msg.charAt(index), dictIndex++);
            }

        }
        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }
}