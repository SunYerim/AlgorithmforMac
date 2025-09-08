import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Beer[] beers = new Beer[k];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            beers[i] = new Beer(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
        }

        Arrays.sort(beers);

        // heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long total = 0;
        long minLevel = -1;

        for (int i = 0; i < k; i++) {
            total += beers[i].favorite;
            pq.add(beers[i].favorite);

            if (pq.size() > n) {
                total -= pq.poll();
            }

            if (pq.size() == n && total >= m) {
                if (minLevel == -1) {
                    minLevel = beers[i].level;
                } else {
                    minLevel = Math.min(minLevel, beers[i].level);
                }

            }
        }

        System.out.println(minLevel);

    }

    static class Beer implements Comparable<Beer> {

        int favorite;
        long level;

        public Beer(int favorite, long level) {
            this.favorite = favorite;
            this.level = level;
        }

        @Override
        public int compareTo(Beer o) {
            if (this.level == o.level) {
                return this.favorite - o.favorite;
            } else {
                return (int) (this.level - o.level);
            }
        }
    }
}
