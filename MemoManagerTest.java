import static org.junit.Assert.*;
import org.junit.Test;

public class MemoManagerTest {
	
	@Test
	public void testSavePerformed() {
		MemoManager Memo = new MemoManager();
		String path=System.getProperty("user.dir")+".txt";
		String contents="Merry X-mas and Happy New Year!";
		assertTrue(Memo.savePerformed(path, contents));
		assertEquals(contents,Memo.openPerformed(path));
	}
	
	@Test
	public void testDeletePerformed() {
		MemoManager Memo = new MemoManager();
		String path=System.getProperty("user.dir")+"2.txt";
		String contents="Merry X-mas and Happy New Year!";
		Memo.savePerformed(path, contents);
		assertTrue(Memo.deletePerformed(path));
		assertEquals("Not Exist",Memo.openPerformed(path));
	}
}