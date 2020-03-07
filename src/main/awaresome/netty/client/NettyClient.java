package awaresome.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;


/**
 * 描述: Client、Server 双方通信
 *
 * @Author : zhenhua.zhang
 * @Date: 2020-03-07 19:53
 */
public class NettyClient {

    /**
     * todo 1.ChannalInitializer 作用？
     *
     * @param args
     */
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
            .group(workerGroup)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new FirstClientHandler());
                }
            });
        connect(bootstrap, "127.0.0.1", 8001, 3);
    }

    /**
     * 连接server端，若失败，间隔5s，重试3次
     *
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    private static void connect(Bootstrap bootstrap, String host, int port, int retry){

        bootstrap.connect(host, port).addListener((future)->{
            if (future.isSuccess()){
                System.out.println("Server端连接成功！");
            } else if(retry == 0){
                System.out.println("Server端连接失败，重试次数已用完！");
            } else{
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry -1), 5, TimeUnit.SECONDS);

            }
        });

    }
}
