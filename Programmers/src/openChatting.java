/* logic
    1. record는 2차원 배열
    2. 배열 길이만큼 반복문 돌면서, (띄워쓰기 기준) 각 배열의 0번쨰 index 글자 확인 후 result 배열에 넣어줌.
    3. 만약, 해당 글자가 change일 경우, record배열의 [i][0]번째 순회후 ..
        -아니면 아싸리 처음부터 change 키워드 있으면 id 비교 후 닉네임 바꿔버리고, enter&leave 로직만 처리해주면 안 됨?
    4. 그냥 action에 따라서 행동로직 짜주고, 유저 정보 저장할 맵과 결과 저장할 리스트 선언해서 구현해주면 끝*/
import java.util.*;

public class openChatting {
    public static void main(String[] args) {
        String[] record = new String[5];
        record[0] = "Enter uid1234 Muzi";
        record[1] = "Enter uid4567 Prodo";
        record[2] = "Leave uid1234";
        record[3] = "Enter uid1234 Prodo";
        record[4] = "Change uid4567 Ryan";

        Map<String, String> userMap = new HashMap<>(); // 유저 정보를 저장할 맵
        List<String> result = new ArrayList<>(); // 결과를 저장할 리스트

        for (String entry : record) {
            String[] parts = entry.split(" "); // 띄워쓰기로 문자열을 분리시킨다.
            String action = parts[0]; // 동작 (Enter, Leave, Change)
            String userId = parts[1]; // 유저 아이디

            // 첫번째 요소에 따라 다르게 행동되도록 코드 작성.
            if (action.equals("Enter")) {
                String userName = parts[2]; // 세 번째 요소는 유저 이름
                userMap.put(userId, userName); // 유저 정보를 맵에 저장
            } else if (action.equals("Change")) {
                String userName = parts[2]; // 세 번째 요소는 유저 이름
                userMap.put(userId, userName); // 유저 정보 업데이트
            }

            // Leave일 경우 필요없음.

        }

        // 결과 생성
        for (String entry : record) {
            String[] parts = entry.split(" ");
            String action = parts[0];
            String userId = parts[1];

            if (action.equals("Enter")) {
                result.add(userMap.get(userId) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                result.add(userMap.get(userId) + "님이 나갔습니다.");
            }
        }

        // 결과 출력
        for (String msg : result) {
            System.out.println(msg);
        }
    }
}
