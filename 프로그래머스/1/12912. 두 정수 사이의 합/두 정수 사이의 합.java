class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        int start = 0, end = 0;
        if (a > b) {
            start = b;
            end = a;
            answer = b;
        } else if (a < b){
            start = a;
            end = b;
            answer = a;
        } else {
            answer = a;
        }
        
        int idx = start;
        for (int i = 1; i <= end-start; i++) {
            answer += (++idx);
            
        }
        
        return answer;
    }
}