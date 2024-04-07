import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* logic
    1. dfs (재귀) -> 0 채우고 돌리고 1 채우고 돌리고
        - 기저 조건을 ... -> cnt == k-1 ?
        - 처음부터 약품 투입하지 않고도 성능통과 가능하면 0 출력 (k가 1이거나 check()돌렸을때 바로 true 반환하면)
    2. check() 메서드 -> k개 충족
*/
public class Solution {
    static int T, d, w, k, ans, change;
    static int[][] map;
    static int[][] copyMap;
    static int[] pickNum; // 약품 투입할 행 저장
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new int[d][w];
            copyMap = new int[d][w];
            pickNum = new int[d];

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    copyMap[i][j] = map[i][j]; // 원본데이터 저장시켜둠.
                }
            }

            ans = Integer.MAX_VALUE;

            // 바로 통과되는 조건
            if (k == 1 || check()) {
                sb.append(0).append("\n");
                continue;
            }
            // 바로 통과 안 되면
            change = 0;
            dfs(0);
            sb.append(ans).append("\n");

        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth) {
        if (depth == d) {
            // 현재 저장된 정답보다 약품을 처리해야하는 행의 개수가 더 많은경우
            if (change >= ans) {
                return;
            }
            // 약품 투입
            for (int i = 0; i < d; i++) {
                // 0: 약품 처리가 x
                if (pickNum[i] != 0) {
                    int changeType = pickNum[i]-1;
                    for (int j = 0; j < w; j++) {
                        map[i][j] = changeType;
                    }
                }
            }

            // 검사 -> 성립하면
            if (check()) {
                ans = change < ans ? change : ans;
            }

            // 본래 상태로 되돌린다.
            for (int i = 0; i < d; i++) {
                if (pickNum[i] != 0) {
                    for (int j = 0; j < w; j++) {
                        map[i][j] = copyMap[i][j];
                    }
                }
            }
            return;
        }

        // 0: 약품 투여 x, 1 : a, 2 : b
        for (int i = 0; i < 3; i++) {
            // 해당 행이 약품을 처리하지 않았고, 약품을 투여해야하는 경우
            if (i != 0 && pickNum[depth] == 0) {
                change++;
            }
            // 해당 행에 약품을 투여하지 않고, 약품을 처리했던 경우
            if (i == 0 && pickNum[depth] != 0) {
                change--;
            }

            pickNum[depth] = i; // 중복 순열
            dfs(depth+1);
        }


    }

    // 모든 세로 줄이 k개 만족하는지
    private static boolean check() {
        // a랑 b랑 구분해서 , 열 단위로
        for (int c = 0; c < w; c++) {
            int r = 0;
            boolean flag = false;

            // 두께만큼 반복한다.
            while (r < d) {
                int aCount = 0;
                int bCount = 0;

                // 연속되는 a
                while (r < d && map[r][c] == 0) {
                    aCount++;
                    r++;
                }
                if (aCount >= k) {
                    flag = true;
                    break;
                }

                // 연속되는 b
                while (r < d && map[r][c] == 1) {
                    bCount++;
                    r++;
                }
                if (bCount >= k) {
                    flag = true;
                    break;
                }
            }

            // 다 순회후
            if (!flag) return false;
        }
        return true;
    }

}