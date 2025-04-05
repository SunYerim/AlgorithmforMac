import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        List<Lecture> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Lecture(start, end));

        }

        Collections.sort(list);

        PriorityQueue<Integer> roomQueue = new PriorityQueue<>();
        roomQueue.add(list.get(0).end);

        for (int i = 1; i < N; i++) {
            Lecture curr = list.get(i);
            if (roomQueue.peek() <= curr.start) {
                roomQueue.poll();
            }
            roomQueue.add(curr.end);
        }
        System.out.println(roomQueue.size());

    }

    static class Lecture implements Comparable<Lecture> {

        int start, end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start;
        }
    }

}
