import java.util.*;

class Solution {
    static List<int[]> list;
    public int[][] solution(int n) {
        list = new ArrayList<>();
        // 재귀
        recur(n, 1, 3, 2); // n개 from - to - mid
        
        int[][] answer = new int[list.size()][2];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public static void recur(int n, int from, int to, int mid) {
        // 원판 한 개면
        if (n == 1) {
            list.add(new int[]{from, to});
            return;
        }
        // from -> mid
        recur(n-1, from, mid, to);
        
        // from -> to
        list.add(new int[]{from, to});
        
        // mid -> to
        recur(n-1, mid, to, from);
    }
}