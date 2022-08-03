package telnetServer;

import java.net.Inet4Address;
import java.util.Date;

import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class TelnetServerHandler extends SimpleChannelUpstreamHandler {


	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		// TODO Auto-generated method stub
		//Firing an event to the next handler
		if( e instanceof ChannelStateEvent) {
			System.err.println(e);
		}
		System.out.println("handleUpstream");
		super.handleUpstream(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
        // Cast to a String first.
        // We know it is a String because we put some code in TelnetPipelineFactory.
		
		String request = (String)e.getMessage();
		
		// Generate and write a response.
		
		String response;
		boolean close = false;
		if(request.length() == 0) {
			response = "Please type something. \r\n";
		}else if("bye".equals(request.toLowerCase())) {
			response = "Have a good day!\r\n";
			close= true;
		}else {
			response = "Did you say'"+request+"'?\r\n";
		}
		
		// We do not need to write a ChannelBuffer here.
		// We know the encoder inserted at TelnetPipelineFactory will do the conversion.
		ChannelFuture future = e.getChannel().write(response);
		
	     // Close the connection after sending 'Have a good day!'
	     // if the client has sent 'bye'.
		if(close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
		
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		// TODO Auto-generated method stub
		
		e.getCause().printStackTrace();
		super.exceptionCaught(ctx, e);
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
	     // Send greeting for a new connection.
		e.getChannel().write("Welcone to "+ Inet4Address.getLocalHost().getHostName()+"!\r\n");
		e.getChannel().write("It is" + new Date() + " now.\r\n");
		super.channelConnected(ctx, e);
	}
	
	

}
