/**
* C202 Program 6
* Prog6.java
* Purpose: To find the shortest path from start city to ending city using a stack.
* @author Tyler Goodwin
* @version 1.0 12/02/16
*/
import java.util.*;
import java.io.*;
public class Prog6 {
    private int CITI; //# of cities
    private int[][] adjacency;
    private int bestcost = Integer.MAX_VALUE;
    ArrayList<Integer> bestpath; 
    public Prog6(int n){
	CITI = n;
	adjacency = new int[CITI][CITI];
	bestpath = new ArrayList<>();
    }
    //Takes a parameter fname and populates a new file f with the string contents of fname.
    public void populateMatrix(String fname){
	File f = new File(fname);
	try{
            Scanner input = new Scanner(f);
            int i,j;
            for(i = 0; i < CITI && input.hasNext(); i++){
		for(j = i; j < CITI && input.hasNext(); j++){
                    if(i == j){
			adjacency[i][j] = 0;
                    }//if i equals j then this will create a new 2D int array for [i][j].
                    else{
			int value = input.nextInt();
			adjacency[i][j] = value;
			adjacency[j][i] = value;
                    }//creates an integer value that is the input to the next int value and sets it equal to both 2D array[i][j] and [j][i].
		}//loops the statemts until j equals CITI and next string input.
            }//loops the statements until i equals CITI and next string input.
            input.close();
        }//Tries to run this to see if there is an error, if not this is what is returned. 
	catch(IOException e){
            System.out.println("File reading failed!");
	}//If an error occures in the try statement then this statement will be printed out.
    }
    //Finds the shortest path using a stack.
    public void search(){
        Stack<Integer> pathStack = new Stack<>();
        boolean[] visitedCities = new boolean[CITI];
        visitedCities[0] = true;
        pathStack.push(adjacency[0][0]);
        int closestCity = 0;
        boolean minFlag = false;
        System.out.print(adjacency[0][0] + " ");
        int mV = 0;
        while(!pathStack.isEmpty()){
            int currentCity = pathStack.peek();
            int min = Integer.MAX_VALUE;
            for(int i = 1; i < CITI; i++){
                if(adjacency[currentCity][i] != 0 && visitedCities[i] != true){
                    if(adjacency[currentCity][i] < min){
                        min = adjacency[currentCity][i];
                        closestCity = i;
                        minFlag = true;
                        mV += min;
                    } //if
                } //if
            } //if
             
            if(minFlag)
            {
                visitedCities[closestCity] = true;
                pathStack.push(closestCity);
                System.out.print(closestCity + " ");
                minFlag = false;
                continue;
            } //if
             
        pathStack.pop();
        } //endwhile
        System.out.println("");
        System.out.println("Cost: " + mV);
    }
    //This method is used to run through netbeans.
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Please type in the number of cities: ");
        int bob = in.nextInt();
        System.out.println("Please type in the file name: ");
        String jim = in.next();
	Prog6 tsp = new Prog6(bob);
	tsp.populateMatrix(jim);
	long start = System.nanoTime();
        tsp.search();
        long stop = System.nanoTime();
        System.out.println("Time taken: " + (stop - start));
    }
}

/*
Outputs (netbeans): 
Please type in the number of cities:  
12 
Please type in the file name:  
tsp12.txt 
0 5 3 8 4 1 11 6 7 10 9 2  
2690 
Time taken: 503885 
 
Please type in the number of cities:  
13 
Please type in the file name:  
tsp13.txt 
0 5 3 8 4 1 11 6 7 10 9 2 12  
2779 
Time taken: 529522 
 
Please type in the number of cities:  
14 
Please type in the file name:  
tsp14.txt 
0 5 3 8 4 1 13 11 6 7 10 9 2 12  
3521 
Time taken: 551430 
 
 
 
 
Please type in the number of cities:  
15 
Please type in the file name:  
tsp15.txt 
0 5 3 8 4 1 13 14 12 2 9 10 7 6 11  
3874 
Time taken: 707583 
 
Please type in the number of cities:  
16 
Please type in the file name:  
tsp16.txt 
0 5 11 8 4 1 9 3 14 13 10 15 12 7 6 2  
5935 
Time taken: 598043 
 
Please type in the number of cities:  
19 
Please type in the file name:  
tsp19.txt 
0 5 11 8 4 1 9 3 14 18 15 12 7 6 10 13 17 16 2  
6812 
Time taken: 1807646 
 
 
 
 
 
Please type in the number of cities:  
29 
Please type in the file name:  
tsp29.txt 
0 27 5 11 8 4 20 1 19 9 3 14 18 24 6 22 26 23 7 15 12 17 13 21 16 10 28 25 2  
11258 
Time taken: 2549256 
*/

