import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Student> students;
    static List<Integer> ans;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        students = new ArrayList<>();
        ans = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            // 기존에 존재하는지 확인
            boolean flag = false;
            for (int j = 0; j < students.size(); j++) {
                // 기존에 존재하면
                if (students.get(j).idx == num) {
                    students.get(j).count++;
                    flag = true;
                }
            }
            if (!flag) {
                if (students.size() == N) {
                    Collections.sort(students);
                    students.remove(0);
                }
                students.add(new Student(num, i + 1, 1));
            }
        }

        for (int i = 0; i < students.size(); i++) {
            ans.add(students.get(i).idx);
        }

        Collections.sort(ans);
        
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }

    }

    static class Student implements Comparable<Student> {

        int idx, time, count;

        public Student(int idx, int time, int count) {
            this.idx = idx;
            this.time = time;
            this.count = count;
        }

        @Override
        public int compareTo(Student o) {
            if (this.count == o.count) {
                return this.time - o.time;
            }
            return this.count - o.count;
        }
    }

}
