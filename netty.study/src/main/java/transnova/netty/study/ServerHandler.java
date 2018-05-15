package transnova.netty.study;
import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.channel.ChannelInboundHandlerAdapter;  
  
/** 
 * Created by zhangtong on 2017/8/14. 
 */  
public class ServerHandler extends ChannelInboundHandlerAdapter {  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
        System.out.println("channelRead: read client back");  
        ByteBuf buf = (ByteBuf) msg;  
        byte[] res = new byte[buf.readableBytes()];  
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中  
        buf.readBytes(res);  
        String resultStr = new String(res);  
        // 接收并打印客户端的信息  
        System.out.println("client message:" + resultStr);  
        // 释放资源  
        buf.release();  
  
        // 向客户端发送消息  
        String response = "connect to client!";  
        // 将消息的字节流写入缓冲区后输出  
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());  
        encoded.writeBytes(response.getBytes());  
        ctx.write(encoded);  
        ctx.flush();  
    }  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
        // 当出现异常就关闭连接  
        cause.printStackTrace();  
        ctx.close();  
    }  
  
}  