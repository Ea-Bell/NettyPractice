package telnetClient2.inputservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;

import telnetClient2.Message;

public class InputService implements IInputService {


	@Override
	public void textInput(ChannelHandlerContext ctx, MessageEvent e) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("textInput");
		
		
		
		//구분자를 넣어야 서버에서확인 가능함.
		String str= "abcde\n\r";
		byte[] bt=  str.getBytes();
		byte bta = '3';
		int a = 2;
		//Message msg=(Message)e.getMessage();
		ChannelBuffer headerBuffer  = null;
		ChannelBuffer bodyBuffer = null;
		ChannelBuffer messageBuffer = null;
		ChannelBufferFactory factory  = HeapChannelBufferFactory.getInstance(ByteOrder.BIG_ENDIAN);
		headerBuffer=factory.getBuffer(5);
		
		headerBuffer.writeInt(a);
		
		headerBuffer.writeByte(bta);
		
		
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		String line = in.readLine();
		
		//System.out.println("ddd : " + e.getFuture().getChannel().getId());
		
		bodyBuffer=factory.getBuffer(str.getBytes().length);
		bodyBuffer.writeBytes(str.getBytes(), 0 , str.getBytes().length);
		messageBuffer  = ChannelBuffers.wrappedBuffer(headerBuffer, bodyBuffer);
		
		//e.getChannel().write(messageBuffer);
		
		Channels.write(ctx, e.getFuture(), messageBuffer);
		
		
		
	}
	
	

}
