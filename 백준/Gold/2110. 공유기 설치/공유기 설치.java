import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 최소거리를 최대로 ..?
    static int N, C, maxDiff = Integer.MIN_VALUE;
    static int[] house;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()); // C개 설치

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        install(1, house[N-1]); // 가능한 사이 거리

        System.out.println(maxDiff);

    }

    private static void install(int start, int end) {
        while (start <= end) {
            int mid = (start + end) / 2;
            int total = 1;
            int pos = 0; // 설치한 공유기 위치

            // 인접한 두 공유기 사이의 거리를 가능한 크게하여 설치
            for (int i = 1; i < house.length; i++) {
                if (house[i] - house[pos] >= mid) {
//                    System.out.println("------ " +pos + ", " + start + " " + end + " " + total);
                    // 설치 가능
                    pos = i;
                    total++;

                }
            }

            // C개보다 덜 설치 되었다면 거리 줄여야됨.
            if (total < C) {
                end = mid - 1;
//                System.out.println(start + " " + end + " " + total);
            } else {
                start = mid + 1;
//                System.out.println(start + " " + end + " " + total+ "------");
            }
        }
        maxDiff = start - 1;
    }

}