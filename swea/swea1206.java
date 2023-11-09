import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea1206 {
    static int[] building;
    static int count;
    public static void main(String[] args) throws IOException {
        // 3번째 건물부터 탐색 시작
        // 우선 통과해야할 조건 -> 양 옆에 있는 건물보다는 무조건 현재 건물이 높아야하고
        // 두 칸 떨어져있는 좌우 건물 중 높이 max값을 현재 건물 높이에서 빼줘서 count 변수에 누적시켜준다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 1; T <= 10; T++) {
            int N = Integer.parseInt(br.readLine());
            building = new int[N];
            count = 0; // return 값 누적 목적
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                building[i] = Integer.parseInt(st.nextToken());
            }

            // 실행 -> 양측 두 개 요소는 0이므로 반복문 돌릴 필요 없음.
            for (int j = 2; j < N-2; j++) {
                if (highBuilding(building, j)) {
                    count += higherBuilding(building, j);
                } else {
                    continue;
                }
            }

            System.out.println("#"+T+" "+count);

        }
        

    }

    // 현재 건물이 양 옆에 있는 건물보다 높은지 판단하는 메소드
    public static boolean highBuilding(int[] building, int idx) {
        // building 배열과 현재 건물의 위치를 매개변수로 -> 3번부터 시작한다.
        if (building[idx] > building[idx-1] && building[idx] > building[idx+1] && building[idx] > building[idx-2] && building[idx] > building[idx+2])
            return true;
        else {
            return false;
        }
    }

    // 왼쪽 max값 return
    public static int leftMax(int[] building, int idx) {
        int num1 = building[idx-1];
        int num2 = building[idx-2];
        return Math.max(num1, num2);
    }

    // 오른쪽 max값 return
    public static int rightMax(int[] building, int idx) {
        int num1 = building[idx+1];
        int num2 = building[idx+2];
        return Math.max(num1, num2);
    }

    public static int higherBuilding(int[] building, int idx) {
        int temp = 0;
        // highBuilding 메소드가 통과된 것만 들어오므로 idx위치의 건물보다 양옆으로 두 칸 떨어진 건물 높이가 높은지 판단해서 max값과 차이를 return

        int leftMax = leftMax(building, idx);
        int rightMax = rightMax(building, idx);
        int finalMaxNum = Math.max(leftMax, rightMax);
        temp += (building[idx] - finalMaxNum);
        return temp;
    }
}
