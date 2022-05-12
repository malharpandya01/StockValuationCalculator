package stockvaluationcalculator;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Malhar Pandya
 * 
 */
public class StockValuationCalculator {

    
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        double expectedAGR;
        double ROI;
        int years;
        double currentStockPrice;
        double sharesOutstanding;
        double operatingCashFlow;
        double capEx;
        double averageFCFMultiple;
        double averageMarketCap;
        double averageFCF;
        
        System.out.println("|| STOCK VALUATION CALCULATOR ||\n");
        
        System.out.print("Current Stock Price: ");
        currentStockPrice = input.nextDouble();
        
        System.out.print("Shares Outstanding: ");
        sharesOutstanding = input.nextDouble();
        
        System.out.print("Enter Current (Quarterly) Operating Cash Flow: ");
        operatingCashFlow = input.nextDouble();
        
        System.out.print("Enter Current (Quarterly) Capital Expenditure: ");
        capEx = input.nextDouble();
        
        System.out.print("Enter Current Expected Annual Growth Rate (decimal): ");
        expectedAGR = input.nextDouble();
        
        System.out.print("Average Free Cash Flow (past 5 years): ");
        averageFCF = input.nextDouble();
        
        System.out.print("Average Market Cap (past 5 years): ");
        averageMarketCap = input.nextDouble();
        
        System.out.print("After how many years would you like to sell the stock?: ");
        years = input.nextInt();
        
        System.out.print("Percent ROI you want to receive every year, until you sell. (decimal): ");
        ROI = input.nextDouble();
        
        
        double futureCashFlows = 0;
        double discountedFCF;
        double freeCashFlow;
        double sumOfFutureCashFlows = 0;
        double sumOfDCF = 0;
        
        freeCashFlow = operatingCashFlow - capEx;
        
        double FCFarray [] = new double [years];
        
        
            for (double counter = 0; counter <= FCFarray.length; counter ++){

                futureCashFlows = freeCashFlow * (1 + expectedAGR);

                sumOfFutureCashFlows += futureCashFlows;
                
                
                discountedFCF = futureCashFlows / Math.pow(1 + ROI, years);
                
                sumOfDCF += discountedFCF;

            }
            
            
        double terminalValue;
        double marginOfSafety;
        double discountedTerminalValue;
        double intrinsicValue;
        double intrinsicSharePrice;
        double percentMarginOfSafety;
        String valuationMetric = new String();
        
        averageFCFMultiple = averageMarketCap / averageFCF;
        
        
        terminalValue = FCFarray[years - 1] * averageFCFMultiple;
        
        discountedTerminalValue = terminalValue / Math.pow(1 + ROI, years);
        
        intrinsicValue = discountedTerminalValue + sumOfDCF;
        
        intrinsicSharePrice = intrinsicValue / sharesOutstanding;
        
        
        marginOfSafety = intrinsicSharePrice - currentStockPrice;
        
        percentMarginOfSafety = (marginOfSafety / currentStockPrice) * 100;
        
        if (marginOfSafety > 0){
        
            valuationMetric = "UNDERVALUED";
        
        }
        
        else {
        
            valuationMetric = "OVERVALUED";
        
        }
        
        System.out.printf("\nThe intrinsic value of the company is $%.2f, which is $%.2f per share.\n", intrinsicValue, intrinsicSharePrice);
        System.out.printf("The stock is currently %s and it has a margin of safety of %.2f percent.", valuationMetric, percentMarginOfSafety);
            
    }
    
}
