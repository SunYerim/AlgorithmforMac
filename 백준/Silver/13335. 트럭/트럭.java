import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, w, l;
    static Truck[] trucks;
    static Queue<Integer> bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        trucks = new Truck[n];
        Queue<Truck> queue = new LinkedList<>();
        bridge = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int weight = Integer.parseInt(st.nextToken());
            Truck newTruck = new Truck();
            newTruck.weight = weight;
            newTruck.time = w;
            queue.add(newTruck);
        }

        for (int i = 0; i < w; i++) {
            bridge.add(0);
        }

        // 시작
        int time = 0;
        int bridgeWeight = 0;
        while (!bridge.isEmpty()) {
            time++;
            bridgeWeight -= bridge.poll(); // 내려옴
            if (!queue.isEmpty()) {
                if (queue.peek().weight + bridgeWeight <= l) {
                    bridgeWeight += queue.peek().weight;
                    bridge.add(queue.poll().weight);
                } else {
                    bridge.add(0);
                }
            }
        }

        System.out.println(time);

    }

    static class Truck {
        int weight, time;
        public Truck() {

        }
        public Truck (int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }
}