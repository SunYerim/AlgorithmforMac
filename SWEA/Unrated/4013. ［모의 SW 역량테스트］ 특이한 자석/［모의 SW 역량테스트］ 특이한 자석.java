import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static ArrayDeque<Integer>[] magnet = new ArrayDeque[4];
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            ans = 0;
            int k = Integer.parseInt(br.readLine());

            sb.append("#").append(tc).append(" ");

            for (int i = 0; i < 4; i++) {
                magnet[i] = new ArrayDeque<>();
            }

            // 입력 받기
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < 8; j++) {
                    magnet[i].add(Integer.parseInt(st.nextToken()));
                }
            }

            // k번만큼 회전 시작
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int number = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                // 명령이 입력될 때마다 메서드 실행
                roundMagnet(number, dir);
            }


            // 극성에 따라, 덱의 첫번째 요소를 뽑아내서 점수를 계산함.
            if (magnet[0].getFirst() == 1) ans+=1;
            if (magnet[1].getFirst() == 1) ans += 2;
            if (magnet[2].getFirst() == 1) ans += 4;
            if (magnet[3].getFirst() == 1) ans += 8;
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    // 몇번 자석, 방향
    private static void roundMagnet(int number, int dir) {
        // dir이 반시계 방향일 때는 맨 앞의 요소를 빼서 뒤로 넣어주어야 한다.
        // dir이 시계 방향일 때는 맨 뒤의 요소를 빼서 앞으로 넣어주면 된다.
        // 이때 양 옆에 붙어있는 자석의 극성이 똑같은지 다른지 보고, 다르다면 같이 도는 방향의 반대 방향으로 회전시켜주어야 한다.
        int[] direction = new int[4]; // 각 자석의 회전 방향을 저장한다.

        direction[number-1] = dir;

        // 현재 자석 왼쪽 확인
        for (int i = number-1; i > 0; i--) {
            if (!(magnet[i].toArray()[6].equals(magnet[i-1].toArray()[2]))) {
                direction[i-1] = -direction[i]; // 회전 방향 반대로 설정
            } else {
                break; // 극성이 같은 경우
            }
        }

        // 현재 자석의 오른쪽 확인
        for (int i = number; i < 4; i++) {
            if (!(magnet[i-1].toArray()[2].equals(magnet[i].toArray()[6]))) {  // 극성이 다른 경우
                direction[i] = -direction[i-1];  // 회전 방향을 반대로 설정
            } else {
                break;  // 극성이 같은 경우 더 이상 확인하지 않음
            }
        }

        // 자석 회전
        for (int i = 0; i < 4; i++) {
            if (direction[i] == 1) {
                magnet[i].addFirst(magnet[i].removeLast());
            } else if (direction[i] == -1) {
                magnet[i].addLast(magnet[i].removeFirst());
            }
        }
    }



}