import java.util.*;

class Solution {
    static int[] selected;
    static boolean[] visited;
    static int[] number;
    static int total;
    static String tmp;
    static Set<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        number = new int[numbers.length()];
        visited = new boolean[numbers.length()];
        
        for (int i = 0; i < numbers.length(); i++) {
            number[i] = numbers.charAt(i) - '0';
        }
        
        // 길이에 따라 다르다.
        for (int i = 1; i <= numbers.length(); i++) {
            selected = new int[i];
            dfs(0, i);
        }
        
        
        return set.size();
    }
    
    private static void dfs(int depth, int len) {
        // 기저
        if (depth == len) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                sb.append(selected[i]);
            }
            int num = Integer.parseInt(sb.toString());
            if (isPrime(num) && num > 1) {
                set.add(num);
                System.out.println("Current set of primes: " + set.toString());
            }
            return;
        }
        
        // 유도 조건
        for (int i = 0; i < number.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[depth] = number[i];
            dfs(depth+1, len);
            visited[i] = false;
        }
    }
    
    private static boolean isPrime(int x) {
    
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
    
}