package telnetServer2.service;



import java.io.FileNotFoundException;

import org.jboss.netty.channel.Channel;

import telnetServer2.file.ChunckedFileMessage;

public class FileServiceImpl implements FileService {
	
	ChunckedFileMessage cf = null;
	@Override
	public void fileOpen(Channel channel,  Object msg) {
		System.out.println("fileOpen");
		
	}
	
	@Override
	public void fileRead(Channel channel,  Object msg) throws Exception  {
	System.out.println("fileOpen");
	//cf = readBundlesFile(channel);  // ??????? 정상적인 파일이 아니라서 오류남
	//cf = readBundlesFile2(channel); // ????? 정상적인 파일이 아니라서 오류남
	cf = readBundlesFile3(channel);
	//cf = readBundlesFile4(channel); 
	
	channel.write(cf);
	}

	private ChunckedFileMessage readBundlesFile(Channel channel) throws FileNotFoundException {
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles.txt";
		ChunckedFileMessage cf=	new ChunckedFileMessage(path);
		return cf;
	}
	
	private ChunckedFileMessage readBundlesFile2(Channel channel) throws FileNotFoundException {
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles2.txt";
		ChunckedFileMessage cf=	new ChunckedFileMessage(path);
		return cf;
	}
	
	private ChunckedFileMessage readBundlesFile3(Channel channel) throws FileNotFoundException {
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles3.txt";
		ChunckedFileMessage cf=	new ChunckedFileMessage(path);
		return cf;
	}

	private ChunckedFileMessage readBundlesFile4(Channel channel) throws FileNotFoundException {
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles4.txt";
		ChunckedFileMessage cf=	new ChunckedFileMessage(path);
		return cf;
	}
}
