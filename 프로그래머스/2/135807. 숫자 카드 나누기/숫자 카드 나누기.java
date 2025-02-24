import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        // 최댓값
        int maxNum = Math.max(arrayA[0], arrayB[0]);
        
        // 하나씩 낮추면서 실행
        for (int i = maxNum; i > 1; i--) {
            // a는 되고 b는 안 될 때
            if (isDivision(arrayA, i)) {
                if (isNotDivision(arrayB, i)) return i;
            }
            
            // b는 되고 a는 안 될 떄
            else if (isDivision(arrayB, i)) {
                if (isNotDivision(arrayA, i)) return i;
            }
            
            
        }
        return 0;
    }
    
    // 전체 나눌 수 있는가
    private static boolean isDivision(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % n != 0) return false;
        }
        return true;
    }
    
    // 전체를 나눌 수 없는가
    private static boolean isNotDivision(int[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % n == 0) return false;
        }
        return true;
    }
}