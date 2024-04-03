import java.util.*;
/* logic
    1. hashmap 사용
    2. participant -> true처리 (put)
    3. completion -> false처리 (replace)
    4. 배열 변환할때 true인 것만 return*/
class Solution {
    static HashMap<String, Integer> marathon;
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        marathon = new HashMap<>();
        // participant 길이 만큼 -> 동명이인이 있을 수도 있다.
        for (String p : participant) {
            marathon.put(p, marathon.getOrDefault(p, 0) + 1);
        }
        // completion 길이 만큼 완주했으면 -1 처리
        for (String c : completion) {
            marathon.put(c, marathon.get(c) - 1);
        }
        
        Object[] keyArr = marathon.keySet().toArray();
        
        for (Object s : keyArr) {
            if (marathon.get(s) != 0) {
                answer = (String) s;
            }
        }
        return answer;
    }
}