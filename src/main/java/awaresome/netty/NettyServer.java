package awaresome.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 描述: netty服务端 启动类
 * 创建一个引导类，指定线程模型，IO模型，连接读写处理逻辑，绑定端口之后，服务端就启动起来了
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 15:45
 */
public class NettyServer {

    /**
     * 简述：
     * Netty Server端启动精简版，1.线程模型 2.IO模型 3.读写逻辑处理 4.bind端口
     *
     * 操作：
     * 使用 'telnet 127.0.0.1 8001' 模拟client请求，参见 initChannel() 逻辑
     *
     * 可优化：
     * 1.监听端口是否绑定成功
     * 2.channel属性 .attr()  || 每条连接 .childAttr()
     * 3.channel选项 .option() || 每条连接 .childOption()
     *
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
            .group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<NioSocketChannel>() {

                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    System.out.println("telnet 请求已收到！");
                    ch.close();
                }
            });
        serverBootstrap.bind(8001);
    }

}
