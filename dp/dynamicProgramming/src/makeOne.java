/* idea
    1. 우선 계산 횟수가 최소가 되어야하므로 1을 빼주다가 5로 나눌 수 있는 순간이 되면? 5로 계속 나눠주는 게 best라고 생각함.
    2. 그렇지만, 1번과 같은 경우처럼 하기에는 4와 같은 반례도 있으므로 x
    3. 그럼 매 순간 min을 생각해주면서 계산 해줘야되는건지?
    4. 아니면 보텀업 방식대로 순간순간 횟수가 작은 걸 택해서 목표 값까지 올라가는 로직?*/
import java.util.*;
public class makeOne {
    //dp table
    public static int[] d = new int[30001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        for (int i = 2; i <=x; i++){
            // 1 빼는 경우
            d[i] = d[i-1] + 1;
            // 2 로 나누어 떨어짐
            if (i % 2 == 0){
                d[i] = Math.min(d[i], d[i/2]+1);
            }
            // 3으로
            if (i % 3 == 0){
                d[i] = Math.min(d[i], d[i/3]+1);
            }
            if (i % 5 == 0){
                d[i] = Math.min(d[i], d[i/5]+1);
            }

        }
        System.out.println(d[x]);



    }

}
