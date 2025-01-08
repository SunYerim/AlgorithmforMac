import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder start = new StringBuilder(br.readLine());
        StringBuilder target = new StringBuilder(br.readLine());

        while (start.length() < target.length()) {
            // 마지막 문자가 A이면
            if (target.charAt(target.length()-1) == 'A') {
                target.deleteCharAt(target.length()-1);
            } else if (target.charAt(target.length()-1) == 'B') {
                target.deleteCharAt(target.length()-1);
                target.reverse();
            }
        }

        System.out.print(start.toString().equals(target.toString()) ? 1 : 0);
    }

}