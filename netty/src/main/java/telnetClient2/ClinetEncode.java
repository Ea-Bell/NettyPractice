package telnetClient2;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClinetEncode extends SimpleChannelHandler{

	
	
	
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("writeRequested");
		
		
		super.writeRequested(ctx, e);
	}

	
	
	
	
}
