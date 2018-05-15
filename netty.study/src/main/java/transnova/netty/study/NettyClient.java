package transnova.netty.study;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;  
  
/** 
 * Created by zhangtong on 2017/8/14. 
 */  
public class NettyClient {  
    public void connect(String host, int port) throws Exception {  
        EventLoopGroup group = new NioEventLoopGroup();  
  
        try {  
            Bootstrap bootstrap = new Bootstrap();  
            bootstrap.group(group);  
            bootstrap.channel(NioSocketChannel.class);  
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);  
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {  
                @Override  
                public void initChannel(SocketChannel ch) throws Exception {  
                    ch.pipeline().addLast(new ClientHandler());  
                }  
            });  
  
            ChannelFuture f = bootstrap.connect(host, port).sync(); // addListener(ChannelFutureListener.CLOSE_ON_FAILURE);  
  
            f.channel().closeFuture().sync();  
        } finally {  
            group.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        NettyClient client = new NettyClient();  
        client.connect("127.0.0.1", 9999);  
    }  
}  
