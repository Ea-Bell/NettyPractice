package netty.netty.testnetty;

import static org.hamcrest.CoreMatchers.both;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Ignore;
import org.junit.Test;

import junit.framework.Assert;

public class NettyTest {

	
	@Test
	@Ignore // 순수 junit으로 test를 하지 않을때 Ignore를 줘서 테스트를 하지 못하게 막는다.
	public void test() {
		ByteBuffer firstBuffer = ByteBuffer.allocate(11);
		System.out.println("초기상태: "+firstBuffer);
		
		firstBuffer.put((byte)1);
		System.out.println(firstBuffer.get());
		System.out.println(firstBuffer);
	}
	
	@Test
	@Ignore
	public void test2() {
		System.out.println("test2");
		
		ByteBuffer firstBuffer = ByteBuffer.allocate(11);
		System.out.println("초기 상태: "+ firstBuffer);
		
		
		firstBuffer.put((byte)1);
		firstBuffer.put((byte)2);
	
		assertEquals("테스트 실패",3, firstBuffer.position());
		
		firstBuffer.rewind(); //postion을 0으로 옮김
		assertEquals(0, firstBuffer.position());
		
		
	
		assertEquals(1,  firstBuffer.get());
		assertEquals(1, firstBuffer.position());
		
		System.out.println(firstBuffer);

	}
	
	@Test
	@Ignore
	public void test3() {
		byte[] tempArray= {1,2,3,4,5,0,0,0,0,0,0};
		ByteBuffer firstBuffer = ByteBuffer.wrap(tempArray);
		
		checkAssertEqualsMethod(0, firstBuffer.position());
		checkAssertEqualsMethod(11,  firstBuffer.limit());
		
		firstBuffer.put((byte)1);
		firstBuffer.put((byte)2);
		firstBuffer.put((byte)3);
		firstBuffer.put((byte)4);
		
		checkAssertEqualsMethod(4,  firstBuffer.position());
		checkAssertEqualsMethod(11,  firstBuffer.limit());
		
		firstBuffer.flip();
		checkAssertEqualsMethod(121, firstBuffer.position());
		checkAssertEqualsMethod(4, firstBuffer.limit());
	}
	
	@Test
	@Ignore
	public void fileopTest() throws IOException {
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles.txt";
		
		@SuppressWarnings("resource")
		BufferedReader  bufferedReader  = new BufferedReader(new FileReader(path), 16 * 1024);
			String str;
			while((str =bufferedReader.readLine())!=null) {
				System.out.println(str);
			}
	}
	
	
	@Test
	public void fileInputStreamTest() throws FileNotFoundException {
		
		String path = "C:\\Users\\LeeChangJong\\Desktop\\nettyTest\\nettyTest\\bundles3.txt";
		try(
		FileInputStream fileInputStream = new FileInputStream(new File(path));
	)	{
			int readBuffer = 0;
			byte[] buffer = new byte[1024];
			while((readBuffer = fileInputStream.read(buffer))!= -1) {
				System.out.println(new String(buffer));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("종료");
		
	}
	
	
	public void checkAssertEqualsMethod(int expected, int actual ) {
		assertEquals("Error생김",expected, actual);
	}
}
