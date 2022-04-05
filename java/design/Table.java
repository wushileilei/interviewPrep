package design;
/*
 * database/ table roll back
 * a class that populate a table like a nested hash map structure
 * {row1 : {col1: foo, col2 : bar}, row2 : {col1:baz}} ---> row a hasmap col nested inside 
 * 
 * function: 
 * createRow(rowName), do nothing if the name already exists
 * deleteRow(rowName): do nothing if a row with that name doesn't exist
 * updateCell(rowName, columnName, newVal): if the row does not exist, do nothing.
 * These actions are grouped together in transactions
 * 
 * beginTransaction(): do nothing if a transaction has already begun
 * commitTransaction(): do nothing if not currently in a transaction.
 * rollbackTransaction(): Revert the data back and end it. Do nothing if not currently in a transaction.
 */
/*	table_map<rowName, row>    Row class: rowName rowmap<colName, colVal>
 * 	boolean inTransaction? true for beigin : false for commit
 *  operation calss : functions rowName, colname, colval, row
 *  rollback: list to track our operations
 * 
 */
  
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
//Table RollBack
// Task: design a class contains functions
//     1.createRow(
//     2.deletRow              
//     3.updateCell
//     4.commitTran 
//     5.beginTran,
//     6.rollBackTran
// do nothing if not applicable
// if (tran): 1,2,3

// Approach: 
//      1.hashmap for the table
//      2.begin commit: boolean flag true or false
//      3:rollback put operations in stack, do the opposite  
//         clear the stack after commit
// Implementation:
//      1. table map<rowName, row>, the row is another map. 
//           my own Row class with attribute rowName, rowMap<colName, val>
//      2. stack for operation 
//            another class operation with attributes: type, rowName, Row, colName, val
class Result {

    Map<String, Row> tableMap = new HashMap<>();
    LinkedList<Operation> operationList = new LinkedList<>();
    boolean isInTransaction = false;

    public void createRow(String rowName){
        // not applicable
        if(!isInTransaction) return;
        if(tableMap.containsKey(rowName)) return;
        
        tableMap.put(rowName, new Row(rowName));

        operationList.add(new Operation("Create", rowName, "", "", null));
    }
    public void deleteRow(String rowName){
        if(!isInTransaction) return;
        if(!tableMap.containsKey(rowName)) return;
        
        Row row = tableMap.remove(rowName);

        operationList.add(new Operation("Delete", rowName, "", "", row));
    }
    public void updateRow(String rowName, String colName, String colValue){
        if(!isInTransaction) return;
        if(!tableMap.containsKey(rowName)) return;
        
        // save data before update for roll back
        Row row = tableMap.get(rowName);
        row.map.put(colName, colValue);

        operationList.add(new Operation("Update", rowName, colName, colValue, row));
    }

    public void beginTransaction(){
        isInTransaction = true;
    }

    public void commitTransaction(){
        isInTransaction = false;
    }

    public void rollbackTransaction(){

        //if(operationList.size() >0) {
            while (operationList.size() > 0) {
                Operation o = operationList.poll();
                if (o.action.equals("Create")) {
                    tableMap.remove(o.rowName);
                } else if (o.action.equals("Delete")) {
                    tableMap.put(o.rowName, o.r);
                } else if (o.action.equals("Update")) {
                    if(!tableMap.containsKey(o.rowName)) {
                        return;
                    }
                    tableMap.get(o.rowName).map.put(o.colName, o.colVal);
                }
            }
        //}
        // after commit we can't roll back 
        operationList.clear();
    }
}

// My Operation  and Row Class
class Operation {
    String action, rowName;
    String colName, colVal;
    Row r;
    public Operation(String action, String rowName, String colName, String colVal, Row r){
        this.action = action;
        this.rowName = rowName;
        this.colName = colName;
        this.colVal = colVal;
        this.r = r;
    }
}

class Row {
    String name;
    Map<String,String> map;

    public Row(String rowName){
        this.name = rowName;
        map = new HashMap<>();
    }
    // for output test
    public void getRow(String name) {
        for (String colName: map.keySet()) {
            if (colName == null) {
                return;
            }
            System.out.print(colName + "-");
            System.out.print(map.get(colName));
            
        }
    }
}



public class Table {
    public static void main(String[] args) throws IOException {
//		Test Result:
//    	SoftDrinks: 
//    		-----------------------
//    		SoftDrinks: COKE-PEPSI
//    		-----------------------
//    		SoftDrinks: COKE-PEPSI
//    		Dairy: 
//    		-----------------------
//    		SoftDrinks: COKE-PEPSI
//    		Dairy: MILK-Organic Valley
//    		-----------------------
//    		SoftDrinks: COKE-PEPSI
//    		-----------------------
//    		Now table is empty
//    		-----------------------
        Result r = new Result();
        r.beginTransaction();
        r.createRow("SoftDrinks");
        printTable(r.tableMap);
        r.updateRow("SoftDrinks", "COKE", "PEPSI");
        printTable(r.tableMap);
        r.createRow("Dairy");
        printTable(r.tableMap);
        r.updateRow("Dairy", "MILK", "Organic Valley");
        printTable(r.tableMap);
        r.deleteRow("Dairy");
        //
        printTable(r.tableMap);
        r.rollbackTransaction();
        printTable(r.tableMap);
        r.commitTransaction();
        
    }
    private static void printTable(Map<String, Row> table) {
        if (table.size() == 0) {
            System.out.println("Now table is empty");
        }
        for (String rowName : table.keySet()) {
            System.out.print(rowName + ": ");
            table.get(rowName).getRow(rowName);    
            System.out.println();
        }
        System.out.println("-----------------------");
    }
}