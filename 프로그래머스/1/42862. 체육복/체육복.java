import java.util.*;

class Solution {
    static int[] students;
    static int answer;
    public int solution(int n, int[] lost, int[] reserve) {
        answer = 0;
        students = new int[n+1];
        Arrays.fill(students, 1);
        
        // 잃어버림
        for (int idx : lost) {
            students[idx]--;
        }
        
        // 여벌
        for (int idx : reserve) {
            students[idx]++;
        }
        
        // 빌려줍니다. -> 앞에서 부터
        for (int i = 1; i <= n; i++) {
            if (i > 1 && students[i] == 0 && students[i-1] >= 2) {
                students[i-1]--;
                students[i]++;
            } else if (i < n && students[i] == 0 && students[i+1] >= 2) {
                students[i+1]--;
                students[i]++;
            }
        }
        
        for (int i = 1; i <= n; i++) {
            if (students[i] >= 1) {
                answer++;
            }
        }
        
        return answer;
    }
}