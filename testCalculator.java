package se;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class testCalculator {
	@Test
	public void testMul() {
		Calculator calc = new Calculator();
		double compareResult = Double.parseDouble(String.format("%.2f", calc.mul(34.2, 6.5)));
		assertTrue(compareResult == 222.3);
	}

	@Test
	public void testConvertPoundToKgByTwoDecimalPlace() {
		Calculator calc = new Calculator();
		double compareResult = Double.parseDouble(String.format("%.2f", calc.convertPoundToKg(3)));
		assertTrue(compareResult == 1.36);
	}
}