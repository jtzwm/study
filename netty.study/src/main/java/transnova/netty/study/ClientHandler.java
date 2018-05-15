package transnova.netty.study;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
/** 
 * Created by zhangtong on 2017/8/14. 
 */  
public class ClientHandler extends ChannelInboundHandlerAdapter {  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("channelRead: read server back");  
        ByteBuf buf = (ByteBuf) msg;  
        byte[] res = new byte[buf.readableBytes()];  
        buf.readBytes(res);  
        System.out.println("server message:" + new String(res));  
        // 引用计数减1，到达零的时候，回收空间  
        buf.release();  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        // 当出现异常时就关闭连接  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
  
    // 连接成功后，向server发送消息  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        String msg = "connect to server!";  
        ByteBuf message = ctx.alloc().buffer(4 * msg.length());  
        message.writeBytes(msg.getBytes());  
        ctx.write(message);  
        // ctx.write(Object)方法不会使消息写入到通道上，他被缓冲在了内部，需要调用ctx.flush()把缓冲区中数据强行输出。  
        // 或者用更简洁的cxt.writeAndFlush(msg)  
        ctx.flush();  
    }  
}  