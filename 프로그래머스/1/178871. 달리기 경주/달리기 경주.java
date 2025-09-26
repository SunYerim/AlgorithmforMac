import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        Map<String, Integer> nameToRank = new HashMap<>();
        Map<Integer, String> rankToName = new HashMap<>();
        
        // init
        for (int i = 0; i < players.length; i++) {
            nameToRank.put(players[i], i + 1);
            rankToName.put(i + 1, players[i]);
        }
        
        // callings
        for (String s : callings) {
            int currRank = nameToRank.get(s);
            
            String passedName = rankToName.get(currRank - 1);
            
            nameToRank.put(s, currRank - 1);
            nameToRank.put(passedName, currRank);
            
            rankToName.put(currRank - 1, s);
            rankToName.put(currRank, passedName);
        }
        
        for (int i = 0; i < players.length; i++) {
            answer[i] = rankToName.get(i + 1);
        }
        return answer;
    }
}