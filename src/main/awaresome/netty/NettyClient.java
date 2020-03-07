package awaresome.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * 描述:client端 最精简版本
 *
 * 问题：
 * 1.Bootstrap && ServerBootStrap 区别
 * 2.NioServerSocketChannel.class && NioSocketChannel.class 区别
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 16:23
 */
public class NettyClient {

    private static int MAX_RETRY = 5;

    /**
     * client端处理：
     * 1.指定线程模型  2.指定 IO 类型为 NIO  3.IO 处理逻辑
     *
     * 可优化：
     * 1.失败重连
     *
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
            // 1.指定线程模型
            .group(workerGroup)
            // 2.指定 IO 类型为 NIO
            .channel(NioSocketChannel.class)
            // 3.IO 处理逻辑
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                }
            });
        // 4.建立连接
        bootstrap.connect("127.0.0.1", 8001).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("连接成功!");
            } else {
                System.out.println("连接失败!");
            }
        });
    }

    /**
     * 失败重连机制
     * bootstrap.config() 返回 BootstrapConfig
     * bootstrap.config().group() 返回 WorkerGroup
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry 重试次数
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host, port).addListener(future -> {
            if(future.isSuccess()){
                System.out.println("client连接成功！");
            }else if(retry == 0){
                System.out.println("client连接失败！重连次数已用完！");
            }else{
                //第几次重连
                int times = MAX_RETRY - retry + 1;
                //重连间隔
                int delay = 1 << times;
                bootstrap.config().group().schedule(() ->
                    connect(bootstrap, host, port, retry -1), delay, TimeUnit.SECONDS);
            }
        });
    }

}
