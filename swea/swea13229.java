import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class swea13229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] string = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            String s = br.readLine();
            int answer = 0;
            for (int j = 0; j < string.length-1; j++) {
                if (s.equals(string[j])) {
                    answer = 7-(j+1);
                }
                else if (s.equals("SUN")) {
                    answer = 7;
                }
            }
            System.out.println("#" + i + " " + answer);
        }
    }
}
