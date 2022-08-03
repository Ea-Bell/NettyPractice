package telnetServer2.service;


import java.io.IOException;

import org.jboss.netty.channel.Channel;

public interface FileService {

	void fileOpen(Channel channel, Object msg);

	void fileRead(Channel channel, Object msg) throws IOException, Exception;
	
}
