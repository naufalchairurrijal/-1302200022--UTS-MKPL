package lib;

public class TaxFunction {
	private static final int NoTaxableForSingle = 54000000;
	private static final int NoTaxableForMarried = NoTaxableForSingle + 4500000;
	private static final int NoTaxableForChild = 1500000;

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
		int noTaxableForIncome = NoTaxableForSingle;

		if (isMarried) {
			noTaxableForIncome = NoTaxableForMarried;
		} 
		noTaxableForIncome = noTaxableForIncome +  (numberOfChildren * NoTaxableForChild);
		int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - noTaxableForIncome;

		if (taxableIncome > 0){
			tax = (int) Math.round(0.05 * taxableIncome);
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
