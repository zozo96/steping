package algorithums.leetCode.basic;

public class SubString {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n < 2)
            return n;
        // 纯小写字母可用26 但是题意为字符，改成128
//        int[] A = new int[26];
        int[] B = new int[128];
        int result = 0;
        // i 用于记录字符串中上一重复下标的位置 + 1，默认为初始0
        // j 用于记录当前字符坐标下表
        // 这样便于直接计算长度 j - i + 1
        int i = 0;
        int j = 0;
        for (; j < n; j++){
            char c = s.charAt(j);
            int index = c;
            if (B[index] != 0){
                // 当前字符s[j] 已存在于s[i,j), 把 i 指向重复字符下一位
                // abba  当i = 2, j = 3时，会出现 i 指回 1的情况， 所以取max
                i = Math.max(B[index], i) ;
            }
            // 不存在于s[i,j)，直接赋值即可,考虑到j第一个值为0  所以这里赋值A[index] 直接就是重复值的下一位
            B[index] = j + 1;
            result = Math.max(result, j - i + 1);
        }
        return result;
    }
}
