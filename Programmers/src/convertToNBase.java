/* logic
    1. 2~16진수 => string만들어서 진수만큼 끊어서 사용하면 될 거 같은 생각.
    2. 진법 변환 함수를 만들어서 쓰는 게 나으려나?
    3. 사실상, 10진수 -> n진법 변환방법만 알고 튜브 순서따라 answer값 만들어주면 끝인 거 같음.
    */
class convertToNBase {
    public String solution(int n, int t, int m, int p) {
        String jinsu = "0123456789ABCDEF"; // 진법에 사용되는 숫자 + 문자
        // 정답
        String answer = "";

        // 진법 변환 함수
        // 10진수 num을 n진수로 변환
        String convertToNBase = "";
        int num = 0;
        while (convertToNBase.length() < t * m) {
            String temp = "";
            //0 -> 0
            if (num == 0) {
                temp = jinsu.charAt(0) + "";
                // 진법 변환
            } else {
                int currentNum = num;
                while (currentNum > 0) {
                    temp = jinsu.charAt(currentNum % n) + temp;
                    currentNum /= n;
                }
            }
            convertToNBase += temp;
            num++;
        }

        // convertToNBased에서 튜브 순서에 따른 결과값 
        for (int i = p - 1; i < t * m; i += m) {
            answer += convertToNBase.charAt(i);
        }

        return answer;
    }
}