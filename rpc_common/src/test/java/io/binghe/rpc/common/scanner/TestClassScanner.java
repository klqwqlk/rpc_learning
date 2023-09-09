package io.binghe.rpc.common.scanner;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestClassScanner {

    @Test
    public void testClassScannerByFile(){

    }

    @Test
    public void testClassScannerByJar() throws IOException {
        List<String> classNameList = ClassScanner.getClassNameList("org.apache.commons.logging.impl");
        classNameList.forEach(System.out::println);
    }
}
