package com.jack.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by jack on 2018/2/2.
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeLine = socketChannel.pipeline();
        //超过15分钟未收到客户端消息则自动断开客户端连接
        //pipeLine.addLast("idleStateHandler",
               // new IdleStateHandler(15, 0, 0, TimeUnit.MINUTES));
        //ch.pipeline().addLast(new Decoder4LoggingOnly());
        // 1024表示单条消息的最大长度，解码器在查找分隔符的时候，达到该长度还没找到的话会抛异常
        pipeLine.addLast(
                new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(new byte[] { 0x7e }),
                        Unpooled.copiedBuffer(new byte[] { 0x7e, 0x7e })));
        //pipeLine.addLast(new TCPServerHandler());

    }
}
