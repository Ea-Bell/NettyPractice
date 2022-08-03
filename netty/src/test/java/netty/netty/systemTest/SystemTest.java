package netty.netty.systemTest;

import org.junit.Test;

public class SystemTest {

	private String osArchitecture = System.getProperty("os.arch");
	private String osName = System.getProperty("os.name");
	private String javaHome = System.getProperty("java.home");
	private String currentDirectory = System.getProperty("user.dir");
	private String userHome = System.getProperty("user.home");
	//32qㅣ트인지 64비트인지 구별하기 위해 추가함.
	private String archDataModel = System.getProperty("sun.arch.data.model");
	
	
	@Test
	public void textTeset() {
		
		System.out.println(osArchitecture);
		System.out.println(osName);
		System.out.println(javaHome);
		System.out.println(currentDirectory);
		System.out.println(userHome);
		System.out.println(archDataModel);
	}
}
