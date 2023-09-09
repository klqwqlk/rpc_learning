package io.binghe.rpc.codec;

import io.binghe.rpc.serializaiton.jdk.JdkSerialization;
import io.binghe.rpc.serialization.api.Serialization;

public interface RpcCodec {

    default Serialization getJdkSerialization(){
        return new JdkSerialization();
    }
}

