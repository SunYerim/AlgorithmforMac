import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            boolean flag = true;
            
            int hour = schedules[i] / 100;
            int minute = schedules[i] % 100;
            
            int goTimeMinute = minute + 10;
            if (goTimeMinute >= 60) {
                hour++;
                goTimeMinute -= 60;
            }
            int goTime = hour * 100 + goTimeMinute;
            
            int currDay = startday;
            
            for (int j = 0; j < timelogs[i].length; j++) {
                if (currDay == 6 || currDay == 7) {
                    currDay = (currDay % 7) + 1;
                    continue;
                }
                
                if (timelogs[i][j] > goTime) {
                    flag = false;
                    break;
                }
                
                currDay = (currDay % 7) + 1;
            }
            
            if (flag) answer++;
        }
        return answer;
    }
}