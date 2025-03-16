import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String minkyum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        minkyum = br.readLine();


        System.out.println(minkyumToMax(minkyum));
        System.out.println(minkyumToMin(minkyum));

    }

    private static String minkyumToMax(String minkyum) {
        // k 뒤에서 끊습니다.
        StringBuilder maxNum = new StringBuilder();
        int mCount = 0;

        for (char c : minkyum.toCharArray()) {
            if (c == 'M') {
                mCount++;
            } else {
               maxNum.append(5);

               for (int i = 0; i < mCount; i++) {
                   maxNum.append(0);
               }
               mCount = 0;
            }
        }

        if (mCount != 0) {
            for (int i = 0; i < mCount; i++) maxNum.append(1);
        }

        return maxNum.toString();
    }

    private static String minkyumToMin(String minkyum) {
        // k 앞에서 끊습니다.
        StringBuilder minNum = new StringBuilder();
        int mCount = 0;

        for (char c : minkyum.toCharArray()) {
            if (c == 'M') {
                mCount++;
            } else {
                if (mCount > 0) {
                    mCount--;
                    minNum.append(1);

                    for (int i = 0; i < mCount; i++)
                        minNum.append(0);

                    mCount = 0;
                }
                minNum.append(5);
            }
        }

        if (mCount > 0) {
            mCount--;
            minNum.append(1);
            for (int i = 0; i < mCount; i++) minNum.append(0);
        }
        return minNum.toString();
    }

}
