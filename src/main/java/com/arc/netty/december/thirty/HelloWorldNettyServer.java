package com.arc.netty.december.thirty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;

/**
 * 测试服务端
 *
 * @author yechao
 * @date 2020/12/30 9:50 上午
 */
public class HelloWorldNettyServer {

    @SneakyThrows
    public static void main(String[] args) {

        // 1 获得链接 parent  接收连接 会把连接发送给worker
        NioEventLoopGroup bossEventExecutors = new NioEventLoopGroup();

        // 2 处理 child
        NioEventLoopGroup workerEventExecutors = new NioEventLoopGroup();

        try {

            // 3 简化服务端启动的 轻松启动服务器   3.1  处理器  3.2 子处理器
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossEventExecutors, workerEventExecutors).channel(NioServerSocketChannel.class)
                    .childHandler(new HelloWorldChannelInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            bossEventExecutors.shutdownGracefully();
            workerEventExecutors.shutdownGracefully();

        }

    }
}
