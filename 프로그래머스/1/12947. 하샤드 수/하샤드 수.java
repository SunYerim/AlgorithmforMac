class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int total = 0;
        String tmp = String.valueOf(x);
        for (int i = 0; i < tmp.length(); i++) {
            total += tmp.charAt(i)-'0';
        }
        if (x % total == 0) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
}