package io.binghe.rpc.test.scanner.consumer.impl;

import io.binghe.rpc.test.scanner.consumer.ConsumerBusinessService;
import io.binghe.rpc.test.scanner.service.DemoService;
import io.kelin.rpc.annotation.RpcReference;

public class ConsumerBusinessServiceImpl implements ConsumerBusinessService {

    @RpcReference(registryType = "zookeeper", registryAddress = "127.0.0.1:2181", version = "1.0.0", group = "binghe")
    private DemoService demoService;


}
