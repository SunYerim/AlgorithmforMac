package com.Programmers;

/*logic
    1. 한칸, 두칸, 대각선 탐색
    2. 맨해튼 거리가 지켜지고 있는지*/
import java.util.*;

class Solution {
    private static final int[][][] OFFSET = {
            // 한 칸
            {{-1, 0}, {0, 1}, {1, 0}, {0, -1}},
            // 두 칸
            {{-2, 0}, {0, 2}, {2, 0}, {0, -2}},
            // 대각선
            {{-1, 1}, {1, 1}, {1, -1}, {-1, -1}}
    };

    private List<int[]> positions = new ArrayList<>();

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int i = 0; i < 5; i++) {
            positions.clear();
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        positions.add(new int[] {j, k});
                    }
                }
            }
            answer[i] = isFollow(places[i]);
        }
        return answer;
    }

    private int isFollow(String[] place) {
        for (int[] position : positions) {
            for (int offset = 0; offset < 3; offset++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = position[0] + OFFSET[offset][dir][0];
                    int ny = position[1] + OFFSET[offset][dir][1];
                    // 범위 벗어나면
                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || place[nx].charAt(ny) != 'P') {
                        continue;
                    }
                    // 인접하게 붙어있는 경우
                    if (offset == 0) return 0;
                    else if (offset == 1) {
                        int px = position[0] + OFFSET[0][dir][0];
                        int py = position[1] + OFFSET[0][dir][1];
                        if (place[px].charAt(py) != 'X') return 0;
                    } else if (offset == 2) {
                        int tx1 = position[0] + OFFSET[0][dir][0];
                        int ty1 = position[1] + OFFSET[0][dir][1];
                        int tx2 = position[0] + OFFSET[0][(dir + 1) % 4][0];
                        int ty2 = position[1] + OFFSET[0][(dir + 1) % 4][1];
                        if (place[tx1].charAt(ty1) == 'O' || place[tx2].charAt(ty2) == 'O') return 0;
                    }
                }
            }
        }
        return 1;
    }
}
