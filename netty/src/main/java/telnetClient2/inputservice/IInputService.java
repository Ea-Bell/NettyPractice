package telnetClient2.inputservice;

import java.io.IOException;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

public interface IInputService {

	void textInput(ChannelHandlerContext ctx, MessageEvent e) throws IOException;

}
