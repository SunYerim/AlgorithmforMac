import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int[] students;
    static ArrayList<Integer> two;
    public static void main(String[] args) throws IOException {
        students = new int[31];
        two = new ArrayList<Integer>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 28; i++) {
            int num = Integer.parseInt(br.readLine());
            students[num] = 1;
        }

        for (int i = 1; i <= 30; i++) {
            if (students[i] == 0) {
                two.add(i);
            }
        }
        Collections.sort(two);
        System.out.println(two.get(0));
        System.out.println(two.get(1));
    }

}