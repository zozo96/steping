/**
 * 项目名：  steping
 * 文件名：  LookForClassLoader.java
 * 模块说明：
 * 修改历史：
 * 2019-02-11 - Songyanyan - 创建。
 */

import com.sun.jmx.remote.util.OrderClassLoaders;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 1.查看系统的类加载器,Java默认提供三个ClassLoader 2.查看内存回收method.showGCInfo
 *
 * @author Songyanyan
 */
public class LookForClassLoader {
    public static void main(String[] args) {
        try {
            forceLoadInAppLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findClassLoader() {
        ClassLoader currentLoader = Thread.currentThread().getContextClassLoader(); // 获取当前线程上下文的类加载器
        System.out.println("current classLoader:" + currentLoader);
        System.out.println("parent classLoader:" + currentLoader.getParent());
        System.out.println("grandparent classLoader:" + currentLoader.getParent().getParent());

        // grandparent classLoader，即最顶层引导加载类BootStrap ClassLoader加载哪些类
        // 该加载器由C++编写，内嵌在JVM内核中，在内存（Java环境）中找不到其相应句柄，所以为null
        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    public static void showGCInfo() {
        // -verbose:jni
        LookForClassLoader obj = new LookForClassLoader();
        System.gc();
    }

    public static void checkClassLoadDirection() {
        // 添加参数 -Xbootclasspath/a:D:\helloJVM 可以更改boot的加载位置，例如D:\helloJVM下放置文件HelloClassLoader.class
        HelloClassLoader loader = new HelloClassLoader();
        loader.print();
    }

    public static void forceLoadInAppLoader() throws Exception {
        //  强制在apploader中加载 HelloClassLoader
        ClassLoader cl = LookForClassLoader.class.getClassLoader();
        byte[] bHelloClassLoader = loadClassBytes("jvmstudy.HelloClassLoader");
        Method md_defineClass = ClassLoader.class.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        md_defineClass.setAccessible(true);
        md_defineClass.invoke(cl, bHelloClassLoader, 0, bHelloClassLoader.length);
        md_defineClass.setAccessible(false);
        HelloClassLoader loader = new HelloClassLoader();
        System.out.println(loader.getClass().getClassLoader());
        loader.print();
    }


    private static byte[] loadClassBytes(String className) throws IOException {
        //获取class文件路径
        String classFile = getClassFile(className);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(classFile);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    private static String getClassFile(String name) {
        StringBuffer sb = new StringBuffer("E:\\myspace\\local-git-hub\\Steping\\src\\");
        name = name.replace('.', File.separatorChar) + ".class";
        sb.append(File.separatorChar + name);
        return sb.toString();
    }
}