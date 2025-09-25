import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int currHealth = health;
        int successCnt = 0;
        int attackIdx = 0;
        
        int finalTime = attacks[attacks.length - 1][0];
        
        for (int t = 1; t <= finalTime; t++) {
            boolean isAttacked = false;
            if (attackIdx < attacks.length && t == attacks[attackIdx][0]) isAttacked = true;
            
            if (isAttacked) {
                currHealth -= attacks[attackIdx][1];
                successCnt = 0;
                attackIdx++;
                
                if (currHealth <= 0) return -1;
            } else {
                currHealth += bandage[1];
                successCnt++;
                
                if (successCnt == bandage[0]) {
                    currHealth += bandage[2];
                    successCnt = 0;
                }
                
                if (currHealth > maxHealth) {
                    currHealth = maxHealth;
                }
            }
        }
        return currHealth;
    }
}