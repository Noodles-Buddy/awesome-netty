package awaresome.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 * 描述: 基于'dev_server_start' 分支的改进版 启动类
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 16:12
 */
public class NettyServer {

    private static final int BEGIN_PORT = 8000;

    /**
     * 引导类
     * 1.添加了attr属性 及 option属性
     * 2.端口绑定，异步，添加监听机制
     *
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        final AttributeKey<Object> clientKey = AttributeKey.newInstance("clientKey");
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                //给服务端的channel，添加自定义属性
                .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                //给每一条连接指定自定义属性
                .childAttr(clientKey, "clientValue")
                //系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //开启TCP底层心跳机制
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //关闭Nagle算法（要求高实时性，有数据发送时就马上发送，就关闭）
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        System.out.println(ch.attr(clientKey).get());
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
