import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int p, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken()); // 플레이어 수
        m = Integer.parseInt(st.nextToken()); // 정원

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();
            Person player = new Person(level, nickname);

            // 방에 들어갔는지 체크
            boolean isEntered = false;

            // 1. 이미 있는 방들 순회
            for (Room room : rooms) {
                // 정원 미달 && 레벨 만족
                if (room.players.size() < m && player.level >= room.baseLevel - 10
                    && player.level <= room.baseLevel + 10) {
                    room.players.add(player);
                    isEntered = true;
                    break;
                }
            }

            // 2. 새로운 방
            if (!isEntered) {
                rooms.add(new Room(level, player));
            }
        }

        for (Room room : rooms) {
            // 정렬
            Collections.sort(room.players);

            if (room.players.size() == m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }

            for (Person p : room.players) {
                sb.append(p.level).append(" ").append(p.nickname).append("\n");
            }
        }

        System.out.print(sb.toString());


    }

    static class Room {

        int baseLevel;
        List<Person> players = new ArrayList<>();

        public Room(int baseLevel, Person p) {
            this.baseLevel = baseLevel;
            this.players.add(p);
        }
    }

    static class Person implements Comparable<Person> {

        int level;
        String nickname;

        public Person(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Person o) {
            return this.nickname.compareTo(o.nickname);
        }
    }

}
