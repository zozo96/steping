
```
javac -encoding UTF-8 MutexMonitor.java
javap -c MutexMonitor 或者 javap -verbose MutexMonitor.class
```
### monitorenter
- 每个对象都与一个 `monitor` 关联，一个 `monitor` 的 `lock` 只能在同一时间被一个线程获得
- monitor 计数器为0，则可被线程获取并+1，此线程即为该 monitor 的所有者
- monitor 所有者进行线程重入，则 monitor 计数器继续加1
- monitor 已被占用，则其他线程尝试获取该 monitor 的所有权时，会被阻塞直至 monitor 的计数器变为0

### monitorexit
- 释放所有权

*********************** javap -c MutexMonitor ***********************
```
Compiled from "MutexMonitor.java"
public class security.MutexMonitor {
  public security.MutexMonitor();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void accessResource(int);
    Code:
       0: getstatic     #2                  // Field MUTEX:Ljava/lang/Object;
       3: dup
       4: astore_2
       5: monitorenter
       6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       9: new           #4                  // class java/lang/StringBuilder
      12: dup
      13: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
      16: iload_1
      17: invokevirtual #6                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      20: ldc           #7                  // String  accessed the Monitor.
      22: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      25: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      31: getstatic     #11                 // Field java/util/concurrent/TimeUnit.MINUTES:Ljava/util/concurrent/TimeUnit;
      34: ldc2_w        #12                 // long 5l
      37: invokevirtual #14                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
      40: goto          48
      43: astore_3
      44: aload_3
      45: invokevirtual #16                 // Method java/lang/InterruptedException.printStackTrace:()V
      48: aload_2
      49: monitorexit
      50: goto          60
      53: astore        4
      55: aload_2
      56: monitorexit
      57: aload         4
      59: athrow
      60: return
    Exception table:
       from    to  target type
           6    40    43   Class java/lang/InterruptedException
           6    50    53   any
          53    57    53   any

  public static void main(java.lang.String[]);
    Code:
       0: new           #17                 // class security/MutexMonitor
       3: dup
       4: invokespecial #18                 // Method "<init>":()V
       7: astore_1
       8: iconst_0
       9: iconst_5
      10: invokestatic  #19                 // InterfaceMethod java/util/stream/IntStream.range:(II)Ljava/util/stream/IntStream;
      13: aload_1
      14: invokedynamic #20,  0             // InvokeDynamic #0:accept:(Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
      19: invokeinterface #21,  2           // InterfaceMethod java/util/stream/IntStream.forEach:(Ljava/util/function/IntConsumer;)V
      24: return

  static {};
    Code:
       0: new           #27                 // class java/lang/Object
       3: dup
       4: invokespecial #1                  // Method java/lang/Object."<init>":()V
       7: putstatic     #2                  // Field MUTEX:Ljava/lang/Object;
      10: return
}
```

