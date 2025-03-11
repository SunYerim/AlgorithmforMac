import java.util.*;

// 2중 for문 -> 시간초과
class Solution {
    static Map<Integer, Integer> map;
    public long solution(int[] weights) {
        long answer = 0;
        int[][] ratios = {{1, 1}, {1, 2}, {2, 3}, {3, 4}};
        map = new HashMap<>();
        
        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }
        
        // 해당하는 비율의 쌍 갯수 찾기
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int weight = entry.getKey();
            
            for (int[] ratio : ratios) {
                // 안 나눠지면
                if ((weight % ratio[0]) > 0) continue;
                
                int otherWeight = weight / ratio[0] * ratio[1];
                
                if (!map.containsKey(otherWeight)) continue;
                
                long count = (long) map.get(weight);
                
                // 같으면
                if (weight == otherWeight) {
                    answer += count * (count - 1) / 2;
                    continue;
                }
                
                long otherCount = (long)map.get(otherWeight);
                answer += count * otherCount;
                
            }
        }
        return answer;
    }
}