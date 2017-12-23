package se;

import static org.junit.Assert.*;
import org.junit.*;

public class AccountBookUnitTest {
	@Test
	public void testsubtractBudget() {
		AccountBook accountbook = new AccountBook();
		String supplementBudget[] = {"0", "����", "�� �ֿ�", "171215", "10000"};
		assertArrayEquals(supplementBudget,accountbook.supplementBudget(supplementBudget));
	}

	@Test
	public void testDeleteMember() {
		AccountBook accountbook = new AccountBook();
		String supplementBudget[] = {"0", "����", "�� �ֿ�", "171215", "10000"};
		String supplementBudget2[] = {"1", "����", "�뵷", "171229", "500000"}; 
		accountbook.supplementBudget(supplementBudget);
		accountbook.supplementBudget(supplementBudget2);
		assertArrayEquals(supplementBudget,accountbook.delete(1));
	}
}

