class 대문자로바꾸기 {
    public String solution(String myString) {
        String answer = "";
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            // 문자를 대문자로 변환하여 answer에 추가
            answer += Character.toUpperCase(c);
        }
        return answer;
    }
}