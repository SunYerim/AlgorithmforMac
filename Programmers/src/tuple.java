/*logic
    1. set(python기준) 처리해서 중복원소 없애고? -> answer 반환 (보류)
    2. s가 문자열이므로 .. 문자열 다루는 문제
        - 우선 { 제거 해주고 }, 기준으로 있는 문자들 배열
    로직은 알겠는데 자바문법이 익숙치 않으니까 미치겠다 ........ 빨리 분발해
    */
import java.util.*;

class tuple {
    public int[] solution(String s) {
        s = s.replaceAll("\\{", ""); // { 문자를 이스케이프 문자로 처리
        String[] newArr = s.substring(0, s.length() - 2).split("\\}\\,");
        // 길이순 sort
        Arrays.sort(newArr, Comparator.comparingInt(String::length));

        // set
        HashSet<Integer> duplicate = new HashSet<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < newArr.length; i++) {
            String[] tupleValues = newArr[i].split(",");
            int[] tuple = new int[tupleValues.length];
            for (int j = 0; j < tupleValues.length; j++) {
                try {
                    tuple[j] = Integer.parseInt(tupleValues[j]);
                } catch (NumberFormatException e) {
                    // 유효하지 않은 정수 형식의 문자열이면 처리하지 않고 다음으로 넘김.
                    continue;
                }
                // 중복안되면
                if (!duplicate.contains(tuple[j])) {
                    result.add(tuple[j]);
                }
                duplicate.add(tuple[j]);
            }
        }

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }

        return answer;
    }
}