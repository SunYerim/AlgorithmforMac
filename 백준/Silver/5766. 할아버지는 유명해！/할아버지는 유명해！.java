import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            int[] arr = new int[10001];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    arr[num]++;
                }
            }

            int firstMaxCnt = 0;
            int secondMaxCnt = 0;

            for (int i = 1; i < 10001; i++) {
                if (arr[i] > firstMaxCnt) {
                    secondMaxCnt = firstMaxCnt;
                    firstMaxCnt = arr[i];
                } else if (arr[i] > secondMaxCnt && arr[i] < firstMaxCnt) {
                    secondMaxCnt = arr[i];
                }
            }

            List<Integer> secondScores = new ArrayList<>();
            for (int i = 1; i < 10001; i++) {
                if (arr[i] == secondMaxCnt) {
                    secondScores.add(i);
                }
            }

            for (int i = 0; i < secondScores.size(); i++) {
                sb.append(secondScores.get(i)).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

}
