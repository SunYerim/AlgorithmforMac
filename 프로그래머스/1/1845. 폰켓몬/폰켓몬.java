import java.util.*;

class Solution {
    public int solution(int[] nums) {

        HashSet<Integer> a = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if(!a.contains(nums[i])) a.add(nums[i]);
        }
        int answer = a.size() > nums.length/2 ? nums.length/2 : a.size();
        return answer;
    }
}