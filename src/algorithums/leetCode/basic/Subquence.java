package leetCode.basic;


public class Subquence {
    public boolean isSubsequence(String s, String t) {
        char[] tt = t.toCharArray();
        char[] ss = s.toCharArray();
        int j = ss.length - 1;
        if (j == -1)
            return true;
        for (int i = tt.length - 1;i >= 0; i--){
            char c = tt[i];
            if (ss[j] == (c)){
                j--;
                if(j == -1)
                    return true;
            }
        }

        return j == -1;
    }
}
