// fibonacci
class Solution {
    static long[] fibo;
    public long solution(int n) {
        int answer = 0;
        fibo = new long[n+1];
        fibo[0] = 1L % 1000000007;
        fibo[1] = 1L % 1000000007;
        for (int i = 2; i <= n; i++) {
            fibo[i] = (fibo[i-1] + fibo[i-2]) % 1000000007;
        }
        return fibo[n];
    }
}