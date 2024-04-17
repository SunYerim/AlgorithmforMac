class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int len = phone_number.length();
        for (int i = 0; i < len-4; i++) {
            answer += "*";
        }
        for (int i = 0; i < 4; i++) {
            answer += Character.toString(phone_number.charAt(len-4+i));
        }
        return answer;
    }
}