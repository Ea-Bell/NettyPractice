package telnetClient2;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import telnetClient2.inputservice.IInputService;
import telnetClient2.inputservice.InputService;

public class TelnetClientEncode  extends SimpleChannelHandler{

	  
	  @Override 
	  public void writeRequested(ChannelHandlerContext ctx, MessageEvent e) throws Exception { 
		  // TODO Auto-generated method stub
	  System.out.println("writeREquested");
	  IInputService inputService = new InputService();
	  inputService.textInput(ctx, e);
	  }
	  
}
