import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        // 2원과 5원짜리로만 거스름돈
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int count = 0; // 동전 개수
        int tmp = 0;

        while (true) {
            if (money % 5 == 0){
                count += money/5;

                System.out.println(count);
                break;
            } else {
                money -= 2;
                count++;
            }
            if (money < 0) {
                System.out.println(-1);
                break;
            }
            
            
        }



    }
}