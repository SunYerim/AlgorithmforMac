import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* logic
    1. L개의 소문자들 중에서 최소 한 개의 모음 & 최소 두 개의 자음
        => 전체 경우에서 모음 하나도 없는거 재낌.
        => or 조합 만들고 boolean메서드 선언해서 거르기.*/
public class Main {
    static char[] selected;
    static char[] alpha;
    static int l, c;
    static boolean[] visited;
    static char[] moeum = new char[]{'a','e','i','o','u'};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        selected = new char[l];
        alpha = new char[c];
        visited = new boolean[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpha);

        dfs(0, 0);

        System.out.println(sb);
    }


    // 조합
    private static void dfs(int depth, int cnt) {
        // 기저
        if (depth == l) {
            if (isValid(selected)) {
                for (int i = 0; i < selected.length; i++) {
                    sb.append(selected[i]);
                }
                sb.append("\n");
            }
            return;
        }

        // 뽑는다.
        for (int i = cnt; i < c; i++) {
            selected[depth] = alpha[i];
            dfs(depth+1, i+1);
        }

    }

    private static boolean isValid(char[] tmp) {
        int mo = 0;
        int ja = 0;
        for (int i = 0; i < tmp.length; i++) {
            boolean isMoeum = false;
            for (int j = 0; j < 5; j++) {
                if (tmp[i] == moeum[j]) {
                    isMoeum = true;
                    break;
                }
            }
            if (isMoeum) mo++;
            else ja++;
        }

        if (mo >= 1 && ja >= 2)
            return true;
        // 충족 안 되면
        else return false;
    }

}