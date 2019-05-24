package base;

/**
 * Thread 与 Runnable 即用到了模板设计模式start 和 run
 * 实现了模板方法才要执行，否则不执行
 * if (target != null) {
 *     target.run();
 * }
 * @Author Sophi
 * @Date 2019/5/24
 **/
public class Pattern_Template {
    public final void consoleMessage(String msg){
        System.out.println("*****");
        swapMsg(msg);
        System.out.println("#####");
    }

    protected void swapMsg(String msg){

    }

    public static void main(String[] args) {
        Pattern_Template templateInstance = new Pattern_Template(){
            @Override
            public void swapMsg(String msg) {
                System.out.println("I use method consoleMessage, but impl my own swap, this is the message: " + msg);
            }
        };
        templateInstance.swapMsg("u r cutie");
    }
}
