import java.util.*;

class Solution {
    static int answer;
    public int solution(String skill, String[] skill_trees) {
        answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            boolean isPossible = check(skill_trees[i], skill);
            
            if (isPossible) answer++;
        }
        return answer;
    }
    
    private static boolean check(String skill_tree, String skill) {
        boolean[] visited = new boolean[skill.length()];
        String[] characters = skill.split("");
        
        int site = 0; // 현 위치를 추적하는 변수
        
        for (char c : skill_tree.toCharArray()) {
            int idx = skill.indexOf(c);
            if (idx == -1) continue;
            
            if (idx > site) return false;
            
            site++;
        }
        return true;
    }
}