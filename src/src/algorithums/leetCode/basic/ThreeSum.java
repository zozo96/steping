package algorithums.leetCode.basic;

import java.util.*;

public class ThreeSum {
    private Set<List> set = new HashSet<>();
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        // -2 -1 0 0 1 2
        List<List<Integer>> result= new ArrayList<>();
        for (int i = 0; i < nums.length;i++){
            if (nums[i] > 0){
                break;
            }
            int s = i + 1;
            int e = nums.length - 1;
            if (s > e)
                break;
            while (true) {
                List<Integer> pair = new ArrayList<>();
                while (nums[i] + nums[s] + nums[e] < 0) {
                    if (s == nums.length - 1)
                        break;
                    s++;
                }
                while (nums[i] + nums[s] + nums[e] > 0) {
                    if (e == 0)
                        break;
                    e--;
                }
                if (s >= e)
                    break;
                if (nums[i] + nums[s] + nums[e] == 0) {
                    pair.add(nums[i]);
                    pair.add(nums[s]);
                    pair.add(nums[e]);
                    set.add(pair);
                }
                s++;
            }
        }
        Iterator<List> it = set.iterator();
        while (it.hasNext()){
            result.add(it.next());
        }
        return result;
    }
}
