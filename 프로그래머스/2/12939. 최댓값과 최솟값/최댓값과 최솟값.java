import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] list = s.split(" ");
        int[] convertList = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            convertList[i] = Integer.parseInt(list[i]);
        }
        Arrays.sort(convertList);
        sb.append(convertList[0]).append(" ").append(convertList[convertList.length-1]);

        return sb.toString();
    }
}