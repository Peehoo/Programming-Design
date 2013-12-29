import junit.framework.Assert;

import org.junit.Test;


public class TestPrefix {
	@Test
	public void test1(){
		Prefix P = new Prefix(2);
		Prefix Q= new Prefix(2);
		P.addWord("I");
		P.addWord("am");
		P.addWord("Peehoo");
		Q.addWord("I");
		Q.addWord("am");
		Q.addWord("Peehoo");
		Assert.assertEquals(true, P.equals(Q));
	}
	
	@Test
	public void test2(){
		Prefix P = new Prefix(2);
		P.addWord("I");
		P.addWord("am");
		P.addWord("Peehoo");
		Prefix R = new Prefix();
		Assert.assertEquals(false, P.equals(R));
	}
	
	@Test
	public void test3(){
		Prefix R = new Prefix();
		Prefix Q = new Prefix();
		Assert.assertEquals(true, R.equals(Q));
	}
	
	@Test
	public void test4(){
		Prefix P = new Prefix();
		Assert.assertEquals(false, P.equals(new String("abc")));
	}
}
