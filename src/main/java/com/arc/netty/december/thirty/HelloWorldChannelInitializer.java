package com.arc.netty.december.thirty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author yechao
 * @date 2020/12/30 9:55 上午
 */
public class HelloWorldChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 下面的这个对象是多例的
        pipeline.addLast("helloWorldHttpServerHandler", new HelloWorldHttpServerHandler());
    }
}

