package io.binghe.rpc.common.scanner;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanner {
    private static final String PROTOCOL_FILE="file";
    private static final String PROTOCOL_JAR="jar";
    private static final String CLASS_FILE_SUFFIX=".class";

    public static List<String> getClassNameList(String packageName) throws IOException {
        List<String> classNameList = new ArrayList<>();

        boolean recursive = true;
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

        while(resources.hasMoreElements()){
            URL url = resources.nextElement();
            String protocol =  url.getProtocol();
            if(PROTOCOL_FILE.equals(protocol)){
                String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
                findAndAddClassesInPackageByFile(packageName, filePath,
                        recursive, classNameList);
            }else if(PROTOCOL_JAR.equals(protocol)){
                findAddClassesInPackageByJAR(packageName, recursive,
                        packageDirName, classNameList, url);
            }
        }
        return classNameList;
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath,
                                                        final boolean recursive,List<String> classNameList){
        File dir = new File(packagePath);
        if(!dir.exists() || !dir.isDirectory()){
            return;
        }

        File[] files = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if( (recursive && pathname.isDirectory()) || pathname.getName().endsWith(CLASS_FILE_SUFFIX)){
                    return true;
                }
                return false;
            }
        });

        for(File file : files){
            if(file.isFile()){
                classNameList.add(packageName+"."+file.getName().substring(0,file.getName().lastIndexOf(CLASS_FILE_SUFFIX)));
            }else{
                findAndAddClassesInPackageByFile(packageName+"."+file.getName(),
                        file.getAbsolutePath(),recursive, classNameList);

            }
        }
    }

    private static String findAddClassesInPackageByJAR(String packageName, boolean recursive,
                                                     String packageDirName, List<String> classNameList, URL url) throws IOException {
        JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
//        System.out.println(url);
//        System.out.println(packageDirName);
//        System.out.println(packageDirName);
        Enumeration<JarEntry> entries = jar.entries();
        while(entries.hasMoreElements()){
            JarEntry jarEntry = entries.nextElement();
            String name = jarEntry.getName();
            //如果是以/开头的
            if (name.charAt(0) == '/') {
                //获取后面的字符串
                name = name.substring(1);
            }
            //如果前半部分和定义的包名相同
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                //如果以"/"结尾 是一个包
                if (idx != -1) {
                    //获取包名 把"/"替换成"."
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                //如果可以迭代下去 并且是一个包
                if ((idx != -1) || recursive){
                    //如果是一个.class文件 而且不是目录
                    if (name.endsWith(CLASS_FILE_SUFFIX) && !jarEntry.isDirectory()) {
                        //去掉后面的".class" 获取真正的类名
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        classNameList.add(packageName + '.' + className);
                    }
                }
            }
        }
        return packageName;

    }
}
