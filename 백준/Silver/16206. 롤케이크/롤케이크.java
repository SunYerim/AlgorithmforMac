import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> tenDiv = new ArrayList<>();
        List<Integer> others = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num % 10 == 0) {
                tenDiv.add(num);
            } else {
                others.add(num);
            }
        }

        Collections.sort(tenDiv);
        Collections.sort(others);

        int result = 0;

        // 10의 배수부터
        for (int cake : tenDiv) {
            int pieces = cake / 10;

            int cuts = pieces - 1;

            if (M >= cuts) {
                result += pieces;
                M -= cuts;
            } else {
                // 자를만큼
                result += M;
                M = 0;
                break;
            }
        }

        // 10의 배수가 아닌
        for (int cake : others) {
            int pieces = cake / 10;

            if (M >= pieces) {
                result += pieces;
                M -= pieces;
            } else {
                result += M;
                M = 0;
                break;
            }
        }

        System.out.println(result);


    }

}
