
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static HashMap<String, Integer> books;
    static ArrayList<String> titles;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        books = new HashMap<>();
        titles = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String title = br.readLine();
            // hashmap에 없으면
            if (!books.containsKey(title)) {
                books.put(title, 1);
            } else {
                Integer freq = books.get(title);
                books.put(title, freq+1);
            }
        }

        // 배열 변환
        Object[] bookArr = books.keySet().toArray();
        Object[] countArr = books.values().toArray();
        // count 갯수 많은것 출력 -> 여러개인경우 사전순으로 출력
       // 최댓값 찾으러
        int max = 0;
        for (int i = 0; i < countArr.length; i++) {
            int count = (int) countArr[i];
            if (count > max) {
                max = count;
            }
        }
        // max값이랑 동일하면 titles 리스트에 title을 add처리 후 사전식 정렬
        for (int i = 0 ; i < countArr.length; i++) {
            if ((int)countArr[i] == max) {
                titles.add((String)bookArr[i]);
            }
        }
        Collections.sort(titles);

        System.out.println(titles.get(0));
    }
}
