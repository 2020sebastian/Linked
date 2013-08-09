import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;


public class MyListTest {
	private MyList<Integer> lst0;
	private MyList<Integer> lst1;
	private Integer[] a;
	@Before
	public void setUp() throws Exception {
		lst0 = new MyList<Integer>();

		a = new Integer[] {3,4,3,5,6,8,6,6,7,5};
		lst1 = new MyList<Integer>();
		for(Integer x: a) {
			lst1.add(x);
		}
	}

	@Test
	public void test1() {
		assertEquals(0, lst0.size());
		assertEquals(true, lst0.isEmpty());
		assertEquals(a.length, lst1.size());
	}
	
	@Test
	public void test2() {
		for(int i = 0; i < a.length; i++) {
			assertEquals(true, lst1.contains(a[i]));
		}
		
	}
	
	@Test
	public void test3() {
		assertEquals(Integer.valueOf(8), lst1.getMax());
	}
	
	@Test
	public void test4()
	{
		lst1.removeAll(3);
		assertEquals(8, lst1.size());
		assertEquals(false, lst1.contains(3));
		lst1.removeAll(6);
		assertEquals(5, lst1.size());
		assertEquals(false, lst1.contains(6));
		lst1.removeAll(5);
		assertEquals(3, lst1.size());
		lst1.removeAll(4);
		assertEquals(2, lst1.size());
		lst1.removeAll(7);
		assertEquals(1, lst1.size());
		lst1.removeAll(8);
		assertEquals(0, lst1.size());
	}

	@Test
	public void test5() {
		Iterator<Integer> p = lst1.iterator();
		Integer[] b = new Integer[lst1.size()];
		int i = 0;
		while(p.hasNext()) {
			 b[i] = p.next();
			 i++;
		}
		Arrays.sort(a);
		Arrays.sort(b);
		assertEquals(a.length, b.length);
		for(int k = 0; k < a.length; k++) {
			assertEquals(a[k], b[k]);
		}
	}
		
	@Test
	public void test6() {
		lst1.removeAt(5);
		assertEquals(Integer.valueOf(7), lst1.getMax());
		lst1.removeAt(7);
		assertEquals(Integer.valueOf(6), lst1.getMax());
	}
	
	@Test
	public void test7() {
		Iterator<Integer> p = lst1.iterator();
		Integer[] arr = new Integer[a.length];
		int i = 0;
		while(p.hasNext()) {
			arr[i++] = p.next();
		}
		lst1.reverse();
		p = lst1.iterator();
		for(int k = arr.length - 1; k >= 0; k--) {
			assertEquals(arr[k], p.next());
		}
	}
}
