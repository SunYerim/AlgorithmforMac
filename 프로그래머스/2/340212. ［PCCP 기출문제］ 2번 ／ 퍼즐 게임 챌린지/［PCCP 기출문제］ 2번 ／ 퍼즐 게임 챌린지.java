import java.util.*;

class Solution {
    static List<Node> list;
    static int answer;
    public int solution(int[] diffs, int[] times, long limit) {
        list = new ArrayList<>();
        
        for (int i = 0; i < diffs.length; i++) {
            list.add(new Node(diffs[i], times[i]));
        }
        
        // Collections.sort(list); // why
        
        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();
        
        while (left < right) {
            int mid = (left + right) / 2;
            long total = calculate(mid);
            
            if (limit >= total) right = mid;
            else left = mid + 1;
        }
        
        return right;
    }
    
    private static long calculate(int level) {
        // 현재 diff로 time 계산하는 로직
        long ans = 0;
        for (int i = 0; i < list.size(); i++) {
            int levelDiff = list.get(i).diff - level;
            
            if (levelDiff <= 0) ans += list.get(i).time;
            else {
                int prevTime = i == 0 ? 0 : list.get(i-1).time;
                int currTime = list.get(i).time;
                int addValue = (prevTime + currTime) * levelDiff + currTime;
                
                ans += addValue;
            }
        }
        return ans;
    }
    
    static class Node implements Comparable<Node>{
        int diff, time;
        
        public Node (int diff, int time) {
            this.diff = diff;
            this.time = time;
        }
        
        @Override
        public int compareTo (Node o) {
            return this.diff - o.diff;
        }
    }
}