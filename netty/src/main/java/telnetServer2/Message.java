package telnetServer2;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.buffer.HeapChannelBufferFactory;

public class Message {
	
	private final static int HEADERBUFFER = 4;
	private IMessageBody body;

	private int id;
	
	public Message() {
	}

	public Message(IMessageBody body, int id) {
		this.body = body;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(IMessageBody body) {
		this.body = body;
	}

	public ChannelBuffer toChannelBuffer(ByteOrder byteOrder) {
		ChannelBuffer headerBuffer = null;
		ChannelBuffer bodyBuffer  = null;
		ChannelBuffer messageBuffer = null;
		ChannelBufferFactory factory = HeapChannelBufferFactory.getInstance(byteOrder);
		//heder의 버퍼의 길이는 정해저있다. 이유는 headerBuffer의 writexxx의 길이 만큼의 공간을 byteOrder를 하기 위해서이기 때문에
		// 그만큼의 공간을 확보 해야한다.
		headerBuffer = factory.getBuffer(HEADERBUFFER);
		headerBuffer.writeByte(id);
		bodyBuffer = this.body.toChannelBuffer(byteOrder);
		messageBuffer=ChannelBuffers.wrappedBuffer(headerBuffer, bodyBuffer);
		return messageBuffer;
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [body=");
		builder.append(body);
		builder.append(", id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
