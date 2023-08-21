public class fibonacci4 {
    // dp 테이블 초기화
    public static long[] d = new long[1000];

    public static void main(String[] args) {
        d[1] = 1;
        d[2] = 1;
        int n = 50;

        // 피보나치 함수 반복문으로 구현(보텀업)
        for (int i = 3; i < n+1; i++){
            d[i] = d[i-1] + d[i-2];
        }
        System.out.println(d[n]);
    }

}
