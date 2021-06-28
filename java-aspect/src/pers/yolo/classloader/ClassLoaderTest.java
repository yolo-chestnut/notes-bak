package pers.yolo.classloader;

import pers.yolo.classloader.pojo.Person;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassLoaderTest {
    public static void main(String[] args) {
        String aClass = "pers.yolo.classloader.pojo.Person";

        MyClassLoader mcl1 = new MyClassLoader();
        MyClassLoader mcl2 = new MyClassLoader();

        Class<?> a1 = mcl1.loadClass(aClass);
        Class<?> a2 = mcl1.loadClass(aClass);
        Class<?> b0 = mcl2.loadClass(aClass);

        System.out.println("a1：    " + a1.getClassLoader());
        System.out.println("a2：    " + a2.getClassLoader());
        System.out.println("b0：    " + b0.getClassLoader());
        System.out.println("Person：" + Person.class.getClassLoader());

        System.out.println(a1 == a2);
        System.out.println(a1 == b0);
        System.out.println(a2 == b0);
        System.out.println(Person.class == a1);
        System.out.println(Person.class == a2);
        System.out.println(Person.class == b0);
    }
}

class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) {
//        return super.loadClass(name);
        Class<?> aClass;
        // 判断是否已经加载过
        aClass = findLoadedClass(name);
        if (aClass != null) return aClass;
        // 若未加载，尝试交给父ClassLoader（ExtClassLoader）加载
        try {
            aClass = getSystemClassLoader().getParent().loadClass(name);
        } catch (ClassNotFoundException e) {
            System.out.println("ExtClassLoader 没有找到" + name);
        }
        if (aClass != null) return aClass;
        // 父ClassLoader加载失败，则自己尝试加载
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) {
//        return super.findClass(name);
        String className = name.replace('.', '/') + ".class";
        String pathName = System.getProperty("user.dir") + "/out/production/java-aspect/" + className;
        Path path = Paths.get(pathName);
        byte[] data = new byte[0];
        try (FileInputStream in = new FileInputStream(path.toFile());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            data = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, data, 0, data.length);
    }
}