*********************** javap -verbose MutexMonitor.class ***********************
```
Compiled from "MutexMonitor.java"
public class security.MutexMonitor {
  public security.MutexMonitor();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void accessResource(int);
    Code:
       0: getstatic     #2                  // Field MUTEX:Ljava/lang/Object;
       3: dup
       4: astore_2
       5: monitorenter
       6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       9: new           #4                  // class java/lang/StringBuilder
      12: dup
      13: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
      16: iload_1
      17: invokevirtual #6                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
      20: ldc           #7                  // String  accessed the Monitor.
      22: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
      25: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
      28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      31: getstatic     #11                 // Field java/util/concurrent/TimeUnit.MINUTES:Ljava/util/concurrent/TimeUnit;
      34: ldc2_w        #12                 // long 5l
      37: invokevirtual #14                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
      40: goto          48
      43: astore_3
      44: aload_3
      45: invokevirtual #16                 // Method java/lang/InterruptedException.printStackTrace:()V
      48: aload_2
      49: monitorexit
      50: goto          60
      53: astore        4
      55: aload_2
      56: monitorexit
      57: aload         4
      59: athrow
      60: return
    Exception table:
       from    to  target type
           6    40    43   Class java/lang/InterruptedException
           6    50    53   any
          53    57    53   any

  public static void main(java.lang.String[]);
    Code:
       0: new           #17                 // class security/MutexMonitor
       3: dup
       4: invokespecial #18                 // Method "<init>":()V
       7: astore_1
       8: iconst_0
       9: iconst_5
      10: invokestatic  #19                 // InterfaceMethod java/util/stream/IntStream.range:(II)Ljava/util/stream/IntStream;
      13: aload_1
      14: invokedynamic #20,  0             // InvokeDynamic #0:accept:(Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
      19: invokeinterface #21,  2           // InterfaceMethod java/util/stream/IntStream.forEach:(Ljava/util/function/IntConsumer;)V
      24: return

  static {};
    Code:
       0: new           #27                 // class java/lang/Object
       3: dup
       4: invokespecial #1                  // Method java/lang/Object."<init>":()V
       7: putstatic     #2                  // Field MUTEX:Ljava/lang/Object;
      10: return
}

E:\myspace\local-git-hub\Steping\src\threadstudy\security>javap -verbose MutexMonitor.class
错误: 找不到类: MutexMonitor.class

E:\myspace\local-git-hub\Steping\src\threadstudy\security>javac -encoding UTF-8 MutexMonitor.java

E:\myspace\local-git-hub\Steping\src\threadstudy\security>javap -verbose MutexMonitor.class
Classfile /E:/myspace/local-git-hub/Steping/src/threadstudy/security/MutexMonitor.class
  Last modified 2019-6-8; size 2222 bytes
  MD5 checksum 2ad4e552f00d287d3993edc37c22c9b7
  Compiled from "MutexMonitor.java"
public class security.MutexMonitor
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
    #1 = Methodref          #27.#49       // java/lang/Object."<init>":()V
    #2 = Fieldref           #17.#50       // security/MutexMonitor.MUTEX:Ljava/lang/Object;
    #3 = Fieldref           #51.#52       // java/lang/System.out:Ljava/io/PrintStream;
    #4 = Class              #53           // java/lang/StringBuilder
    #5 = Methodref          #4.#49        // java/lang/StringBuilder."<init>":()V
    #6 = Methodref          #4.#54        // java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
    #7 = String             #55           //  accessed the Monitor.
    #8 = Methodref          #4.#56        // java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
    #9 = Methodref          #4.#57        // java/lang/StringBuilder.toString:()Ljava/lang/String;
   #10 = Methodref          #58.#59       // java/io/PrintStream.println:(Ljava/lang/String;)V
   #11 = Fieldref           #60.#61       // java/util/concurrent/TimeUnit.MINUTES:Ljava/util/concurrent/TimeUnit;
   #12 = Long               5l
   #14 = Methodref          #60.#62       // java/util/concurrent/TimeUnit.sleep:(J)V
   #15 = Class              #63           // java/lang/InterruptedException
   #16 = Methodref          #15.#64       // java/lang/InterruptedException.printStackTrace:()V
   #17 = Class              #65           // security/MutexMonitor
   #18 = Methodref          #17.#49       // security/MutexMonitor."<init>":()V
   #19 = InterfaceMethodref #66.#67       // java/util/stream/IntStream.range:(II)Ljava/util/stream/IntStream;
   #20 = InvokeDynamic      #0:#72        // #0:accept:(Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
   #21 = InterfaceMethodref #66.#73       // java/util/stream/IntStream.forEach:(Ljava/util/function/IntConsumer;)V
   #22 = Class              #74           // java/lang/Thread
   #23 = InvokeDynamic      #1:#77        // #1:run:(Lsecurity/MutexMonitor;I)Ljava/lang/Runnable;
   #24 = Methodref          #22.#78       // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
   #25 = Methodref          #22.#79       // java/lang/Thread.start:()V
   #26 = Methodref          #17.#80       // security/MutexMonitor.accessResource:(I)V
   #27 = Class              #81           // java/lang/Object
   #28 = Utf8               MUTEX
   #29 = Utf8               Ljava/lang/Object;
   #30 = Utf8               <init>
   #31 = Utf8               ()V
   #32 = Utf8               Code
   #33 = Utf8               LineNumberTable
   #34 = Utf8               accessResource
   #35 = Utf8               (I)V
   #36 = Utf8               StackMapTable
   #37 = Class              #65           // security/MutexMonitor
   #38 = Class              #81           // java/lang/Object
   #39 = Class              #63           // java/lang/InterruptedException
   #40 = Class              #82           // java/lang/Throwable
   #41 = Utf8               main
   #42 = Utf8               ([Ljava/lang/String;)V
   #43 = Utf8               lambda$main$1
   #44 = Utf8               (Lsecurity/MutexMonitor;I)V
   #45 = Utf8               lambda$null$0
   #46 = Utf8               <clinit>
   #47 = Utf8               SourceFile
   #48 = Utf8               MutexMonitor.java
   #49 = NameAndType        #30:#31       // "<init>":()V
   #50 = NameAndType        #28:#29       // MUTEX:Ljava/lang/Object;
   #51 = Class              #83           // java/lang/System
   #52 = NameAndType        #84:#85       // out:Ljava/io/PrintStream;
   #53 = Utf8               java/lang/StringBuilder
   #54 = NameAndType        #86:#87       // append:(I)Ljava/lang/StringBuilder;
   #55 = Utf8                accessed the Monitor.
   #56 = NameAndType        #86:#88       // append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
   #57 = NameAndType        #89:#90       // toString:()Ljava/lang/String;
   #58 = Class              #91           // java/io/PrintStream
   #59 = NameAndType        #92:#93       // println:(Ljava/lang/String;)V
   #60 = Class              #94           // java/util/concurrent/TimeUnit
   #61 = NameAndType        #95:#96       // MINUTES:Ljava/util/concurrent/TimeUnit;
   #62 = NameAndType        #97:#98       // sleep:(J)V
   #63 = Utf8               java/lang/InterruptedException
   #64 = NameAndType        #99:#31       // printStackTrace:()V
   #65 = Utf8               security/MutexMonitor
   #66 = Class              #100          // java/util/stream/IntStream
   #67 = NameAndType        #101:#102     // range:(II)Ljava/util/stream/IntStream;
   #68 = Utf8               BootstrapMethods
   #69 = MethodHandle       #6:#103       // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;
Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
   #70 = MethodType         #35           //  (I)V
   #71 = MethodHandle       #6:#104       // invokestatic security/MutexMonitor.lambda$main$1:(Lsecurity/MutexMonitor;I)V
   #72 = NameAndType        #105:#106     // accept:(Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
   #73 = NameAndType        #107:#108     // forEach:(Ljava/util/function/IntConsumer;)V
   #74 = Utf8               java/lang/Thread
   #75 = MethodType         #31           //  ()V
   #76 = MethodHandle       #6:#109       // invokestatic security/MutexMonitor.lambda$null$0:(Lsecurity/MutexMonitor;I)V
   #77 = NameAndType        #110:#111     // run:(Lsecurity/MutexMonitor;I)Ljava/lang/Runnable;
   #78 = NameAndType        #30:#112      // "<init>":(Ljava/lang/Runnable;)V
   #79 = NameAndType        #113:#31      // start:()V
   #80 = NameAndType        #34:#35       // accessResource:(I)V
   #81 = Utf8               java/lang/Object
   #82 = Utf8               java/lang/Throwable
   #83 = Utf8               java/lang/System
   #84 = Utf8               out
   #85 = Utf8               Ljava/io/PrintStream;
   #86 = Utf8               append
   #87 = Utf8               (I)Ljava/lang/StringBuilder;
   #88 = Utf8               (Ljava/lang/String;)Ljava/lang/StringBuilder;
   #89 = Utf8               toString
   #90 = Utf8               ()Ljava/lang/String;
   #91 = Utf8               java/io/PrintStream
   #92 = Utf8               println
   #93 = Utf8               (Ljava/lang/String;)V
   #94 = Utf8               java/util/concurrent/TimeUnit
   #95 = Utf8               MINUTES
   #96 = Utf8               Ljava/util/concurrent/TimeUnit;
   #97 = Utf8               sleep
   #98 = Utf8               (J)V
   #99 = Utf8               printStackTrace
  #100 = Utf8               java/util/stream/IntStream
  #101 = Utf8               range
  #102 = Utf8               (II)Ljava/util/stream/IntStream;
  #103 = Methodref          #114.#115     // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/in
voke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #104 = Methodref          #17.#116      // security/MutexMonitor.lambda$main$1:(Lsecurity/MutexMonitor;I)V
  #105 = Utf8               accept
  #106 = Utf8               (Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
  #107 = Utf8               forEach
  #108 = Utf8               (Ljava/util/function/IntConsumer;)V
  #109 = Methodref          #17.#117      // security/MutexMonitor.lambda$null$0:(Lsecurity/MutexMonitor;I)V
  #110 = Utf8               run
  #111 = Utf8               (Lsecurity/MutexMonitor;I)Ljava/lang/Runnable;
  #112 = Utf8               (Ljava/lang/Runnable;)V
  #113 = Utf8               start
  #114 = Class              #118          // java/lang/invoke/LambdaMetafactory
  #115 = NameAndType        #119:#123     // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/M
ethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #116 = NameAndType        #43:#44       // lambda$main$1:(Lsecurity/MutexMonitor;I)V
  #117 = NameAndType        #45:#44       // lambda$null$0:(Lsecurity/MutexMonitor;I)V
  #118 = Utf8               java/lang/invoke/LambdaMetafactory
  #119 = Utf8               metafactory
  #120 = Class              #125          // java/lang/invoke/MethodHandles$Lookup
  #121 = Utf8               Lookup
  #122 = Utf8               InnerClasses
  #123 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/M
ethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #124 = Class              #126          // java/lang/invoke/MethodHandles
  #125 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #126 = Utf8               java/lang/invoke/MethodHandles
{
  public security.MutexMonitor();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 28: 0

  public void accessResource(int);
    descriptor: (I)V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=5, args_size=2
         0: getstatic     #2                  // Field MUTEX:Ljava/lang/Object;
         3: dup
         4: astore_2
         5: monitorenter
         6: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
         9: new           #4                  // class java/lang/StringBuilder
        12: dup
        13: invokespecial #5                  // Method java/lang/StringBuilder."<init>":()V
        16: iload_1
        17: invokevirtual #6                  // Method java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        20: ldc           #7                  // String  accessed the Monitor.
        22: invokevirtual #8                  // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        25: invokevirtual #9                  // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
        28: invokevirtual #10                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        31: getstatic     #11                 // Field java/util/concurrent/TimeUnit.MINUTES:Ljava/util/concurrent/TimeUnit;
        34: ldc2_w        #12                 // long 5l
        37: invokevirtual #14                 // Method java/util/concurrent/TimeUnit.sleep:(J)V
        40: goto          48
        43: astore_3
        44: aload_3
        45: invokevirtual #16                 // Method java/lang/InterruptedException.printStackTrace:()V
        48: aload_2
        49: monitorexit
        50: goto          60
        53: astore        4
        55: aload_2
        56: monitorexit
        57: aload         4
        59: athrow
        60: return
      Exception table:
         from    to  target type
             6    40    43   Class java/lang/InterruptedException
             6    50    53   any
            53    57    53   any
      LineNumberTable:
        line 32: 0
        line 34: 6
        line 35: 31
        line 38: 40
        line 36: 43
        line 37: 44
        line 39: 48
        line 40: 60
      StackMapTable: number_of_entries = 4
        frame_type = 255 /* full_frame */
          offset_delta = 43
          locals = [ class security/MutexMonitor, int, class java/lang/Object ]
          stack = [ class java/lang/InterruptedException ]
        frame_type = 4 /* same */
        frame_type = 68 /* same_locals_1_stack_item */
          stack = [ class java/lang/Throwable ]
        frame_type = 250 /* chop */
          offset_delta = 6

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=2, args_size=1
         0: new           #17                 // class security/MutexMonitor
         3: dup
         4: invokespecial #18                 // Method "<init>":()V
         7: astore_1
         8: iconst_0
         9: iconst_5
        10: invokestatic  #19                 // InterfaceMethod java/util/stream/IntStream.range:(II)Ljava/util/stream/IntStream;
        13: aload_1
        14: invokedynamic #20,  0             // InvokeDynamic #0:accept:(Lsecurity/MutexMonitor;)Ljava/util/function/IntConsumer;
        19: invokeinterface #21,  2           // InterfaceMethod java/util/stream/IntStream.forEach:(Ljava/util/function/IntConsumer;)V
        24: return
      LineNumberTable:
        line 43: 0
        line 44: 8
        line 45: 24

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=2, locals=0, args_size=0
         0: new           #27                 // class java/lang/Object
         3: dup
         4: invokespecial #1                  // Method java/lang/Object."<init>":()V
         7: putstatic     #2                  // Field MUTEX:Ljava/lang/Object;
        10: return
      LineNumberTable:
        line 29: 0
}
SourceFile: "MutexMonitor.java"
InnerClasses:
     public static final #121= #120 of #124; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #69 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/l
ang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #70 (I)V
      #71 invokestatic security/MutexMonitor.lambda$main$1:(Lsecurity/MutexMonitor;I)V
      #70 (I)V
  1: #69 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/l
ang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #75 ()V
      #76 invokestatic security/MutexMonitor.lambda$null$0:(Lsecurity/MutexMonitor;I)V
      #75 ()V
```