import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, answer = Integer.MAX_VALUE;
    static int[] curr, target;
    static int[] list1, list2;

    public static void main(String[] args) throws IOException {
        // 0번 스위치를 눌렸을때, 안 눌렸을때 구분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String s = br.readLine();
        String t = br.readLine();
        curr = new int[N];
        target = new int[N];

        for (int i = 0; i < N; i++) {
            curr[i] = s.charAt(i) - '0';
            target[i] = t.charAt(i) - '0';
        }

        list1 = new int[N]; // 0번 안 눌렸을때
        list2 = new int[N]; // 0번 눌렸을때

        for (int i = 0; i < N; i++) {
            list1[i] = curr[i];
            if (i < 2) {
                list2[i] = 1 - curr[i];
            } else {
                list2[i] = curr[i];
            }
        }

        int cnt1 = 0;
        int cnt2 = 1;

        // 비교
        for (int i = 1; i < N - 1; i++) {
            // i번째 스위치 누름.
            if (list1[i - 1] != target[i - 1]) {
                list1[i - 1] = 1 - list1[i - 1];
                list1[i] = 1 - list1[i];
                list1[i + 1] = 1 - list1[i + 1];
                cnt1++;
            }
            if (list2[i - 1] != target[i - 1]) {
                list2[i - 1] = 1 - list2[i - 1];
                list2[i] = 1 - list2[i];
                list2[i + 1] = 1 - list2[i + 1];
                cnt2++;
            }
        }

        // 마지막 스위치
        if (list1[N - 2] != target[N - 2]) {
            list1[N - 2] = 1 - list1[N - 2];
            list1[N - 1] = 1 - list1[N - 1];
            cnt1++;
        }

        if (list2[N - 2] != target[N - 2]) {
            list2[N - 2] = 1 - list2[N - 2];
            list2[N - 1] = 1 - list2[N - 1];
            cnt2++;
        }

        // 동일한지 아닌지 판단
        boolean flag1 = true;
        boolean flag2 = true;

        for (int i = 0; i < N; i++) {
            if (list1[i] != target[i]) {
                flag1 = false;
                break;
            }
        }

        for (int i = 0; i < N; i++) {
            if (list2[i] != target[i]) {
                flag2 = false;
                break;
            }
        }

        if (flag1) {
            answer = Math.min(answer, cnt1);
        } else if (flag2) {
            answer = Math.min(answer, cnt2);
        }

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

}
