import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    static int T, n, w, h, answer;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            answer = Integer.MAX_VALUE;
            dfs(0, map);


            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }


    // 벽돌을 깨뜨리는 경우 -> 진행 경로에 또 깨드릴것이 있으면 연쇄 실행 시킨다.
    private static void dfs(int depth, int[][] arr) {
        // 기저
        if (answer == 0) return;
        if (countBrick(arr) == 0) {
            answer = 0;
            return;
        }
        if (depth == n) {
            answer = Math.min(answer, countBrick(arr));
            return;
        }

        // 고른다.
        for (int i = 0; i < w; i++) {
            boolean[][] visited = new boolean[h][w];
            int[][] tmp = copyWall(arr);
            boolean flag = false;
            // 위에서부터 벽돌을 찾아서 부순다.
            for (int j = 0; j < h; j++) {
                if (arr[j][i] > 0) {
                    removeBrick(arr, j, i, tmp, visited);
                    downBrick(tmp);
                    // 한번만 부숨
                    flag = true;
                    break;
                }
            }
            if (flag) {
                dfs(depth+1, tmp);
            }
        }
    }

    // 배열 복사하는 메서드
    private static int[][] copyWall(int[][] arr) {
        int[][] tmp = new int[h][w];
        for (int i = 0; i < h; i++) {
            System.arraycopy(arr[i], 0, tmp[i], 0, w);
        }
        return tmp;
    }

    // 벽돌 갯수 세아리는 메서드
    private static int countBrick(int[][] walls) {
        int ans = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (walls[i][j] > 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 벽돌을 아래쪽으로 누르는 메서드
    private static void downBrick(int[][] arr) {
        // 열 단위로 처리한다.
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (arr[j][i] > 0)stack.push(arr[j][i]);
                arr[j][i] = 0;
            }
            int idx = h-1;
            while (!stack.isEmpty()) {
                arr[idx--][i] = stack.pop();
            }
        }
    }

    // 벽돌을 제거하는 메서드
    // tmp -> 제거한 결과를 저장, arr에서 뭘 제거할지 찾음
    private static void removeBrick(int[][] arr, int x, int y, int[][] tmp, boolean[][] visited) {
        // 4방향으로 메서드 실행됨
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < arr[x][y]; k++) {
                int nx = x + dx[i]*k;
                int ny = y + dy[i]*k;
                if (nx>=0 && ny>=0 && nx<h&&ny<w&& !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    tmp[nx][ny]--;
                    if (arr[nx][ny] > 0) {
                        tmp[nx][ny] = 0;
                        removeBrick(arr, nx, ny, tmp, visited);
                    }
                }
            }
        }
    }



}
