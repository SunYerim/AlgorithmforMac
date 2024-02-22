import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    static int n;
    static int[] persons;
    static long money;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        persons = new int[n+1]; // 1번 인덱스부터
        // 손님의 순서를 바꿨을 때 강호가 받을 수 있는 팁

        for (int i = 1; i <= n; i++) {
            persons[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(persons);

        // 뒤에서부터 가면 됨.

        for (int i = n; i > 0; i--) {
            int tmp = persons[i]-(persons.length-(i)-1);
            if (tmp < 0) continue;


            money += tmp;
        }

        System.out.println(money);

    }

}