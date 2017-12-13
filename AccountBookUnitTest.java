import static org.junit.Assert.*;
import org.junit.*;

public class AccountBookUnitTest {
	@Test
	public void testsubtractBudget() {
		AccountBook accountbook = new AccountBook();
		String supplementBudget1[] = {"0", "����", "�뵷", "171210", "500000"};
		String supplementBudget2[] = {"1", "����", "���ֿ�", "171215", "10000"};
		assertEquals(accountbook.supplementBudget(supplementBudget1)+1, accountbook.supplementBudget(supplementBudget2));
	}

	@Test
	public void testDeleteMember() {
		AccountBook accountbook = new AccountBook();
		String supplementBudget[] = {"0", "����", "�˹�", "171208", "600000"};
		accountbook.supplementBudget(supplementBudget);
		assertTrue(accountbook.delete(0));
	}
}
