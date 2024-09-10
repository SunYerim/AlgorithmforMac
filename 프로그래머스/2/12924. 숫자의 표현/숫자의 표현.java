class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int m = 1; m * (m-1) / 2 < n; m++) {
            if ((n - m * (m - 1) / 2) % m == 0) {
                answer++;
            }
        }
        return answer;
    }
}