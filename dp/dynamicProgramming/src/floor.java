/*logic
    1. 우선 노가다성으로 접근
    2. 타일이 비어있는 상태에서 채울 수 있는 경우의 수를 생각하자니 로직이 떠오르지가 않음.
    3. 그래서 역으로 -> 타일이 꽉차 있는 상태에서, 세로 한 줄씩 비워가면? => 2 * N이라고 고정되어있음. 세로 길이가.
    4. d[0] = 0, d[1] = 1, d[2] = 3, d[3] = 5
    5. 처음에 d[2] = 5 아닌가? 라는 생각을 했었는데, 책 보니까 앞서 고려해준 상황이라 중복 제거하고 들어간다함.*/
import java.util.*;
public class floor {
    public static int[] d = new int[1000];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // dp table -> 0: 0, 1: 1, 2: 3, 3: 5 ...
        d[1] = 1;
        d[2] = 3;
        for (int i = 3; i <= n ; i++){
            d[i] = (d[i-1] + 2 * d[i-2]) % 796796;
        }
        System.out.println(d[n]);
    }

}
