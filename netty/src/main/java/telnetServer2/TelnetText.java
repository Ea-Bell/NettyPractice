package telnetServer2;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.HeapChannelBufferFactory;

public class TelnetText implements IMessageBody {

	
	private int number;
	private String str;
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

	@Override
	public void readFromBuffer(ChannelBuffer buffer, int size) {
		// TODO Auto-generated method stub
		
		byte[] dest = new byte[5];
		buffer.readBytes(dest);
		this.str = new String(dest)+"\r\n";
	}

	@Override
	public void writeToBuffer(ChannelBuffer buffer) {
		byte[] bytes = str.getBytes();
		buffer.writeBytes(bytes, 0, bytes.length);
		
	}

	@Override
	public int getByteSize() {
		//getByteSize를 구하는 이유는 
		return str.length();
	}

	@Override
	public ChannelBuffer toChannelBuffer(ByteOrder byteOrder) {
		ChannelBuffer buffer = null;
		ChannelBufferFactory factory = HeapChannelBufferFactory.getInstance(byteOrder);;
		buffer=factory.getBuffer(getByteSize());
		this.writeToBuffer(buffer);
		return buffer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TelnetText [str=");
		builder.append(str);
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
}
