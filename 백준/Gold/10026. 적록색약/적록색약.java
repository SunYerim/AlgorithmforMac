import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static char[][] map;
    static boolean[][][] visited;
    static int numberOfArea1, numberOfArea2;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        // dfs
        // 적록색약과 아닌 사람이 봤을 때의 영역 개수를 구분하여 출력 -> visited배열을 3차원으로 확장해서
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N][2]; // 마지막 요소 0번째 인덱스는 정상, 1번째 인덱스는 적록색약
        for (int i = 0; i < N; i++) {
            String string = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = string.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j][0]) {
                    dfs1(i, j, map[i][j], N, 0);
                    numberOfArea1++;
                }
                if (!visited[i][j][1]) {
                    dfs2(i, j, map[i][j], N, 1);
                    numberOfArea2++;
                }
            }
        }

        System.out.println(numberOfArea1+" "+numberOfArea2);
        
    }

    public static void dfs1(int x, int y, char color, int len, int type) {
        // 정상
        visited[x][y][type] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //범위 내에 위치해있으면,
            if (nx >= 0 && nx < len && ny >= 0 && ny < len) {
                if (!visited[nx][ny][0] && map[nx][ny] == color) {
                    dfs1(nx, ny, color, len, type);
                    //return true;
                }
            }
        }
    }

    public static void dfs2(int x, int y, char color, int len, int type) {

        visited[x][y][type] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < len && ny >=0 && ny < len) {
                if (!visited[nx][ny][1] && isSameColor(map[nx][ny], color, type)) {
                    dfs2(nx, ny, color, len, type);

                }
            }
        }

    }

    public static boolean isSameColor(char c1, char c2, int type) {
        if (type == 1) {
            return (c1 == 'R' && c2 == 'G' || c1 == 'G' && c2 == 'R' || c1 == c2);
        } else {
            return c1!=c2;
        }
    }
}