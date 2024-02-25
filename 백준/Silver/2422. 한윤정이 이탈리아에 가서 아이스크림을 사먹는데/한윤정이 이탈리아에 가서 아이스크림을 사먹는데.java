import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, ans;
    static int[] selected, icecream;
    static boolean[][] notSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        icecream = new int[n+1]; // 0번 인덱스는 사용 안 함.
        selected = new int[3];
        //notSelected = new int[m][2];
        notSelected = new boolean[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            icecream[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < 2; j++) {
//                notSelected[i][j] = Integer.parseInt(st.nextToken());
//            }
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            notSelected[a][b] = true;
            notSelected[b][a] = true;
        }

        combination(0, 1);
        System.out.println(ans);
    }

    private static void combination(int cnt, int start) {
        // 기저
        if (cnt == 3) {
            // 고른 조합중에 뽑히면 안되는 조합이 있는지 확인
            boolean isCorrect = true;
            // selected 배열의 원소 3개 중에서
            // notSelected의 i행에 있는 숫자 두 개 모두 포함되어있으면 isCorrect false처리
            if (notSelected[selected[0]][selected[1]] || notSelected[selected[0]][selected[2]] || notSelected[selected[1]][selected[2]]) {
                isCorrect = false;
            }
            
            if (isCorrect){
                ans++;
            }
//            for (int i = 0; i < m; i++) {
////
//
//            }
            //if (isCorrect) ans++;
            return;
        }
        // 유도
        for (int i = start; i <= n; i++) {
            selected[cnt] = i;
            combination(cnt+1, i+1);
        }
    }
}