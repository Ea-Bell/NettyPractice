package telnetServer2.file;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferFactory;
import org.jboss.netty.buffer.HeapChannelBufferFactory;

import telnetServer2.IMessageBody;

public class FileReadMessage implements IMessageBody {
	private byte[] readbyte = null;
	
	public FileReadMessage(byte[] readbyte) {
		this.readbyte  = readbyte; 
	}
	
	@Override
	public void readFromBuffer(ChannelBuffer buffer, int receiveSize) {
	}
	
	@Override
	public void writeToBuffer(ChannelBuffer buffer) {
			buffer.writeBytes(readbyte);
	}
	@Override
	public int getByteSize() {
		return readbyte.length ;
	}
	@Override
	public ChannelBuffer toChannelBuffer(ByteOrder byteOrder) {
		ChannelBuffer buffer = null;
		ChannelBufferFactory factory = HeapChannelBufferFactory.getInstance(byteOrder);;
		buffer=factory.getBuffer(getByteSize());
		this.writeToBuffer(buffer);
		return buffer;
	}
}
