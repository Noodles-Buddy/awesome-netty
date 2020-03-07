package awaresome.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * 描述: 精简版 Server启动类
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 16:12
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    /**
     * 引导类
     *
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println("netty server started!");
                        ch.close();
                    }
                });
        bind(serverBootstrap, BEGIN_PORT);
    }

    /**
     * bind() 是异步的方法，调用之后立即返回一个ChannelFuture，用于监听绑定结果
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap,  int port){
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("端口[" + port + "] 绑定成功！");
            } else {
                System.out.println("端口[" + port + "] 绑定失败！");
                bind(serverBootstrap, port +1);
            }
        });
    }

}
