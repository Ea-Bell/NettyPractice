package telnetClient2;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


//클라이언트에서 server로 데이터를 넘길때 어떤 형식으로 넘기는게 좋을까?
// 2022-08-03 

public class TelnetClientHandler extends SimpleChannelHandler {

	@Override
	public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		// TODO Auto-generated method stub
		
		if(e instanceof ChannelEvent) {
			System.err.println(e);
		}
		super.handleUpstream(ctx, e);
	}

	
	
	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
		// TODO Auto-generated method stub
		if(e instanceof ChannelEvent)
		{
			System.err.println(e);
		}
		super.handleDownstream(ctx, e);
	}



	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("messageReceived");
		super.messageReceived(ctx, e);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, e);
	}
	
	  
	  // 현재 채널이 서버와 접속이 되었을때 channelConnected이벤트 발생함
	  // 모든 이벤트 처리는 요서 할것.
	  @Override 
	  public void channelConnected(ChannelHandlerContext ctx,ChannelStateEvent e) throws Exception { // TODO Auto-generated method stub
	  ChannelFuture future = e.getFuture();
	  Channel channel = e.getChannel();
	  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	  
	  System.out.println("channelConnected");
	  
	  // 순서 정리 // 클라이언트와 서버가 연결되어지면 channelConnected가 발생되어짐 연결이 완료된후 원하는 작업을 수행 후 
	  // channel에 대해 write 이벤트를 발생시킨후 writeRequested를 호출한다.
	  Message msg = new Message(); msg.setId(1);
	  String line;
	  
		  for (;;) {
			  line = in.readLine();
			  if(line == null) {
				  break;
			  }
			  future = channel.write(msg+"\r\n");
			  if("bye".equals(line.toLowerCase())) {
				  channel.close();
				  break;
			  }
		  }
//		  System.out.println(line);
	  }
	  
	  
	  
	  
	  @Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		  
		  System.out.println("channelOpen");
		  
	}



	@Override 
	  public void channelClosed(ChannelHandlerContext ctx,ChannelStateEvent e) throws Exception { 
		  // TODO Auto-generated method stub
		  System.out.println("ChannelClosed"); 
		  e.getChannel().close();
	  }
	  
	 	
	

	
	
	
}
