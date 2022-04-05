package design;
/*
The Problem:
Reconciliation is a term Addepar uses for a set of correctness and consistency measures applied to 
the data we receive and use in financial calculations.
One of the most common reconciliation checks is called *unit reconciliation*, which answers the question, 
"does the transaction history add up to the number of shares the bank says I have?". For example, if the 
bank said I had 100 shares of Apple at the end of yesterday, and I bought 20 shares of Apple today, then 
we expect the bank to report 120 shares at the end of today. This surprisingly isn't always the case! The 
bank may send incomplete data, we may be parsing it incorrectly, or there may be events like corporate 
actions or trade settlement lag that cause an inconsistency.
Unit reconciliation is very important, because numbers that don't add up shouldn't be trusted for any metrics.

The Input:
recon.in has three sections:
D0-POS describes the positions in the account at the end of Day 0. Each record is a space-separated pair of Symbol 
and Shares. For example "AAPL 10" means 10 shares of AAPL were held at the end of Day 0, and "Cash 122.16" means we 
had $122.16 in the account at the end of Day 0.
D1-TRN describes the transactions that occurred in the account on Day 1. Each record is space-separated collection 
of four items: Symbol, Transaction Code, Shares, and Total Value. For example, the record "AAPL BY 10 6123.21" means 
10 shares of AAPL were bought for a total cost of $6123.21.
D1-POS describes the positions in the account at the end of Day 1, and has the same format as D0-POS.
The Output:
The objective is to write a program that produces recon.out. Each line should be a space-separated record indicating 
a position that fails unit reconciliation. For example, "AAPL 10" means that the reported shares of AAPL in D1-POS is 
10 higher than expected based on the transactions.
recon.in
--------
D0-POS
AAPL 100
GOOG 200
Cash 10
D1-TRN
AAPL SL 50 30000
GOOG BY 10 10000
D1-POS
AAPL 50
GOOG 220
Cash 20000
recon.out
---------
Cash -10
GOOG 10
*/

/*
 * 1. read the file : 3 typed 
 * 2. deal the data: for D0-POS: use a hash map to store stock(key) shares(val)
 * 					 for D1-TRN: calculate it (buy new stock ---> put in the map, no enough---> mistake)
 * 					 for D1-1: compare the result with my result(no share at all for a stock?? )
 * 3.output diff
 * 
 */
import java.util.List;
import java.util.HashMap;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class Reconciler {
  private static HashMap<String, Long> position = new HashMap<>();
  private static double cash = 0.0;
  private final static String PART1 = "D0-POS";
  private final static String PART2= "D1-TRN";
  private final static String PART3 = "D1-POS"; 
  
  // split 3 kinds of data 
  private static void recon(List<String> lines) {
	  String type = "";
	  // read line by line
	  for (String line : lines) {
		  line = line.trim();
		  if (line.equals(PART1)) {
			  type = PART1;
			  continue;
		  }
		  else if (line.equals(PART2)) {
			  type = PART2;
			  continue;
		  }
		  else if (line.equals(PART3)) {
			  type = PART3;
			  continue;
		  }
		  else if (line.equals("")) {
			  continue;
		  }
		  
		  String[] data = line.split(" ");
		  
		  if (type.equals(PART1)) {
			  // build map
			  getOriData(data);
		  }
		  else if (type.equals(PART2)) {
			  // recalculate
			  calTran(data);
		  }
		  else if (type.equals(PART3)) {
			  check(data);
		  }
		  		  
	  }
  }
  // build map data 
  // AAPL 100
  // GOOG 200
  // Cash 10
  private static void getOriData(String[] data) {
	  String str = data[0].trim();
	  long  num = Long.parseLong(data[1].trim());
	  
	  if (str.equals("Cash")) {
		  cash = Double.parseDouble(data[1].trim());
	  }
	  else {
		  position.put(str, num);
	  }
  } 
  
  // implement calculate
  // AAPL SL 50 30000
  // GOOG BY 10 10000
  private static void calTran(String[] data) {
	  String str = data[0].trim();
	  String trade = data[1].trim();
	  long num = Long.parseLong(data[2].trim());
	  double money = Double.parseDouble(data[3].trim());
	  
	  if (trade.equals("SL")) {
		  // mistake detect
		  // sell stock we don't have cause negative stock position
		  if (!position.containsKey(str)) {
			  num = 0 - num;
			  cash += money;
			  System.out.println("There might be a mistake. It seems over sell.");
		  }
		  else {
			  cash += money;
			  num = position.get(str) - num;
		  }
	  }
	  else if (trade.equals("BY")) {
		  cash -= money;
		  num = position.getOrDefault(str, (long)0) + num;
	  }
	  
	  position.put(str, num);
  } 
  // check and out put diff
  //  D1-POS
  //  AAPL 50
  //  GOOG 220
  //  Cash 20000
  private static void check(String[] data) {
	  String str = data[0].trim();
	  long num = Long.parseLong(data[1].trim());
	  
	  if (str.equals("Cash")){
		  double m = Double.parseDouble(data[1].trim());
		  double diff = m - cash;
		  if (diff != 0.0) {
			  System.out.println("Cash " + diff);
		  }
	  }
	  else {
		  if (!position.containsKey(str)) {
			  System.out.println("There is no " + str + "in D1_POS");
		  }
		  else {
			  long hold = Long.parseLong(data[1].trim());
			  long diff = hold - position.get(str);
			  if (diff != 0) {
				  System.out.println(str + " " + diff);
			  }
		  }
	  }
  } 
  

  public static void main(String[] args) throws IOException {
	// read file
    List<String> lines = Files.readAllLines(Paths.get("recon.txt"));
    
    // deal the data
    recon(lines);
  }
  

}