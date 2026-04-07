import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] board, groupingBoard;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        groupingBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. bfs -> grouping
        int groupID = 1;
        Map<Integer, Integer> groupSizeMap = new HashMap<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    int size = bfs(i, j, groupID, visited);
                    groupSizeMap.put(groupID, size);
                    groupID++;
                }
            }
        }

        // 2. 0 -> merging
        int maxSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    Set<Integer> nearGroups = new HashSet<>();

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                            if (groupingBoard[nx][ny] > 0) {
                                nearGroups.add(groupingBoard[nx][ny]);
                            }
                        }
                    }

                    int currSize = 1;
                    for (int id : nearGroups) {
                        currSize += groupSizeMap.get(id);
                    }
                    maxSize = Math.max(maxSize, currSize);

                }
            }
        }

        System.out.println(maxSize);
    }

    public static int bfs(int x, int y, int groupID, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        groupingBoard[x][y] = groupID;

        int size = 0;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            size++;

            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (board[nx][ny] == 1 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        groupingBoard[nx][ny] = groupID;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return size;
    }

}
