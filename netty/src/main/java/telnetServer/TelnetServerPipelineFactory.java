package telnetServer;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.ssl.SslContext;

import telnetServer2.TelnetDecode;
import telnetServer2.TelnetEncode;

public class TelnetServerPipelineFactory implements ChannelPipelineFactory {

	private final SslContext sslCtx;
	
	
	
	public TelnetServerPipelineFactory(SslContext sslCtx) {
		this.sslCtx = sslCtx;
	}

	public ChannelPipeline getPipeline() throws Exception {
		// TODO Auto-generated method stub
		
	
		// Create a default pipeline implementation.
		// Channels로  해당 파이프라인으로 이동해서 데이터를 처리한다.
		ChannelPipeline pipeline = Channels.pipeline();
			
		pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
		
		//decoder로 데이터를 읽은후
		pipeline.addLast("decoder",new TelnetDecode());
		
		//encoder로 해당 클라이언트로 데이터를 내려준다.
		pipeline.addLast("encoder", new StringEncoder());
		
		//핸들러로 decoder로 해당 데이터를 byte로 바뀐것을 그에 맞는 데이터로 처리한다.
		pipeline.addLast("handler", new TelnetServerHandler());
		return pipeline;
	}

}
