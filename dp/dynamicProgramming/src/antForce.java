/*logic
    1. 우선 입력 받을 데이터들 다 입력받고
    2. 현재 식량창고와 인접한 칸만 털건지, 현재 식량창고 + 한칸 떨어져있는 것 털건지?
    3. 어렵다 ....*/
import java.util.*;
public class antForce {
    // dp table
   public static int[] d = new int[1000];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        // 보텀업 -> 반복문 이용
        d[0] = arr[0];
        d[1] = arr[1];
        for (int i = 2; i < n; i++){
            d[i] = Math.max(d[i-1], d[i-2] + arr[i]);
        }
        System.out.print(d[n-1]);

    }

}
