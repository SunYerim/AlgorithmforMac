import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] initialStates;
    static int[][] currentStates;
    static boolean possible;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            initialStates = new int[6][3];
            currentStates = new int[6][3];
            possible = false;

            int totalGames = 0; // 총 게임 수
            int totalDraws = 0; // 총 무승부 횟수

            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                initialStates[j][0] = Integer.parseInt(st.nextToken());
                initialStates[j][1] = Integer.parseInt(st.nextToken());
                initialStates[j][2] = Integer.parseInt(st.nextToken());

                // 각 팀의 총 경기 횟수가 5회인지를 확인
                if (initialStates[j][0] + initialStates[j][1] + initialStates[j][2] != 5) {
                    flag = false;
                }
                totalGames += initialStates[j][0] + initialStates[j][1] + initialStates[j][2];
                totalDraws += initialStates[j][1];
            }

            if (totalGames != 30) {
                possible = false;
            } else if (totalDraws % 2 != 0) {
                possible = false;
            } else if (flag) {
                currentStates = copyArr(initialStates);
                dfs(0, 1);
            }

            sb.append(possible ? "1 " : "0 ");

        }
        System.out.print(sb.toString());
    }

    public static void dfs(int team1, int team2) {
        if (possible) {
            return;
        }

        // 기저
        if (team1 == 5) {
            boolean allZero = true;
            for (int i = 0; i < 6; i++) {
                if (currentStates[i][0] != 0 || currentStates[i][1] != 0
                    || currentStates[i][2] != 0) {
                    allZero = false;
                    break;
                }
            }
            if (allZero) {
                possible = true;
            }
            return;
        }

        // 유도
        int nextTeam1 = team1;
        int nextTeam2 = team2 + 1;
        if (nextTeam2 > 5) {
            nextTeam1++;
            nextTeam2 = nextTeam1 + 1;
        }

        // 1. team1 승리, team2 패배
        if (currentStates[team1][0] > 0 && currentStates[team2][2] > 0) {
            currentStates[team1][0]--;
            currentStates[team2][2]--;
            dfs(nextTeam1, nextTeam2);
            currentStates[team1][0]++;
            currentStates[team2][2]++;
        }

        // 2. 무승부
        if (currentStates[team1][1] > 0 && currentStates[team2][1] > 0) {
            currentStates[team1][1]--;
            currentStates[team2][1]--;
            dfs(nextTeam1, nextTeam2);
            currentStates[team1][1]++;
            currentStates[team2][1]++;
        }

        // 3. team1 패배, team2 승리
        if (currentStates[team1][2] > 0 && currentStates[team2][0] > 0) {
            currentStates[team1][2]--;
            currentStates[team2][0]--;
            dfs(nextTeam1, nextTeam2);
            currentStates[team1][2]++;
            currentStates[team2][0]++;
        }
    }

    public static int[][] copyArr(int[][] arr1) {
        int[][] arr2 = new int[6][3];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                arr2[i][j] = arr1[i][j];
            }
        }
        return arr2;
    }

}
