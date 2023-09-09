package io.binghe.rpc.test.scanner;

import io.binghe.rpc.common.reference.RpcReferenceScanner;
import io.binghe.rpc.common.scanner.ClassScanner;
import io.binghe.rpc.common.server.RpcServiceScanner;
import org.junit.Test;

import java.util.List;

public class TestScanner {

    /**
     * 扫描io.binghe.rpc.test.scanner包下所有的类
     */
    @Test
    public void testScannerClassNameList() throws Exception {
        List<String> classNameList = ClassScanner.getClassNameList("io.binghe.rpc.test.scanner");
        classNameList.forEach(System.out::println);
    }

    /**
     * 扫描io.binghe.rpc.test.scanner包下所有标注了@RpcService注解的类
     */
    @Test
    public void testScannerClassNameListByRpcService() throws Exception {
        RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService("io.binghe.rpc.test.scanner");
    }

    /**
     * 扫描io.binghe.rpc.test.scanner包下所有标注了@RpcReference注解的类
     */
    @Test
    public void testScannerClassNameListByRpcReference() throws Exception {
        RpcReferenceScanner.doScannerWithRpcReferenceAnnotationFilter("io.binghe.rpc.test.scanner");
    }
}

