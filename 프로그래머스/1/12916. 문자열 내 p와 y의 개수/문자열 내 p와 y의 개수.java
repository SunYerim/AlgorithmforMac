class Solution {
    boolean solution(String s) {
        boolean answer = true;
        int pCount = 0;
        int yCount = 0;
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (tmp == 'p' || tmp=='P') {
                pCount++;
            } else if (tmp == 'y' || tmp == 'Y') {
                yCount++;
            }
        }
        
        if (pCount == yCount || (pCount == 0 && yCount==0))
            answer = true;
        else 
            answer = false;

        
        return answer;
    }
}