import java.util.*;

class Solution {
    static int startX = Integer.MAX_VALUE, startY = Integer.MAX_VALUE, endX = Integer.MIN_VALUE, endY = Integer.MIN_VALUE;
    public int[] solution(String[] wallpaper) {
        char[][] board = new char[wallpaper.length][wallpaper[0].length()];
        
        // init
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                board[i][j] = wallpaper[i].charAt(j);
            }
        }
        
        // go
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[0].length(); j++) {
                if (board[i][j] == '#') {
                    startX = Math.min(startX, i);
                    startY = Math.min(startY, j);
                    endX = Math.max(endX, i);
                    endY = Math.max(endY , j);
                }
            }
        }
        
        int[] answer = {startX, startY, endX + 1, endY + 1};
        return answer;
    }
}