import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashMap<String, List<String>> idol = new HashMap<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String group = br.readLine();
            idol.put(group, new ArrayList<String>());
            int people = Integer.parseInt(br.readLine());
            for (int j = 0; j < people; j++) {
                String nickname = br.readLine();
                idol.get(group).add(nickname);
            }
            Collections.sort(idol.get(group));
        }

        // 퀴즈
        for (int i = 0; i < m; i++) {
            String quiz = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if (type == 1) {
                // 그룹 이름
                for (String groupName : idol.keySet()) {
                    if (idol.get(groupName).contains(quiz)) {
                        sb.append(groupName).append("\n");
                        break;
                    }
                }
            } else if (type == 0) {
                // 해당 그룹 사람들 다
                for (String person : idol.get(quiz)) {
                    sb.append(person).append("\n");
                }
            }
        }
        System.out.println(sb.toString());
    }

}
