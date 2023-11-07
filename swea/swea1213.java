import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea1213 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i <= 10; i++) {
            int count = Integer.parseInt(br.readLine());
            String findString = br.readLine();
            String longString = br.readLine();
            int stringLength = longString.length();
            int findstringLength = findString.length();
            int temp = 0;

            for (int j = 0; j <= stringLength - findstringLength; j++) {
                if (longString.substring(j, j+findstringLength).equals(findString)) {
                    temp++;
                }
            }

            System.out.println("#"+i+" "+temp);

        }




    }
}
