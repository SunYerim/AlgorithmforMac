import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[][] map;
    static int[] friends;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // a-b가 친구이거나 a-c, b-c인 c가 존재해야 한다.
        // 플로이드 워셜

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        friends = new int[n];

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], INF);
        }
        // 자기 자신으로 가는 비용 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==j)
                    map[i][j] = 0;
            }
        }

        // 친구관계 설정
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                if (tmp.charAt(j) == 'Y') {
                    map[i][j] = 1;
                    map[j][i] = 1;
                }
            }
        }

        // 가장 유명한 사람의 친구 수
        for (int k = 0; k < n; k++) {
            for (int a = 0; a < n; a++) {
                if (k != a && map[k][a] == 1) {
                    friends[k]++;
                } else if (k != a) {
                    for (int b = 0; b < n; b++) {
                        // 바로 아는 사이 or 한 다리 건너서 아는 사이라면 friends의 좌표에 담으면 된다.
                        if (k!=b && a != b && map[k][b] + map[b][a] == 2) {
                            friends[k]++;
                            break;

                        }
                    }
                }

            }



        }


        Arrays.sort(friends);
        System.out.println(friends[n-1]);

    }
}