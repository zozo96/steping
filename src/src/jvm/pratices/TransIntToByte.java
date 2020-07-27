package jvm.pratices;

/**
 * 打印出整数的二进制
 */
public class TransIntToByte {
    public static void main(String args[]) {
        int a = -6;
        // 整数有32位 0x8000_0000最高位为1的数字
        for (int i = 0; i < 32; i++) {
            int t = (a & 0x8000_0000 >>> i) >>> (31 - i);
            System.out.println(t);
        }
    }
}
