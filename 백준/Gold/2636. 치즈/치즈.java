import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* logic
1. n, m 입력받고 치즈의 상태를 입력받아 map에 저장
2. 총 걸리는 시간 저장할 count, 마지막으로 없어진 치즈의 수 저장할 lastCheese 0으로 초기화 시켜둠
3. queue에 cheese의 시작점인 [0,0]을 넣은채로 반복문을 시작함. 이때 한 타임 동안 없어지는 치즈 개수를 저장할 temp를
    0으로 초기화 시켜둠.
4. queue의 첫번째 값 꺼내 now에 저장하고 '상하좌우' 순으로 주변 탐색함. (-1 => 탐색 완!)
 1) 주변이 0이라면 (회색) queue에 추가하고 해당 위치는 -1로 바꿈.
 2) 주변이 1이라면 없어질 치즈이므로(c 표기) temp를 1 증가시키고 해당위치는 -1로 바꿈.
5. queue가 비어서 안쪽 while문이 끝나면 temp의 수를 확인하여 이번 타임에 없어진 치즈가 0개일때, 반복문 종료한 뒤
    count와 lastCheese를 출력함.
6. temp가 0이 아니라면, cheese를 모두 돌며 -1로 바꿨던 값들을 0으로 바꿔줌.
7. 값을 모두 바꾼 뒤 없어진 치즈의 수인 temp의 값을 lasCheese에 저장하고 한 타임이 지났으므로 count 1 증가시킴.
*/
public class Main {
    static int n, m, hour, lastCheese, temp;
    static int[][] map;
    static ArrayList<Integer> cheeses;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cheeses = new ArrayList<>(); // 매 시간마다 치즈 갯수 기록 용도

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        hour = 0;
        lastCheese = 0;


        while (true) {

            temp = 0;
            bfs(0, 0);
            if (temp == 0) {
                break;
            } else {
                lastCheese = temp;
                hour++;

                // 다시 돌면서 0으로 바꿔준다.
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (map[i][j] == -1) {
                            map[i][j] = 0;
                        }
                    }
                }
            }
        }

        System.out.println(hour);
        System.out.println(lastCheese);



    }

    static void bfs(int x, int y) {
        Queue<int[]> cheese = new LinkedList<>();
        cheese.offer(new int[]{x, y});


        while (!cheese.isEmpty()) {
            int[] now = cheese.poll();
            map[now[0]][now[1]] = -1;

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] == 0) {
                        cheese.offer(new int[]{nx, ny});
                        map[nx][ny] = -1;
                    }
                    if (map[nx][ny] == 1) {
                        temp+= 1;
                        map[nx][ny] = -1;
                    }
                }

            }
        }

        if (temp != 0) {
            lastCheese = temp;
            //temp = 0;
        }


    }
}