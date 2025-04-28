import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static int[] heights;
    static List<Day> days;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        heights = new int[366];
        days = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            days.add(new Day(a, b));
        }

        Collections.sort(days);

        for (int i = 0; i < N; i++) {
            Day curr = days.get(i);

            search(curr.start, curr.end);
        }

        // 넓이 계산
        int startIdx = 0;
        int hei = 0;
        boolean flag = false;
        for (int i = 0; i <= 365; i++) {
            if (heights[i] > 0) {
                if (!flag) {
                    startIdx = i;
                    flag = true;
                }
                hei = Math.max(hei, heights[i]);
            } else {
                if (flag) {
                    answer += (i - startIdx) * hei;
                    hei = 0;
                    flag = false;
                }

            }

        }

        // 마지막 구간
        if (flag) {
            answer += (366 - startIdx) * hei;
        }
        System.out.println(answer);

    }

    public static void search(int start, int end) {

        for (int i = start; i <= end; i++) {
            heights[i]++;
        }

    }

    static class Day implements Comparable<Day> {

        int start, end;

        public Day(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Day o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

}
