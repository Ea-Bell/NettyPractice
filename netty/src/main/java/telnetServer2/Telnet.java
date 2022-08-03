package telnetServer2;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;



public class Telnet {
	static final boolean SSL = System.getProperty("ssl") !=null;
	static final int PORT = Integer.parseInt(System.getProperty("port",SSL?"8992": "8023"));
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	NioServerSocketChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
	ServerBootstrap bootstrap = new ServerBootstrap(factory);
	bootstrap.setPipelineFactory(new TelnetServerPipelineFactory());
	bootstrap.bind(new InetSocketAddress(PORT));
	}

}
