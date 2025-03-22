import java.util.*;

class Solution {
    static HashSet<String> jewel; // 보석 개수
    static int[] answer;
    static int cnt;
    static Map<String, Integer> map;
    public int[] solution(String[] gems) {
        // 1. 보석 개수 구하기
        jewel = new HashSet<>();
        for (String gem : gems) {
            jewel.add(gem);
        }
        cnt = jewel.size();
        answer = new int[2];
        
        // 투포인터
        int left = 0;
        int right = 0;
        int len = Integer.MAX_VALUE;
        map = new HashMap<>();
        
        while (right < gems.length) {
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
            
            // 구간 내 보석 개수 중복되는 경우 start 이동
            while (map.get(gems[left]) > 1) {
                map.put(gems[left], map.get(gems[left]) - 1);
                left++;
            }
            
            // 같으면
            if (map.size() == cnt && len > (right - left)) {
                len = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
            right++;
        }
        return answer;
    }
}