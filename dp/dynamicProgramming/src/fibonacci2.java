
import java.util.*;
public class fibonacci2 {
    // 재귀적인 방법
    // 한 번 계산된 결과를 메모이제이션하기 위한 리스트 초기화
    public static long[] d = new long[100];

    // 피보나치 함수를 재귀함수로 구현 (탑다운 다이나믹 프로그래밍)
    public static long fibo (int x){
        if (x==1 || x==2){
            return 1;
        }
        if (d[x] != 0) {
            return d[x];
        }
        d[x] = fibo(x-1) + fibo(x-2);

        return d[x];

    }

    public static void main(String[] args) {
        System.out.println(fibo(50));
    }
}

