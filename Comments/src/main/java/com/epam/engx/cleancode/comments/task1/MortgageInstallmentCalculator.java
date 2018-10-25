package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    public static final int MONTHS = 12;
    public static final double PERCENT = 100.0;

    /**
     * @param principal    principal amount
     * @param term         term of mortgage in years
     * @param interestRate rate of interest
     * @return monthly payment amount
     */
    public static double calculateMonthlyPayment(
            int principal, int term, double interestRate) {

        //cannot have negative loanAmount, term duration and rate of interest
        if (principal < 0 || term <= 0 || interestRate < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }

        // Convert interest rate into a decimal - eg. 6.5% = 0.065
        interestRate = convertInterestRateToDecimal(interestRate);

        // convert term in years to term in months
        double termInMonths = convertTermToMonths(term);

        double monthlyPayment;
        //for zero interest rates
        if (interestRate == 0)
            monthlyPayment = principal / termInMonths;
        else {
            double monthlyRate = interestRate / 12.0;
            monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
        }

        return monthlyPayment;
    }

    private static double convertInterestRateToDecimal(double interestRate) {
        interestRate /= PERCENT;
        return interestRate;
    }

    private static int convertTermToMonths(int years) {
        return years * MONTHS;
    }
}
