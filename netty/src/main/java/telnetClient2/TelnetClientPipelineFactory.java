package telnetClient2;


import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.ssl.SslContext;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;

public class TelnetClientPipelineFactory implements ChannelPipelineFactory {

	private final SslContext sslCtx;

	public TelnetClientPipelineFactory(SslContext sslCtx) {
		this.sslCtx = sslCtx;
	}

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		// TODO Auto-generated method stub
		
		ChannelPipeline pipeline = Channels.pipeline();
				 
	        if (sslCtx != null) {
	        	pipeline.addLast("ssl", sslCtx.newHandler());
	        }
				pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new TelnetClientEncode());
				//pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
				pipeline.addLast("handler", new TelnetClientHandler());
		return pipeline;
	}
	
	
	

}
