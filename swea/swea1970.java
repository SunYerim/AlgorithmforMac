import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class swea1970 {
    public static void main(String[] args) throws IOException {
        // 입력되는 금액은 10원 이상 100만원 이하이다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // N원 입력 받기.
            int money = Integer.parseInt(br.readLine());
            int[] moneys = new int[8]; // 돈 종류 배열 -> 거슬러주어야할 화폐 종류의 개수 담는 용
            // 십원, 백원, 천원, 만원 단위를 끊어서 본다.
            if (money < 100) {
                int ten = (money / 10);
                if (ten >= 5) {
                    moneys[6]++;
                    ten-=5;
                }
                for (int i = 0; i < ten; i++) {
                    moneys[7]++;
                }
            } else if (money < 1000) {
                int ten = (money % 100) / 10;
                int hundred = (money / 100);
                if (ten >= 5) {
                    moneys[6]++;
                    ten-=5;
                }
                for (int i = 0; i < ten; i++) {
                    moneys[7]++;
                }
                if (hundred >= 5) {
                    moneys[4]++;
                    hundred -= 5;
                }
                for (int i = 0; i < hundred; i++) {
                    moneys[5]++;
                }

            } else if (money < 10000) {
                int ten = (money % 100) / 10;
                int hundred = (money % 1000) / 100;
                int thousand = (money / 1000);
                if (ten >= 5) {
                    moneys[6]++;
                    ten-=5;
                }
                for (int i = 0; i < ten; i++) {
                    moneys[7]++;
                }

                if (hundred >= 5) {
                    moneys[4]++;
                    hundred-=5;
                }
                for (int i = 0; i < hundred; i++) {
                    moneys[5]++;
                }

                if (thousand >= 5) {
                    moneys[2]++;
                    thousand-=5;
                }
                for (int i = 0; i < thousand; i++) {
                    moneys[3]++;
                }
            } else if (money < 100000) {
                int ten = (money % 100) / 10;
                int hundred = (money % 1000) / 100;
                int thousand = (money % 10000) / 1000;
                int million = (money / 10000);
                if (ten >= 5) {
                    moneys[6]++;
                    ten-=5;
                }
                for (int i = 0; i < ten; i++) {
                    moneys[7]++;
                }
                if (hundred >= 5) {
                    moneys[4]++;
                    hundred-=5;
                }
                for (int i = 0; i < hundred; i++) {
                    moneys[5]++;
                }

                if (thousand >= 5) {
                    moneys[2]++;
                    thousand-=5;
                }
                for (int i = 0; i < thousand; i++) {
                    moneys[3]++;
                }

                if (million >= 5) {
                    moneys[0]++;
                    million-=5;
                }
                for (int i = 0; i < million; i++) {
                    moneys[1]++;
                }
            } else if (money < 1000000) {
                int ten = (money % 100) / 10;
                int hundred = (money % 1000) / 100;
                int thousand = (money % 10000) / 1000;
                int million = (money % 100000) / 10000;
                int tenmillion = (money / 100000) * 10;
                if (ten >= 5) {
                    moneys[6]++;
                    ten-=5;
                }
                for (int i = 0; i < ten; i++) {
                    moneys[7]++;
                }
                if (hundred >= 5) {
                    moneys[4]++;
                    hundred-=5;
                }
                for (int i = 0; i < hundred; i++) {
                    moneys[5]++;
                }

                if (thousand >= 5) {
                    moneys[2]++;
                    thousand-=5;
                }
                for (int i = 0; i < thousand; i++) {
                    moneys[3]++;
                }

                if (million >= 5) {
                    moneys[0]++;
                    million-=5;
                }
                for (int i = 0; i < million; i++) {
                    moneys[1]++;
                }

                for (int j = 0; j < tenmillion/5; j++) {
                    moneys[0]++;
                }

            } else { // 최대엑수일때
                int ans = (1000000 / 50000);
                for (int i = 0; i < ans; i++) {
                    moneys[0]++;
                }
            }

            System.out.println("#"+tc);
            for (int k = 0; k < 8; k++){
                System.out.print(moneys[k]+" ");
            }
            System.out.println();



        }
    }
}
