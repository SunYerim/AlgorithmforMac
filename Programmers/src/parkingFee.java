import java.util.*;

class parkingFee {
    public int[] solution(int[] fees, String[] records) {
        // 입차시간, total 시간
        Map<String, Integer> carInTime = new HashMap<>();
        Map<String, Integer> carTotalTime = new HashMap<>();

        // " " 기준으로 split 시킨다.
        for (String record : records) {
            String[] parts = record.split(" ");
            String time = parts[0];
            String carNumber = parts[1];
            String action = parts[2];

            // in out에 따라 다른 로직 실행
            if (action.equals("IN")) {
                carInTime.put(carNumber, convertTimeToMinutes(time));
            } else if (action.equals("OUT")) {
                int inTime = carInTime.remove(carNumber);
                int outTime = convertTimeToMinutes(time);
                int totalTime = outTime - inTime;
                carTotalTime.put(carNumber, carTotalTime.getOrDefault(carNumber, 0) + totalTime);
            }
        }

        // 주차 중인 차량 처리 (출차기록이 없는경우)
        int currentTime = convertTimeToMinutes("23:59");
        for (String carNumber : carInTime.keySet()) {
            int inTime = carInTime.get(carNumber);
            int totalTime = currentTime - inTime;
            carTotalTime.put(carNumber, carTotalTime.getOrDefault(carNumber, 0) + totalTime);
        }

        // 차량번호 작은대로 결과 출력해야된다는 걸 못 봄 ........
        List<String> carNumbers = new ArrayList<>(carTotalTime.keySet());
        Collections.sort(carNumbers, (a, b) -> a.compareTo(b));

        ArrayList<Integer> answerList = new ArrayList<>();
        for (String carNumber : carNumbers) {
            int totalTime = carTotalTime.get(carNumber);

            if (totalTime <= fees[0]) {
                answerList.add(fees[1]);
            } else {
                int extraTime = totalTime - fees[0];
                int extraFee = (int) Math.ceil((double) extraTime / fees[2]) * fees[3];
                int totalFee = fees[1] + extraFee;
                answerList.add(totalFee);
            }
        }

        // answer 배열로 정답
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    // 시간: 분 -> 분으로 변환해주는 함수
    private int convertTimeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }
}
