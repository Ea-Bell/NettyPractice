package telnetServer2;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TelnetEncode extends SimpleChannelHandler {

	private static final int HEADERBUFFER= 4;

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("writeRequested");
		Object object = e.getMessage();
		
		if(object instanceof Message) {
			System.out.println("writeRequested Message");
			Message message = (Message) object;
			ChannelBuffer channelBuffer = null;
			channelBuffer = message.toChannelBuffer(ByteOrder.BIG_ENDIAN);
			Channels.write(ctx, e.getFuture(), channelBuffer);	
		}else {
			int id = (int)object;
			System.out.println("object가 MessageClass가 아닌것들의 모임  getIdVlaue= "+ id);
			ChannelBuffer headerBuffer = null;
			ChannelBuffer bodyBuffer  = null;
			ChannelBuffer messageBuffer = null;
			String str="Not define HeaderId";
			
			
			ChannelBufferFactory factory = HeapChannelBufferFactory.getInstance(ByteOrder.BIG_ENDIAN);
			headerBuffer=factory.getBuffer(HEADERBUFFER);
			headerBuffer.writeByte(id);
			bodyBuffer=factory.getBuffer(str.getBytes().length+1);
			bodyBuffer.writeBytes(str.getBytes(), 0, str.getBytes().length);
			messageBuffer = ChannelBuffers.wrappedBuffer(headerBuffer, bodyBuffer);
			Channels.write(ctx, e.getFuture(), messageBuffer);
			
		}
		
		
	}
	
	
}
