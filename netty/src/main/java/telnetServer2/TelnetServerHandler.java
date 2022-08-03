package telnetServer2;


import java.time.LocalDateTime;


import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import telnetServer2.file.FileMessage;
import telnetServer2.service.FileService;
import telnetServer2.service.FileServiceImpl;

public class TelnetServerHandler extends SimpleChannelHandler {

	//fileServiceImple
	private final FileService fileService;
	
	
	public TelnetServerHandler() {
		this.fileService = new FileServiceImpl();
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Message c= (Message)e.getMessage();
		ChannelFuture future;
		Channel channel = e.getChannel();
		int id = c.getId();
		switch (id) {
			case 1:
				if(!(c.getBody() instanceof TelnetText)) {
					break;
				}
				TelnetText tt=	(TelnetText)c.getBody();
				System.out.println(LocalDateTime.now()+" text= "+ tt.toString());
				Message cc= null;
				cc = new Message(tt, id);
				//TODO write error발생 write를 사용하는 방법은?
				// TelnetEncode를 PipelineFactory에 등록은 안시켜서 오류가 났었다.
				future = channel.write(cc);
				future.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("operationComplete");
					}
				});
				break;
	
			case 2 : 
				if(!(c.getBody() instanceof FileMessage)) {
						break;
				}
				fileService.fileRead(channel, e.getMessage());
				break;
				
			case 0:
				System.out.println("End");
				
				//future = channel.write(id);
				future = channel.close();
				future.addListener(new ChannelFutureListener() {
					
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("Invalid command");
					}
				});
				break;
		}
		
//		TelnetText telnetText = (TelnetText) recevieMessage.getBody();
		super.messageReceived(ctx, e);
	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		// TODO Auto-generated method stub
		super.channelOpen(ctx, e);
		
		System.out.println("channelOpen:" + e.getChannel().getId());
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		
		System.out.println("channelClosed:" + e.getChannel().getId());
	}

/*	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("exceptionCaught");
		System.out.println(e.getCause().getMessage());
		 e.getChannel().close();
	
	}*/
	
	
	
}
