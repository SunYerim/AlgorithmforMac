import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 소수 판별 함수
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false; // 나누어 떨어지면 소수가 아님
            }
        }
        
        return true; // 나누어 떨어진 적이 없으면 소수임
    }
}