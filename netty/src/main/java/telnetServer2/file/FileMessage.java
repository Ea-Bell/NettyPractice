package telnetServer2.file;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.stream.ChunkedInput;

import telnetServer2.IMessageBody;

public class FileMessage implements IMessageBody  {
	
	private static final int MESSAGELANGHT = 12;
	
	//0 : read
	//1 : write
	//2 : delete
	private int mode;
	private int length; //길이
	private long offset;
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	//원래는 int로 넘어와야하는데 현재 터미널에서는  byte형태로만 넘어와서 형변환 해줘야 한다.
	@Override
	public void readFromBuffer(ChannelBuffer buffer, int receiveSize) {
		// TODO Auto-generated method stub
/*		byte[] dest = new byte[1];
		buffer.readBytes(dest);
		this.mode=Integer.parseInt(new String(dest));
		System.out.println("mode= "+(mode));*/
		
	}

	@Override
	public void writeToBuffer(ChannelBuffer buffer) {
		// TODO Auto-generated method stub
		
		byte[] dest = new byte[5];
		buffer.readBytes(dest);
	}

	@Override
	public int getByteSize() {
		// TODO Auto-generated method stub
		return MESSAGELANGHT;
	}

	@Override
	public ChannelBuffer toChannelBuffer(ByteOrder byteOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileMessage [mode=");
		builder.append(mode);
		builder.append("]");
		return builder.toString();
	}
}
