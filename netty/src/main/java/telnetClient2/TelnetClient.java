package telnetClient2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.ssl.SslContext;
import org.jboss.netty.handler.ssl.util.InsecureTrustManagerFactory;


//netty사용시 이벤트의 호출에 순서를 확실하게 알아야 기능 첨삭이 가능하다.
//기본적으로 client - > server통신을 할 경우 protocol? api?가 중요하므로 그에 맞게 servers는 clinet가 요청한 버퍼의 수만큼 읽고
// client는 server에 recived 받을 버퍼 데이터를 받아야한다.
//servers는 그 사이에서 데이터를 처리 해줘야함.
public class TelnetClient {

	static final boolean SSL = System.getProperty("ssl") != null;
	static final String HOST = System.getProperty("host", "192.168.10.162");
	static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8992" : "8023"));
	
	public static void main(String[] args) throws InterruptedException, IOException {
		final SslContext sslCtx;
		
		if(SSL) {
			sslCtx = SslContext.newClientContext(InsecureTrustManagerFactory.INSTANCE);
		}else {
			sslCtx = null;
		}
		
		ClientBootstrap bootstrap = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
		
	
			bootstrap.setPipelineFactory(new TelnetClientPipelineFactory(sslCtx));
			bootstrap.connect(new InetSocketAddress(HOST, PORT));

		
			
	}
}
