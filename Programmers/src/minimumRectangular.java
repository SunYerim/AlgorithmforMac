class Solution {
    public int solution(int[][] sizes) {
        int maxGaro = 0; // 가로 중 최댓값
        int maxSero = 0; // 세로 중 최댓값

        for (int i = 0; i < sizes.length; i++) {
            int garo = Math.max(sizes[i][0], sizes[i][1]);
            int sero = Math.min(sizes[i][0], sizes[i][1]);

            maxGaro = Math.max(maxGaro, garo);
            maxSero = Math.max(maxSero, sero);
        }

        return maxGaro * maxSero;
    }
}
