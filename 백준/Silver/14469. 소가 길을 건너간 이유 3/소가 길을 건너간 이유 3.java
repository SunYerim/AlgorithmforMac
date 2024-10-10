import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Cow> cow;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        cow = new ArrayList<Cow>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int dest = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            cow.add(new Cow(dest, time));
        }
        Collections.sort(cow);

        int answer = cow.get(0).destTime; // 초기값 -> 소 도착 시간

        // n마리 도착
        for (int i = 0; i < cow.size() - 1; i++) {
            // 빨리옴
            answer += cow.get(i).time;
            if (answer < cow.get(i+1).destTime) {
                // 미리 오면
                int tmp = cow.get(i+1).destTime - answer;
                answer += tmp;
            }

        }
        answer += cow.get(cow.size()-1).time;
        System.out.println(answer);
    }

    static class Cow implements Comparable<Cow> {
        int destTime, time;

        public Cow (int destTime, int time) {
            this.destTime = destTime;
            this.time = time;
        }

        @Override
        public int compareTo(Cow o) {
            return this.destTime - o.destTime;
        }
    }

}
