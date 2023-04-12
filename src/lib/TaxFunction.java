package lib;

public class TaxFunction {
	private static final int NonTaxableSingle = 54000000;
	private static final int NonTaxableMarried = NonTaxableSingle + 4500000;
	private static final int NonTaxableChild = 1500000;

	private static final double TaxRate = 0.05;

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		checkMonthWorking(numberOfMonthWorking);
		int nmbrOfChildren = checkNumberOfChild(numberOfChildren);
		int isMarriedCheck= checkIsMarried(isMarried, monthlySalary, otherMonthlyIncome, numberOfMonthWorking, deductible, nmbrOfChildren);
		int chckTax = checkTax(isMarriedCheck);
		return chckTax;
	}

	private static void checkMonthWorking(int numberOfMonthWorking){
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
	}

	private static int checkNumberOfChild(int numberOfChildren){
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		return numberOfChildren;
	}

	private static int checkIsMarried(boolean isMarried, int monthlySalary, int otherMonthlyIncome,
			int numberOfMonthWorking, int deductible, int numberOfChildren) {
		int tax = 0;
		int nonTaxableIncome = NonTaxableSingle;

		if (isMarried) {
			nonTaxableIncome = NonTaxableMarried;
		} 

		nonTaxableIncome += numberOfChildren * NonTaxableChild;
		int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - nonTaxableIncome;

		if (taxableIncome > 0){
			tax = (int) Math.round(TaxRate * taxableIncome);
		}
		return tax;
	}

	private static int checkTax(int tax){
		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}

}
