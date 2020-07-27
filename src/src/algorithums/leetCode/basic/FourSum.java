package algorithums.leetCode.basic;

import java.util.*;

public class FourSum {
    private Set<List<Integer>> set = new HashSet<>();
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        // -5 -3 -2 -1 0 0 1 2 5 7
        List result = new ArrayList<>();
        if (nums.length < 4){
            return result;
        }

        for (int s = 0; s < nums.length - 2; s++){
            for (int e = nums.length - 1; e > 2; e--){
                if (s == e - 2)
                    break;
                int i = s + 1;
                int j = e - 1;
                int sum = target - nums[s] - nums[e];
                while (i < j){
                    while (nums[i] + nums[j] < sum){
                        if (i == nums.length -1)
                            break;
                        i++;
                    }
                    while (nums[i] + nums[j] > sum){
                        if (j == 0)
                            break;
                        j--;
                    }
                    if (i >= j)
                        break;
                    if (nums[i] + nums[j] == sum){
                        List<Integer> data = new ArrayList();
                        data.add(nums[s]);
                        data.add(nums[i]);
                        data.add(nums[j]);
                        data.add(nums[e]);
                        set.add(data);
                    }
                    i++;
                }
            }
        }

        Iterator it = set.iterator();
        while (it.hasNext()){
            result.add(it.next());
        }
        return result;
    }
}
