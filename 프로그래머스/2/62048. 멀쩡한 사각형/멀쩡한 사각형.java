import java.util.*;
import java.io.*;

// 
class Solution {
    public long solution(int w, int h) {
        long W = (long) w;
        long H = (long) h;
        long answer = W * H - (W + H - gcd(w, h));
        
        return answer;
    }
    
    public static int gcd (int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
    
}



