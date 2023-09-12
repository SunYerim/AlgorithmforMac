/* logic
    1. n -> k진수, String형으로
    2. 사실상 소수를 판별하는 게 0을 기준으로 있는 문자(숫자)만 뽑아오면 되는 거 같음
    3. 소수 판별함수를 돌려서 result를 return 시킨다. isprime함수
*/

class ktoIsprime {
    // 소수 판별함수 -> true, false
    public boolean isprime(long n){
        if(n <= 1)
            return false;
        else if(n == 2)
            return true;
        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0)
                return false;
        return true;
    }
    // k진수로 만든다.
    public String toKnum(int n, int k) {
        String res = "";
        while(n > 0) {
            res = n % k + res;
            n /= k;
        }
        return res;
    }
    public int solution(int n, int k) {
        int answer = 0, i, j;
        String s = toKnum(n,k);
        for(i = 0; i < s.length(); i = j) {
            for(j = i + 1; j < s.length() && s.charAt(j) != '0'; j++);
            // string -> long
            if(isprime(Long.parseLong(s.substring(i,j))))
                answer++;
        }
        return answer;
    }
}