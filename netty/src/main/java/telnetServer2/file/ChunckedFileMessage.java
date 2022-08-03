package telnetServer2.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.jboss.netty.handler.stream.ChunkedInput;

import telnetServer2.Message;


// 이부분에 대해 한번더 생각 할것.
// channel.write를 하면 ChunkedFileMessage는 파일이 다 불러질떄까지 계속 호출이 된다.
//그래서 fileReadSize = this.reader.read(chars, 0, READSIZE); 에 대해 
public class ChunckedFileMessage implements ChunkedInput {

	private boolean bEnd;
	private int fileReadSize;	  //현재 내가 파일을 읽은 크기
	private int size; //파일을 읽을 크기
	private final int EOF = -1;
	private FileInputStream fileInputStream; 
	
	public ChunckedFileMessage (String path) throws FileNotFoundException {
		this.bEnd = false;
		this.fileReadSize = -1;
		this.size = 1024;
		File fl = new File(path);
		this.fileInputStream = new FileInputStream(fl);
		
	}
	
	@Override
	public boolean hasNextChunk() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hasNextChunk발생");
		return !bEnd;
	}
	@Override
	public Object nextChunk() throws Exception {
		Message msg = new Message();
		System.out.println("nextChunk 이벤트 발생");
		
		byte[] buffer = new byte[size];
		fileReadSize = this.fileInputStream.read(buffer);
		if(fileReadSize != EOF) {
			//System.out.println(new String(buffer));
		} else {
			bEnd= true;
		}
		msg.setBody(new FileReadMessage(buffer));
		return msg;
	}

	@Override
	public boolean isEndOfInput() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("isEndOfInput발생");
		return !hasNextChunk();
	}

	@Override
	public void close() throws Exception {
		try { if(this.fileInputStream != null) this.fileInputStream.close();} catch(IOException e) {} 
	}

}
