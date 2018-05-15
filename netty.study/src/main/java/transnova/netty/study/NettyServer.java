package transnova.netty.study;
import io.netty.bootstrap.ServerBootstrap;  
import io.netty.channel.*;  
import io.netty.channel.nio.NioEventLoopGroup;  
import io.netty.channel.socket.SocketChannel;  
import io.netty.channel.socket.nio.NioServerSocketChannel;  
  
/** 
 * Created by zhangtong on 2017/8/14. 
 */  
public class NettyServer {  
    private int port;  
  
    private NettyServer(int port) {  
        this.port = port;  
    }  
  
    private void runServer() throws Exception {  
        // EventLoopGroup是用来处理IO操作的多线程事件循环器  
        // bossGroup 接收连接请求  
        EventLoopGroup parentGroup = new NioEventLoopGroup();  
        // workerGroup 处理已经收到的请求  
        EventLoopGroup childGroup = new NioEventLoopGroup();  
        try {  
            // 服务启动器  
            ServerBootstrap sb = new ServerBootstrap();  
            sb.group(parentGroup, childGroup)  
                    //配置 Channel  
                    .channel(NioServerSocketChannel.class)  
                    .childHandler(new ChannelInitializer<SocketChannel>() {  
                        @Override  
                        public void initChannel(SocketChannel ch) throws Exception {  
                            // 注册handler  
                            ch.pipeline().addLast(new ServerHandler());  
                        }  
                    })  
                    // 设置服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝  
                    .option(ChannelOption.SO_BACKLOG, 128)  
                    // 心跳保活  
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  
  
            // 绑定端口，启动服务器  
            ChannelFuture f = sb.bind(port).sync();//.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);  
            // 一直等待直到服务器 socket 关闭  
            f.channel().closeFuture().sync();  
        } finally {  
            // 将所有的event loop都关闭  
            parentGroup.shutdownGracefully();  
            childGroup.shutdownGracefully();  
        }  
    }  
  
    public static void main(String[] args) throws Exception {  
        new NettyServer(9999).runServer();  
        System.out.println("over");  
    }  
}  