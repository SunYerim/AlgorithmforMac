import java.util.*;

class Solution {
    static char[][] board;
    static int currX, currY, boardRows, boardCols;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        board = new char[park.length][park[0].length()];
        boardRows = park.length;
        boardCols = park[0].length();
        
        // init
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                board[i][j] = park[i].charAt(j);
                if (board[i][j] == 'S') {
                    currX = i;
                    currY = j;
                }
            }
        }
        
        // go
        for (int i = 0; i < routes.length; i++) {
            String[] direction = routes[i].split(" ");
            String dir = direction[0];
            int cnt = Integer.parseInt(direction[1]);
            
            go(dir, cnt);
        }
        
        answer[0] = currX;
        answer[1] = currY;
        
        return answer;
    }
    
    public static void go(String dir, int count) {
        int nextX = currX;
        int nextY = currY;
        
        boolean canMove = true;
        
        for (int i = 0; i < count; i++) {
            if (dir.equals("N")) {
                nextX--;
            } else if (dir.equals("S")) {
                nextX++;
            } else if (dir.equals("W")) {
                nextY--;
            } else if (dir.equals("E")) {
                nextY++;
            }
            
            if (nextX < 0 || nextX >= boardRows || nextY < 0 || nextY >= boardCols) {
                canMove = false;
                break;
            }
            
            if (board[nextX][nextY] == 'X') {
                canMove = false;
                break;
            }
        }
        
        if (canMove) {
            currX = nextX;
            currY = nextY;
        }
        
    }
}