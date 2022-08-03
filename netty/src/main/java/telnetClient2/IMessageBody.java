package telnetClient2;

import java.nio.ByteOrder;

import org.jboss.netty.buffer.ChannelBuffer;

public interface IMessageBody
{
	/**
	 * 버퍼로 부터 데이터를 읽어 들여 객체의 멤버들을 초기화한다.
	 * <p/>
	 * @param buffer 채널버퍼
	 * @param receiveSize 채널버퍼로 부터 읽을 바이트 사이즈 
	 */
	public void readFromBuffer (ChannelBuffer buffer, int receiveSize);
	/**
	 * 객체의 멤버변수 값을 채널버퍼에 write 한다.
	 * <p/>
	 * @param buffer 채널버퍼
	 */
	public void writeToBuffer(ChannelBuffer buffer);
	/**
	 * 객체(멤버변수들)의 바이트 사이즈를 알아낸다. 
	 * <p/>
	 * @return 바이트 사이즈
	 */
	public int getByteSize();
	/**
	 * 객체를 채널버퍼 형태로 변환한다.
	 * <p/>
	 * @param byteOrder 바이트 순서 (LITTLE_ENDIAN 혹은 BIG_ENDIAN)
	 * @return  객체의 멤버변수 값이 기록된 채널버퍼
	 */
	public ChannelBuffer toChannelBuffer(ByteOrder byteOrder);
	/**
	 *  객체를 스트링 형태로 변환한다.
	 * <p/>
	 * @return 객체내의 멤버변수의 값들을 변환한 스트링 
	 */
	public String toString();
}