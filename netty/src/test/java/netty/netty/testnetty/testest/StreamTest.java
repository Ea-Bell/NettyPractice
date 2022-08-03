package netty.netty.testnetty.testest;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.ws.soap.Addressing;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;



public class StreamTest {
	
	//테스트 결론 성능 이슈로 인하여 stream은 for문 보다 느리게 동작을 한다. 빠른 처리를 하기 위해서는 for문으로 데이터를 처리하는게 좋다.
	//다만 가독성과 성능에 대해 크게 신경쓰지 않는곳이라면 stream을 쓰는 방안도 생각해 볼만한거 같다.
	
	
	List<Integer> groupSystemIds = new ArrayList<Integer>();
	int count = 10000000;
	long afterTime;
	long beforeTime;
	boolean isAdmin = false;
	boolean anyMatch;

	@Before
	public void beforeTest() {
		
		for(int i=0; i< count; i++ ) {
			groupSystemIds.add(i);
		}
	}
	
	@After
	public void afterTest() {
		System.out.println("test="+ anyMatch+ " time="+(beforeTime-afterTime)+"ms"+ " count="+ groupSystemIds.size()+"\n");
	}
	
	@Test
	public void stream() {

		afterTime = System.currentTimeMillis();
		System.out.println("afterTime="+afterTime);
		anyMatch = groupSystemIds.stream()
				.anyMatch(s-> s.equals(10000));
		beforeTime = System.currentTimeMillis();
		System.out.println("beforeTime="+beforeTime);
		System.out.println("streamTest");
		
		
	}
	
	@Test
	public void forTimeTest() {
		afterTime = System.currentTimeMillis();
		System.out.println("afterTime="+afterTime);
		for(int nLoop=0; nLoop<groupSystemIds.size(); nLoop++)
		{
			if(groupSystemIds.get(nLoop) ==10000)
			{
				isAdmin = true;
				break;
			}
		}
		
		beforeTime = System.currentTimeMillis();
		System.out.println("beforeTime="+beforeTime);
		System.out.println("forTimeTest");
	}
	
}
