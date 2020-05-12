package design_mode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例模式
 * <p>
 * 1. 懒汉
 * 2. 饿汉：基于static
 * 静态成员变量
 * 静态块儿
 * 静态内部类
 * 3. 枚举：基于static
 * 4. 锁
 * AtomicReference
 * CAS乐观锁
 * 优点：不需要使用传统的锁机制来保证线程安全，CAS是一种基于忙等待的算法，依赖底层硬件的实现，相对于锁它没有线程切换和阻塞的额外消耗，可以支持较大的并行度。
 * 缺点：如果忙等待一直执行不成功(一直在死循环中)，会对CPU造成较大的执行开销。
 * synchronized
 * 排他锁
 * 5. ThreadLocal
 * <p>
 * 破坏单例： 反射、序列化
 * 1. 反射
 * 通过反射获取默认构造函数实例化
 * 2. 反序列化
 * 通过反射 newInstance()实例化对象。即使类已声明默认构造函数为 private，实际反序列化是根据Object构造函数反射生成。
 * 步骤：
 * readObject()
 * readObject0(false)
 * readOrdinaryObject() // 关键方法
 * obj = desc.isInstantiable() ? desc.newInstance() : null;
 * newInstance()
 * Object ob = in.readObject(); // 反序列化为 Object
 * Target target = (Target) ob; // 强转为目标对象 Target
 * <p>
 * 阻止破坏单例：
 * 1. 针对反射的情况：
 * 构造函数中增加标志位，使构造函数仅能被调用一次
 * 2. 针对反序列化的情况：
 * 在单例类中实现 readResolve() 方法
 * 可以读 readOrdinaryObject() // 关键方法
 *
 * @author Sonya
 * @date 2020/5/12 22:58
 */
public class ModeSingleton {
    /**
     * 通过反射，破坏单例模式
     */
    static void destroySingletonByReflect() throws Exception {
        SingletonOfHungary singleton = SingletonOfHungary.getInstance();
        SingletonOfHungary newInstance;

        Class<SingletonOfHungary> clazz = SingletonOfHungary.class;
        // 获取默认构造函数
        Constructor<SingletonOfHungary> declaredConstructor = clazz.getDeclaredConstructor();
        // 默认构造函数可访问
        declaredConstructor.setAccessible(true);
        // 创建对象
        newInstance = declaredConstructor.newInstance();
        System.out.println("singleton.hashCode: " + singleton.hashCode());
        System.out.println("newInstance.hashCode: " + newInstance.hashCode());
    }

    /**
     * 通过反序列化，破坏单例模式
     */
    static void destroySingletonByDeSerialize() throws Exception {
        SingletonOfHungary singleton = SingletonOfHungary.getInstance();
        SingletonOfHungary newInstance;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tmp"));
        out.writeObject(singleton);

        File file = new File("tmp");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        //调用readObject()反序列化
        newInstance = (SingletonOfHungary) in.readObject();
        System.out.println("singleton.hashCode: " + singleton.hashCode());
        System.out.println("newInstance.hashCode: " + newInstance.hashCode());
    }

    /**
     * 阻止 反射破坏单例。加构造函数标志位
     */
    static void stopReflectDestroy() throws Exception {
        SingletonOfInnerStatic singleton = SingletonOfInnerStatic.getInstance();
        SingletonOfInnerStatic newInstance;

        Class<SingletonOfInnerStatic> clazz = SingletonOfInnerStatic.class;
        // 获取默认构造函数
        Constructor<SingletonOfInnerStatic> declaredConstructor = clazz.getDeclaredConstructor();
        // 默认构造函数可访问
        declaredConstructor.setAccessible(true);
        // 创建对象
        newInstance = declaredConstructor.newInstance();
        System.out.println("singleton.hashCode: " + singleton.hashCode());
        System.out.println("newInstance.hashCode: " + newInstance.hashCode());
    }

    /**
     * 阻止 反序列化破坏单例。实现 readResolve()
     */
    static void stopDeSerializeDestroy() throws Exception{
        SingletonOfInnerStatic singleton = SingletonOfInnerStatic.getInstance();
        SingletonOfInnerStatic newInstance;

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tmp"));
        out.writeObject(singleton);

        File file = new File("tmp");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        //调用readObject()反序列化
        newInstance = (SingletonOfInnerStatic) in.readObject();
        System.out.println("singleton.hashCode: " + singleton.hashCode());
        System.out.println("newInstance.hashCode: " + newInstance.hashCode());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("反射，破坏单例模式：");
        destroySingletonByReflect();
        System.out.println("反序列化，破坏单例模式：");
        destroySingletonByDeSerialize();
        System.out.println("阻止 反射破坏单例：");
        stopReflectDestroy();
        System.out.println("阻止 反序列化破坏单例");
        stopDeSerializeDestroy();
    }
}

/**
 * 懒汉 延迟初始化 - 不安全
 */
class SingletonOfLazy {

    private static SingletonOfLazy instance;

    private SingletonOfLazy() {
    }

    public static SingletonOfLazy getInstance() {
        if (instance == null) {
            instance = new SingletonOfLazy();
        }
        return instance;
    }
}

/**
 * 饿汉.静态成员变量 类加载时初始化 - 线程安全
 */
class SingletonOfHungary implements Serializable {

    private static SingletonOfHungary instance = new SingletonOfHungary();

    private SingletonOfHungary() {
    }

    public static SingletonOfHungary getInstance() {
        return instance;
    }
}

/**
 * 饿汉.静态块初始化静态成员变量 类加载时初始化 - 线程安全
 */
class SingletonOfInnerStatic implements Serializable{

    private static SingletonOfInnerStatic instance;

    //申明一个标志位，用于标志构造函数是否被调用过
    private static boolean alreadyExist;

    static {
        instance = new SingletonOfInnerStatic();
        alreadyExist = true;
    }

    private SingletonOfInnerStatic() {
        if (alreadyExist) {
            System.out.println("单例模式不能破坏，已存在此单例！");
        }
    }

    public static SingletonOfInnerStatic getInstance() {
        return instance;
    }

    public Object readResolve() {
        return instance;
    }
}

/**
 * 饿汉.静态内部类 类加载时初始化 - 线程安全
 */
class SingletonOfInnerClass {

    private SingletonOfInnerClass() {
    }

    private static class SingletonHolder {
        private static final SingletonOfInnerClass INSTANCE = new SingletonOfInnerClass();
    }

    public static SingletonOfInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

/**
 * 枚举.枚举类的成员变量均为static 在静态代码块中实例化 - 线程安全
 */
enum SingletonOfEnum {
    INSTANCE;
}

/**
 * 乐观锁 - 线程安全
 */
class SingletonOfCAS {
    public static final AtomicReference<SingletonOfCAS> INSTANCE = new AtomicReference<>();

    private SingletonOfCAS() {
    }

    public static SingletonOfCAS getInstance() {
        for (; ; ) {
            SingletonOfCAS singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new SingletonOfCAS();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}

/**
 * ThreadLocal 基于static - 线程安全
 * ThreadLocal是线程隔离的，会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
 * 对于多线程资源共享的问题，同步机制(synchronized)采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
 */
class SingletonOfThreadLocal {

    private SingletonOfThreadLocal() {
    }

    private static final ThreadLocal<SingletonOfThreadLocal> INSTANCE = ThreadLocal.withInitial(SingletonOfThreadLocal::new);

    public static SingletonOfThreadLocal getInstance() {
        return INSTANCE.get();
    }
}