import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * A Main Driver class that takes in a Matrix and finds the relations in it
 *  
 * @author Mohamed Kaid
 */
public class Main {

	public static void main(String[] args) {
		//Scanner and variables
		Scanner input = new Scanner(System.in);
		String fileName ="hi.txt", st=null, answer=null;
		int row = 10, column = 10;
		boolean[][] arr	= new boolean[row][column];
		boolean value;

		//loop through the program until user quits
		do {
		//setting up the array
		arr=setArray(row,column,fileName);

		//checking if matrix is Reflexive
		value = Reflexive(arr,row,column);
		if(value==true) {
			st="Yes";
		}else
			st="No";
		System.out.println("Reflexive:\t"+st);

		//checking if matrix is antiReflexive
		value = antiReflexive(arr,row,column);
		if(value==true) {
			st="Yes";
		}else
			st="No";
		System.out.println("AntiReflexive:\t"+st);

		//checking if matrix is Symmetric
		value = Symmetric(arr,row,column);
		if(value==true) {
			st="Yes";
		}else
			st="No";
		System.out.println("Symmetric:\t"+st);

		//checking if matrix is antiSymmetric
		value = antiSymmetric(arr,row,column);
		if(value==true) {
			st="Yes";
		}else
			st="No";
		System.out.println("AntiSymmetric:\t"+st);
		
		//asking the user if they want to continue or end the program
		System.out.print("\nWant to run the program again (yes or no): ");
		answer= input.nextLine();
		System.out.println();
		}while(!answer.equalsIgnoreCase("no"));
		
		System.out.println("Thank you, Have a nice day!");
	}

	/**
	 * Asks for the file name reads the matrix and stores it into a 2d array
	 * @returns the array with the matrix
	 */
	public static boolean[][] setArray(int row, int col,String fileName) {
		//temp 2d array
		boolean[][] temp = new boolean[row][col];
		Scanner input = new Scanner(System.in);
		int tempNum=0;
		boolean tempValue;
		//asking the user for the file name
		System.out.print("Enter File Name: ");
		fileName = input.nextLine();
		System.out.println();
		//try and catch block
		try(
				//scan the file that was taking form the user
				Scanner scan = new Scanner(new File(fileName));
				){
			//loop through the file and store the contents into the 2d array
			while(scan.hasNextLine()) {
				for(int i=0;i<row;i++) {
					for(int j=0;j<col;j++) {
						tempNum=scan.nextInt();
						if(tempNum==0) {
							tempValue =false;
						}else
							tempValue =true;

						temp[i][j]=tempValue;
					}
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//if you want to view the matrix that is being tested uncomment this next block of code
		/*		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}*/


		//returning the temp 2d array
		return temp;

	}
	
	
	/**
	 * runs thorough out the array and finds the variables that are the same like [{1,1}{2,2}..]
	 * @returns true if the 2d array is REFLEXIVE
	 */
	public static boolean Reflexive(boolean[][] temp,int row, int col) {
		//variables used through out the method
		int counter = 0;
		int value =0;
		boolean result = true;
		
		//temp Array used to store all the variables that are the same {1,1}{2,2}
		boolean[] tempArray = new boolean[row];
		
		//for loop the just loops through the rows
		for(int i=0;i<row;i++) {
			//i = to the row number but counter is the column number then store it to the tempArray
			tempArray[i]= temp[i][counter];;
			counter++;
		}
		
		//loop through the tempArray and check if they are all 1's or if anyone equals 0 result = false;
		for(int x=0;x<tempArray.length;x++) {
			if(tempArray[x]==false) {
				result =false;
			}
		}
		return result;
	}
	
	
	/**
	 * runs thorough out the array and finds the variables that are the same like [{1,1}{2,2}..]
	 * @returns true if the 2d array is ANTIREFLEXIVE
	 */
	public static boolean antiReflexive(boolean[][] temp,int row, int col) {
		//variables used through out the method
		int counter = 0;
		int value =0;
		boolean result = true;
		
		//temp Array used to store all the variables that are the same {1,1}{2,2}
		boolean[] tempArray = new boolean[row];
		
		//for loop the just loops through the rows
		for(int i=0;i<row;i++) {
			//i = to the row number but counter is the column number then store it to the tempArray
			tempArray[i]= temp[i][counter];;
			counter++;
		}
		//loop through the tempArray and check if they are all 0's or if anyone equals 1 result = false;
		for(int x=0;x<tempArray.length;x++) {
			if(tempArray[x]==true) {
				result =false;
			}
		}
		return result;
	}

	/**
	 * runs thorough out the array and finds the variables ignoring the ones that are in the diagonal [{1,1}{2,2}..]
	 * if the variables equal then it is true
	 * @returns true if the 2d array is Symmetric
	 */
	public static boolean Symmetric(boolean[][] temp,int row, int col) {
		//variables used through out the method
		boolean valueOne;
		boolean valueTwo;
		boolean result = true, zeroChecker = true;

		//for loop the just loops through the rows and columns
		for(int i=0;i<row;i++) {
			for(int j=1;j<col;j++) {
				//Checking if i and j are the same like 1,1 or 2,2 so they can be ignored 
				if(i==j) {
					continue;
				}else
				//storing i,j into valueOne and j,i into valueTwo to be compared
				valueOne = temp[i][j];
				valueTwo = temp[j][i];
				
				//this is to check if they are all 0's then it is automatically true
				if(valueOne==true || valueTwo==true) {
					zeroChecker=false;
				}
				//checking if valueOne does NOT equal valueTwo then it is false not symmetric
				if(valueOne!=valueTwo) {
					result = false;
				}
			}
		}

		//if the zeroChecker is true then that means the matrix is all zeros ignoring the diagonal ones
		if(zeroChecker==true) {
			result=true;
		}
		return result;

	}

	/**
	 * runs thorough out the array and finds the variables ignoring the ones that are in the diagonal [{1,1}{2,2}..]
	 * if one variable equal 1 and the other 0 then it is true
	 * @returns true if the 2d array is AntiSymmetric
	 */
	public static boolean antiSymmetric(boolean[][] temp,int row, int col) {
		//variables used through out the method
		boolean valueOne;
		boolean valueTwo;
		boolean result = true, zeroChecker = true;
		
		//for loop the just loops through the rows and columns
		for(int i=0;i<row;i++) {
			for(int j=1;j<col;j++) {
				//Checking if i and j are the same like 1,1 or 2,2 so they can be ignored
				if(i==j) {
					continue;
				}else
				//storing i,j into valueOne and j,i into valueTwo to be compared
				valueOne = temp[i][j];
				valueTwo = temp[j][i];
				
				//this is to check if they are all 0's then it is automatically true
				if(valueOne==true || valueTwo==true) {
					zeroChecker=false;
				}
				//checking if valueOne does NOT equal valueTwo then it is false not symmetric
				if(valueOne==valueTwo) {
					result = false;
				}
			}
		}

		//if the zeroChecker is true then that means the matrix is all zeros ignoring the diagonal ones
		if(zeroChecker==true) {
			result=true;
		}
		return result;

	}

}
