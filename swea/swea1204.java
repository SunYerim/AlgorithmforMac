import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea1204 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            br.readLine(); // 테스트 케이스 read
            int[] scores = new int[101]; // 점수 0 ~ 100
            // 학생 1000명 이므로 입력 받으면서 해당 index값 count를 증가시킨다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 1000; k++) {
                int score = Integer.parseInt(st.nextToken());
                scores[score]++;

            }
            int maxNum = -999;
            int answer = 0;
            // 배열에서 최댓값 뽑아오는 로직
            for (int j = 0; j < scores.length; j++) {
                if (maxNum <= scores[j]) {
                    maxNum = scores[j];
                    answer = j;
                }
            }
            System.out.println("#"+i+" "+answer);
        }
    }
}
