import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] students;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        students = new int[N];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            students[i] = Integer.parseInt(st.nextToken());
        }

        int total = 0;
        for (int i = 1; i < N; i++) {
            list.add(students[i] - students[i-1]);
        }

        Collections.sort(list);

        // K개의 그룹으로 나누기 -> N - K개 묶어주기
        for (int i = 0; i < N - K; i++) {
            total += list.get(i);
        }

        System.out.println(total);
    }
}
