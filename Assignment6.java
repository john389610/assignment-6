
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author John Vincent
 * Traveling Salesperson Problem with a Stack - Shortest Path Tree.
 * This is a non-recursive Traveling Salesperson Problem solution
 */
public class Assignment6 {
    private int [][] adjacency;
    private int CITI;
    private Stack pathStack ;
   public Assignment6(int n){
       CITI=n;
       adjacency=new int[CITI][CITI];
       pathStack = new Stack();
   }//constructor
   /*fills adjacency Matrix with values form given file
    * @param String fName
    */
   public void populateMatrix(String fName){
        File f=new File(fName);
        try{
            Scanner input = new Scanner(f);
            int i,j;
            for (i = 0; i < CITI&&input.hasNext(); i++)
            {
                for (j = i; j <CITI&&input.hasNext(); j++)
                {
                    if(i==j){
                        adjacency[i][j]=0;
                    }else
                    {
                        
                    
                    int value=input.nextInt();
                    adjacency[i][j]=value;
                    adjacency[j][i]=value;
                    }
                }
            
            }input.close();
        }catch(IOException e){
            System.out.println("file reading failed");
        }
    }//populateMatrix
   /* method  to find the shortest path tree
    *  uses int [][] adjacency, Stack pathStack, and int CITI
    */
   public void spt()
        {            
            int[] visitedCities = new int[CITI];
            visitedCities[0] = 1;
            pathStack.push(0);
            int currentCity, closestCity = 0; 
            int min;
            boolean minFlag = false;
            System.out.print(0 + " ");
            
            while (!pathStack.isEmpty())
            {
                
                currentCity = (int)pathStack.peek();                
                min = Integer.MAX_VALUE;
                for (int i = 1; i < CITI; i++)
                {
                    if (adjacency[currentCity][i] > 0 && visitedCities[i] == 0)
                    {
                        if (min > adjacency[currentCity][i])
                        {
                            min = adjacency[currentCity][i];
                            closestCity = i;
                            minFlag = true;
                            
                        }
                    }                    
                }
                if (minFlag)
                {
                    visitedCities[closestCity] = 1;
                    pathStack.push(closestCity);
                    System.out.print(closestCity + " ");
                    
                    minFlag = false;
                    continue;
                }               
                pathStack.pop();
            }
        }//spt
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numCities=Integer.parseInt(args[0]);
        Assignment6 one=new Assignment6(numCities);
        one.populateMatrix(args[1]);
        System.out.println("Best path for "+ one.CITI);
        one.spt();
        System.out.println("");
        /*//for printing adjacency matrix
        for (int i = 0; i < one.adjacency.length; i++) {
            for (int j = 0; j < one.adjacency[0].length; j++) {
                System.out.print( one.adjacency[i][j]+" ");
                
            }
            System.out.println("");
        }*/
    }//main    
}//class
