import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length + 1];
        List<Integer> groupSizes = new ArrayList<>();
        
        for (int i = 0; i < cards.length; i++) {
            int boxNumber = i + 1;
            
            if (!visited[boxNumber]) {
                int groupSize = 0;
                int currBox = boxNumber;
                
                while (!visited[currBox]) {
                    visited[currBox] = true;
                    groupSize++;
                    currBox = cards[currBox - 1];
                }
                groupSizes.add(groupSize);
            }
        }
        
        Collections.sort(groupSizes, Collections.reverseOrder());
        
        if (groupSizes.size() >= 2) {
            return groupSizes.get(0) * groupSizes.get(1);
        } else {
            return 0;
        }
        
    }
    
}