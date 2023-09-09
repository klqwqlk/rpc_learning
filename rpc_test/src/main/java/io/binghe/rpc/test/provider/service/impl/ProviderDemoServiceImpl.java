package io.binghe.rpc.test.provider.service.impl;

import io.binghe.rpc.test.provider.service.DemoService;
import io.kelin.rpc.annotation.RpcService;

@RpcService(interfaceClass = DemoService.class, interfaceClassName = "io.binghe.rpc.test.scanner.service.DemoService", version = "1.0.0", group = "binghe")
public class ProviderDemoServiceImpl implements DemoService {

}

