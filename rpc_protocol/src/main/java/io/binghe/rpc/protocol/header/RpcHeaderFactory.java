package io.binghe.rpc.protocol.header;

import io.binghe.rpc.common.id.IdFactory;
import io.binghe.rpc.constants.RpcConstants;
import io.binghe.rpc.protocol.enumeration.RpcType;

public class RpcHeaderFactory {
    public static RpcHeader getRequestHeader(String serializationType){
        RpcHeader header = new RpcHeader();
        long requestId = IdFactory.getId();
        header.setMagic(RpcConstants.MAGIC);
        header.setRequestId(requestId);
        header.setMsgType((byte) RpcType.REQUEST.getType());
        header.setStatus((byte) 0x1);
        header.setSerializationType(serializationType);
        return header;
    }

}
