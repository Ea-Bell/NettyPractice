package telnetServer2;

import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.DelimiterBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.Delimiters;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;


public class TelnetServerPipelineFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		// TODO Auto-generated method stub
		
		ChannelPipeline pipeline = Channels.pipeline();
		
		//개행문자가 들어가서 delmiter를 해서 개행 문자들을 없애준다.
		
		pipeline.addLast("delmiter", new DelimiterBasedFrameDecoder(800, Delimiters.lineDelimiter()));
		pipeline.addLast("decoder",new TelnetDecode());
		//encoder로 해당 클라이언트로 데이터를 내려준다.
		pipeline.addLast("encoder", new TelnetEncode());
		//ChunkedWriteHandler는   ChunkedInput을 인터페이스를 받은 객체를  channel.write로 변수로 넘기면 이벤트가 발생한다.
		pipeline.addLast("chunkedWriteHandler", new ChunkedWriteHandler());
		//핸들러로 decoder로 해당 데이터를 byte로 바뀐것을 그에 맞는 데이터로 처리한다.
		pipeline.addLast("handler", (ChannelHandler) new TelnetServerHandler());
		return pipeline;
	}

	
	
}
