import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

/*
 * Task:
 * 		given a [["it", "is", 1,] 2,["test", "case"]]
 * 		print every other element and turn words to upper case
 * 		follow up: there might be nested list
 * 		case:[["it", "is",["another"] 1,] 2,["test", "case"]]
 * 
 * Approach:
 * 		1. create a class called Element, attributes contains list of element, Integer, String
 * 		2. push item of the list from the end of the list, if it is a list, flatten it then push
 * 		   to the stack(the stack is like a iterator)
 * 		3. set a flag initial 1, flag = 1 print else stack pop
 * 		4. string to upper case: before print , check it is a string, then use.toUpperCase method
 * Algorithm:
 * 		more like a simulation problem
 * Analysis:
 * 
 * Author: Shilei Wu 02/14/2022 
 */
public class AddeparTech {
	// prepare my test case
	// test case: [["it", "is",["another"] 1,] 2,["test", "example"]]
	private static List<Element> buildTestCase() {
		// build ["another"]
		Element another = new Element("another");
		List<Element> tempList =  new ArrayList<>();
		tempList.add(another);
		Element listElementOne = new Element(tempList);
		
		// build [["it", "is",["another"] 1,] as an element
		ArrayList<Element> listOne = new ArrayList<>();
		listOne.add(new Element("it"));
		listOne.add(new Element("is"));
		listOne.add(listElementOne);
		listOne.add(new Element(1));
		Element eleOne = new Element(listOne);
		
		// build ["test", "example"] as an element
		ArrayList<Element> temp2 = new ArrayList<>();
		temp2.add(new Element("test"));
		temp2.add(new Element("example"));
		Element eleThree = new Element(temp2);
		
		// build[["it", "is",["another"] 1,] 2,["test", "example"]]
		List<Element> testCase = new ArrayList<>();
		testCase.add(eleOne);
		testCase.add(new Element(2));
		testCase.add(eleThree);
		
		return testCase;
	}
	//[["it", "is",["another"] 1,] 2,["test", "example"]]
	// output:  IT ANOTHER 2 EXAMPLE
	
    public static void main(String[] args) {
        // get the test data
    	List<Element> testCase = buildTestCase();
    	
    	// use deque as stack
    	Deque<Element> stack = new LinkedList<>();
    	flatten(testCase, stack);
    	
    	boolean flag = true;
    	while (!stack.isEmpty()) {
    		
    		if (flag) {
    			if (stack.peek().isInt()) 
    				System.out.println(stack.pop().getInt());
    			else
    				System.out.println(stack.pop().getStr().toUpperCase()); // to uppercase
    			
    		}
    		else {
    			if (stack.isEmpty()) break;
    			stack.pop();
    		};
    		// change flag for print every another
    		flag = !flag;
    	}
    	
    }
    // use recursion to flatten nested list
    private static void flatten(List<Element> list, Deque<Element> stack) {
    	
    	for (int i = list.size() - 1; i >= 0; i--) {
    		if (list.get(i).isInt() || list.get(i).isStr()) {
    			// it is the same as .addFirst
    			stack.push(list.get(i));
    		}
    		else {
    			flatten(list.get(i).getList(), stack);
    		}
    	}
    }
}
// class element 
class Element {
	
	private List<Element> nestedList = null;
	private Integer num = null;
	private String str = null;
	
	public Element(String str) {
		this.str = str;
	}
	public Element(Integer num) {
		this.num = num;
	}
	public Element(List<Element> list) {
		this.nestedList = list;
	}
	
	public boolean isInt() {
		return num != null;
	}
	public int getInt() {
		return num;
	}
	
	public String getStr() {
		return str;
	}
	public boolean isStr() {
		return str != null;
	}
	
	public List<Element> getList() {
		return nestedList;
	}
	public boolean isList() {
		return nestedList != null;
	}
}
