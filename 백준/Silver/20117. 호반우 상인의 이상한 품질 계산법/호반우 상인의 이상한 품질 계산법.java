import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        list = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);
        int len = list.length;
        int total = 0;

        if (len % 2 == 0) {
            for (int i = N / 2; i < N; i++) {
                total += list[i] * 2;
            }
        } else {
            for (int i = N / 2 + 1; i < N; i++) {
                total += list[i] * 2;
            }
            total += list[N / 2];
        }
        System.out.println(total);

    }

}
