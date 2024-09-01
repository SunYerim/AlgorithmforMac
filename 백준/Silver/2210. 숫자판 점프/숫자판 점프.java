
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid;
    static boolean[][] visited;
    static Set<String> resultSet;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        grid = new int[5][5];
        resultSet = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                visited = new boolean[5][5];
                backtracking(i, j, new ArrayList<>());
            }
        }

        System.out.println(resultSet.size());
    }

    private static void backtracking(int row, int col, List<Integer> path) {
        if (path.size() == 6) {
            resultSet.add(String.join("", path.stream().map(String::valueOf).toArray(String[]::new)));
            return;
        }

        path.add(grid[row][col]);

        for (int d = 0; d < 4; d++) {
            int newRow = row + dy[d];
            int newCol = col + dx[d];

            if (isValid(newRow, newCol)) {
                visited[newRow][newCol] = true;
                backtracking(newRow, newCol, new ArrayList<>(path));
                visited[newRow][newCol] = false;
            }
        }

        path.remove(path.size() - 1);
    }

    private static boolean isValid(int row, int col) {
        return row >= 0 && row < 5 && col >= 0 && col < 5;
    }

}
