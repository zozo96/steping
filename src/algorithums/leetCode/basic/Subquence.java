package leetCode.basic;

import java.util.Stack;

public class Subquence {
    public boolean isSubsequence(String s, String t) {
        char[] tt = t.toCharArray();

        for (int i = tt.length - 1;i >= 0; i--){
            String c = tt[i]+"";
            if (s.endsWith(c)){
                s = s.substring(0, s.length() - 1);
            }
        }

        return s.length() == 0;
    }
}
