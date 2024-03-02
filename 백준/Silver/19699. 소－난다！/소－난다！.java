import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] cowWeight;
    static int[] selectedCow;
    static boolean[] isSelected;
    static HashSet<Integer> cows; // 최종 판별 조건에 걸린 놈들을 담기위한 용도
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cowWeight = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cowWeight[i] = Integer.parseInt(st.nextToken());
        }

        selectedCow = new int[m];
        isSelected = new boolean[n];
        cows = new HashSet<>();

        dfs(0, 0);

        ArrayList<Integer> list = new ArrayList<>(cows);
        Collections.sort(list);
        if (list.isEmpty())
            System.out.println(-1);
        else {
            // hashset -> list 변환
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i)+" ");
            }
        }



    }


    private static void dfs(int depth, int start) {
        // 기저
        if (depth == m) {
            // 소수 판별
            int total = 0;
            for (int i = 0; i < selectedCow.length; i++) {
                total += selectedCow[i];
            }
            if (isPrime(total))
                cows.add(total);
            return;
        }

        for (int i = start; i < n; i++) {
            selectedCow[depth] = cowWeight[i];
            dfs(depth+1, i+1);
        }
    }


    private static boolean isPrime(int x) {
        // 소수 판별
        for (int i = 2; i < Math.abs(x); i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }
}