import java.util.*;
import java.io.*;

class Solution {
    static char[][] map;
    static int cntO = 0, cntX = 0, empty = 0, answer;
    public int solution(String[] board) {
        answer = 0;
        // map
        map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                
                map[i][j] = c;
                switch (c) {
                    case 'O':
                        cntO++;
                        break;
                    case 'X':
                        cntX++;
                        break;
                    case '.':
                        empty++;
                        break;
                }  
            }
        }
        
        boolean isO = check('O');
        boolean isX = check('X');
        
        if (empty == 0) {
            if (cntX + 1 == cntO && !isX) return 1;
            else return 0;
        } else {
            if (!isO && !isX && cntO == cntX) return 1;
            else if (isO && !isX && cntX + 1 == cntO) return 1;
            else if (isX && !isO && cntO == cntX) return 1;
            else if (!isO && !isX && cntX + 1 == cntO) return 1;
            else return 0;
        }
        
//         return answer;
    }
    
    public static boolean check(char c) {
        // 가로, 세로
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }
        
        // 대각선
        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;
        
        return false;
    }
}