import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] selected;
    static int[] numbers;
    static int n, m, total;
    static boolean[] notSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 뽑혀야 되는 숫자 저장
        if (m != 0) {
            notSelected = new boolean[10];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int num = Integer.parseInt(st.nextToken());
                notSelected[num] = true; // 뽑혀라
            }
        }

        numbers = new int[11];
        for (int i = 0; i < 10; i++) {
            numbers[i] = i;
        }
        selected = new int[n];

        dfs(0);
        System.out.println(total);
    }

    private static void dfs(int cnt) {
        // 기저
        if (cnt == n) {
            if (m != 0) {
                for (int i = 0; i < 10; i++) {
                    // 반드시 뽑혀야 하는 수
                    if (notSelected[i]) {
                        boolean exist = false;
                        for (int j = 0; j < n; j++) {
                            if (selected[j] == i) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) return;
                    }
                }
                total++;
            }
            else if (m == 0) {
                total++;
            }
            return;
        }

        // 유도
        for (int i = 0; i < 10; i++) {
            // 여기서 뽑혔는지 확인
            selected[cnt] = i;
            dfs(cnt+1);
        }
    }
}