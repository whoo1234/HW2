import org.junit.*;
import static org.junit.Assert.*;

public class testCalculator {
	@Test
	public void testMul() {
		Calculator calc = new Calculator();
		double compareResult = Math.floor(calc.mul(34.2, 6.5)*100)/100;
		assertTrue(compareResult == 222.3);
	}

	@Test
	public void testConvertPoundToKgByTwoDecimalPlace() {
		Calculator calc = new Calculator();
		double compareResult = Math.floor(calc.convertPoundToKg(3)*100)/100;
		assertTrue(compareResult == 1.36);
	}
}