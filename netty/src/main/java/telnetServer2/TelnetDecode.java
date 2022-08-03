package telnetServer2;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;

import telnetServer2.file.FileMessage;

public class TelnetDecode extends ReplayingDecoder<TelnetDecode.MessageState> {

	Message message;
	private int id;
	
	public enum MessageState{
		HEADER,Message
	}
	
	public  TelnetDecode() {
		super(MessageState.HEADER, true);
		this.message=new Message();
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, MessageState state)
			throws Exception {
		// TODO Auto-generated method stub
	
		Message recevieMessage = this.message;
		
		//num=buffer.readInt();
		//str=buffer.readchar();
		switch (state) {
		
		//readInt를 할시 비트 연산으로 값을 찾아야 한다.
		case HEADER:
			//id= buffer.readInt();
			byte[] b = new byte[1];
			String str;
			buffer.readBytes(b);
			
			System.out.println("b" + b);
			
			str = new String(b);
			id = Integer.parseInt(str);
			System.out.println("MessageState.HEADER");
			System.out.println("HEADER buffer="+ id);
			recevieMessage.setId(id);
			checkpoint(MessageState.Message);
		
		case Message:
			System.out.println("MessageState.Message");
//			byteStr=buffer.readByte();
			switch (id) {
				case 1:
					TelnetText telnetText = new TelnetText();
					telnetText.readFromBuffer(buffer, 0);
					recevieMessage.setBody(telnetText);
					this.message=new Message();
					checkpoint(MessageState.HEADER);
					return recevieMessage;
				
					//read하는거 없음
				case 2:
					FileMessage fileMessage = new FileMessage(); 
					fileMessage.readFromBuffer(buffer, 0);
					recevieMessage.setBody(fileMessage);
					this.message = new Message();
					checkpoint(MessageState.HEADER);
					return recevieMessage;
//				case 3:
//					FileChunkedMessage fcm = new FileChunkedMessage();
//					fcm.readFromBuffer(buffer, 0);
//					recevieMessage.setBody(fcm);
//					this.message = new Message();
//					checkpoint(MessageState.HEADER);
//					return recevieMessage;
/*				case 0:
					TelnetText telnetText2 = new TelnetText();
					telnetText2.readFromBuffer(buffer, 0);
					recevieMessage.setBody(telnetText2);
					checkpoint(MessageState.HEADER);
					this.message=new Message();
					return recevieMessage;
				*/
				// 원하는 id가 없을시 에러 처리후 종류
				default: 
					//header, body를 생각해서 readBytes를 적어둘것.;
					recevieMessage.setId(0);
					recevieMessage.setBody(null);
					checkpoint(MessageState.HEADER);
					this.message=new Message();
					return recevieMessage;
/*					System.out.println("ddddfasdf");
					recevieMessage.setId(0);
					byte [] def= new byte[1];
					buffer.readBytes(def);
					str = new String(def);
					recevieMessage.setBody(null);
					checkpoint(MessageState.HEADER);
					this.message=new Message();
					return recevieMessage;
*/					
			}
			
			

//			System.out.println("Message buffer="+ (buffer));
//			System.out.println("Message buffer="+ (recevieMessage.getId()));
		}
			
		return null;
	}

}
