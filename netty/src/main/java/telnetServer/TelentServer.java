package telnetServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.ssl.SslContext;
import org.jboss.netty.handler.ssl.util.SelfSignedCertificate;

public class TelentServer {

	static final boolean SSL = System.getProperty("ssl") !=null;
	static final int PORT = Integer.parseInt(System.getProperty("port",SSL?"8992": "8023"));
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			final SslContext sslCtx;
			if(SSL) {
				SelfSignedCertificate ssc = new SelfSignedCertificate();
				sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
			}else {
				sslCtx= null;
			}
			
			ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
			
			bootstrap.setPipelineFactory(new TelnetServerPipelineFactory(sslCtx));
			
			bootstrap.bind(new InetSocketAddress(PORT));
	}
}
