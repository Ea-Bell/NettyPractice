package telnetServer2.file;

import org.jboss.netty.channel.Channel;

import telnetServer2.Message;

public interface IFileHandlerService {

	void readFile(Channel Channel, Message message) throws Exception;

}
