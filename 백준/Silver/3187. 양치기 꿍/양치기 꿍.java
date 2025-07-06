import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, totalSheep = 0, totalFoxes = 0;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        // bfs
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && board[i][j] != '#') {
                    int[] curr = bfs(i, j);

                    if (curr[0] > curr[1]) {
                        totalSheep += curr[0];
                    } else {
                        totalFoxes += curr[1];
                    }

                }
            }
        }

        System.out.println(totalSheep + " " + totalFoxes);

    }

    public static int[] bfs(int y, int x) {
        Queue<Space> q = new LinkedList<>();
        int currSheep = 0, currFoxes = 0;

        q.add(new Space(y, x));
        visited[y][x] = true;

        if (board[y][x] == 'k') {
            currSheep++;
        } else if (board[y][x] == 'v') {
            currFoxes++;
        }

        while (!q.isEmpty()) {
            Space space = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = space.y + dy[i];
                int nx = space.x + dx[i];

                if (ny >= 0 && ny < R && nx >= 0 && nx < C && !visited[ny][nx]
                    && board[ny][nx] != '#') {
                    visited[ny][nx] = true;

                    if (board[ny][nx] == 'k') {
                        currSheep++;
                    } else if (board[ny][nx] == 'v') {
                        currFoxes++;
                    }

                    q.add(new Space(ny, nx));
                }
            }
        }

        return new int[]{currSheep, currFoxes};

    }

    static class Space {

        int y, x;

        public Space(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
