import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // init
        int videoLenSec = timeToSeconds(video_len);
        int posSec = timeToSeconds(pos);
        int opStartSec = timeToSeconds(op_start);
        int opEndSec = timeToSeconds(op_end);
        
        int currPos = posSec;
        
        
        // go
        for (String command : commands) {
            // 오프닝 건너뛰기
            if (currPos >= opStartSec && currPos <= opEndSec) {
                currPos = opEndSec;
            }
            
            // 명령
            if (command.equals("next")) {
                currPos += 10;
            } else if (command.equals("prev")) {
                currPos -= 10;
            }
            
            if (currPos < 0) currPos = 0;
            if (currPos > videoLenSec) currPos = videoLenSec;
        }
        
        // final
        if (currPos >= opStartSec && currPos <= opEndSec) {
            currPos = opEndSec;
        }
        
        return secondsToTime(currPos);
    }
    
    public static int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }
    
    public static String secondsToTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}