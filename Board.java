import java.lang.Math.*; 
import java.util.ArrayList;
import java.util.*;
//import edu.princeton.cs.algs4.Queue;



/*

 for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                
            }
        }

c
*/

public class Board {

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[] tilesthing;
    private int n;
    public Board(int[][] tiles) {
        n=tiles.length;
        tilesthing = new int[n*n];
        for (int i=0; i<tiles.length; i++) {
            for (int j=0; j<tiles.length; j++) {
                int x=helper(j,i);
                tilesthing[x]=tiles[i][j];
            }

        }

    }

    public int[] helper2(int loc) {
        int[] x=new int[2];
        int c=loc%n;
        int r=loc/n;
        x[0]=c;
        x[1]=r;
        return x;
    }

    public int helper(int col, int row) {
        //((col) % n) + (n * (row))
        return (col%n) + (n*(row));

    }
     
                                           
    // string representation of this board
    public String toString() {
        StringBuilder x= new StringBuilder(); 
        x.append(n);
        x.append("\n");
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                int y=helper(j,i);
                x.append(tilesthing[y]);
                x.append(" ");


            }

            x.append("\n");
        }
        return x.toString();


    }
    

    // tile at (row, col) or 0 if blank
    public int tileAt(int col, int row){

        //j then i 

        
        return tilesthing[helper(col,row)];
    }

    // board size n
    public int size(){
        return n;

    }

    // number of tiles out of place
    public int hamming() {
        int x=0;
        for (int i=0; i<tilesthing.length; i++) {
            if (tilesthing[i]!=i+1 && tilesthing[i]!=0) {
                //System.out.println("hi"+tilesthing[i]);
                //System.out.println("x"+i);
                x=x+1;

            }
        }

        return x;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){


        int z=0;
        for (int i=0; i<tilesthing.length; i++) {
            if (tilesthing[i]!=i+1 && tilesthing[i]!=0) {
                int[] x=helper2(i); //current location
                int[] y=helper2(tilesthing[i]-1);// ideal location
                //System.out.println("hi"+tilesthing[i]);
                //System.out.println("x"+i);

                z=z+(Math.abs(x[0]-y[0]))+(Math.abs(x[1]-y[1]));

            }
        }

        return z;

    }

    

    // is this board the goal board?
    public boolean isGoal() {
        boolean x=true;
        if (hamming()!=0) {
            x=false;
            return x;
        }

        return x; 

    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y==null) {
            return false;
        }
        if (y==this) {
            return true;
        }

        if (y.getClass()!=this.getClass()) {
            return false;
        }

        Board x= (Board) y;

        if (this.size()!=x.size()) {
            return false; 
        }

        for (int i=0; i<this.tilesthing.length; i++) {
            if (this.tilesthing[i]!=x.tilesthing[i]) {
                return false;

            }


        }





        return true;


    }


    private boolean sidechecker(int col, int row) {
       /* if ((col<0 && row<0) || (col<0 &&row>n) || (col>n && row<0) || (col>n &&row>n)  ){ // corners
            return 2;

        }
        if (col<0 || row<0 || row>n || col>n){ // side
            return 3;

        }
        return 4; //none*/
        //System.out.println("sidechecker");
       // System.out.println("col="+col);
      //  System.out.println("row="+row);
        if (col<0 || row<0 || row>=n || col>=n){
            return false;
                    
        }

      //  System.out.println(col);
      //  System.out.println(row);
     //  System.out.println("sidecheckerend");

        return true;

    }

    public int[] copy(int[] tocopy) {
        int[] copyto = new int[tocopy.length];
        for (int i=0; i<tocopy.length; i++) {
            copyto[i] = tocopy[i];

        }

        return copyto;
    }


    // all neighboring boards
    public ArrayList<Board> neighbors() {//Iterable<Board> neighbors() {
        //System.out.println("neighborsfunction");

        ArrayList<Board> neighbor = new ArrayList<Board>(); 
        ArrayList<Integer> neighborindex = new ArrayList<Integer>(); 
        int empty=0;

        for (int i=0; i<tilesthing.length; i++) {
            if (tilesthing[i]==0) {
                empty=i;
                 int[] x=helper2(i);
                 if (sidechecker(x[0], x[1]+1)) {
                    neighborindex.add(helper(x[0], x[1]+1));
                    //System.out.println(helper(x[0], x[1]+1));

                 }
                 if (sidechecker(x[0], x[1]-1)) {
                    neighborindex.add(helper(x[0], x[1]-1));
                    //System.out.println(helper(x[0], x[1]-1));

                 }
                 if (sidechecker(x[0]+1, x[1])) {
                    neighborindex.add(helper(x[0]+1, x[1]));
                    //System.out.println(helper(x[0]+1, x[1]));

                 }
                 if (sidechecker(x[0]-1, x[1])) {
                    neighborindex.add(helper(x[0]-1, x[1]));
                    //System.out.println(helper(x[0]-1, x[1]));

                 }
                break;




         
                // }
            }

          //  System.out.println(Arrays.toString(tilesthing));
        }


      for (int i=0; i<neighborindex.size(); i++) {
          // System.out.println(Arrays.toString(tilesthing));
           int[] tilesthingcopy= copier(tilesthing); 
        


           int temp=0;
        //System.out.println(i);
         //   System.out.println(neighborindex.get(i));
           temp= tilesthingcopy[neighborindex.get(i)];
          tilesthingcopy[neighborindex.get(i)]=tilesthingcopy[empty];
            tilesthingcopy[empty] = temp;

          // int[][] temp2 = new int[n][n];
            //System.out.println(temp);
           int[][] temp2=transfer(tilesthingcopy,n);
         //  System.out.println(Arrays.toString(tilesthingcopy));
          // System.out.println("");
         

           Board board2=create(temp2);
           neighbor.add(board2);
          // System.out.println(board2.toString());







//neighbor[i]=neighborindex[i];
      }


        return neighbor;

    }

    private Board create(int[][] z){
    	Board board2 = new Board(z); 
    	return board2;
    }

 	private int[][] transfer(int[] temporary, int size) {
 		int[][] y= new int[size][size];
 		for (int i=0; i<temporary.length; i++) {
 			int[] x=helper2(i);
 			y[x[1]][x[0]] = temporary[i];

 		}
 		return y;
 	}

   private int[] copier(int[] tiles) { 
   	 int[] tilesthingcopy = new int[tiles.length];
       
           

         for (int j=0; j<tiles.length; j++) {
            tilesthingcopy[j]=tiles[j];
                
        }

        return tilesthingcopy;


   }

    // is this board solvable?
    public boolean isSolvable() {
        // use the previous functions 
        int inversioncount;
        boolean empty=false;
        int counter=0;
        int[] location= new int[2];
        int [] rowmajor= new int[tilesthing.length-1];
        for (int i=0; i<tilesthing.length; i++) {
        	if (tilesthing[i]==0) {
        		location= helper2(i);
        		empty=true;
        		continue;

        	}
        	if (empty==false) {
        		rowmajor[i] = tilesthing[i];
        		continue; 
        	}
        	rowmajor[i-1]=tilesthing[i];
       

        }
      //  rowmajor[0]=3;
       // rowmajor[1]=1;
       // rowmajor[2]=2;
        for (int i=0; i<rowmajor.length-1; i++) {
        	/*if (i==rowmajor.length-1) {
        		break;
        	}*/
        	//int x=idk;


        	for (int j=i+1; j<rowmajor.length; j++) {
        		if (rowmajor[i]>rowmajor[j]) {
        			counter++;
        			//System.out.println("hello"+counter);
        		}

        	}
        }
       // System.out.println(Arrays.toString(rowmajor));
        if (n%2==0) {
        	//even
        	//number of inversions+ row number of blank square -- if even unsolvable 
        	int x=counter+location[1];
        	System.out.print("Board is: ");
        	if (x%2==0) {
        		System.out.println("unsolvable");
        		return false;
        	}
        	else{
        		System.out.println("solvable");
        		return true;
        	}
        }
        else {
        	//odd;
        	if (counter%2!=0){
        		System.out.println("unsolvable");
        		return false;
        	}
        	else{
        		System.out.println("solvable");
        		return true;
        	}
        }


     

   }


    // unit testing (required)
    public static void main(String[] args){
        //scan number in
        //x
        int x=2;
        int count=1;
       // System.out.println("x");


        int [][] grid = new int[x][x];
        for (int i=0; i<x; i++) {
            for (int j=0; j<x; j++) {
                if (count%(x*x)!=0){ 
                    grid[i][j]=count;
                }
                else{
                    grid[i][j]=0;
                }
                count++;
                //System.out.println(count);

            }
        }
       // System.out.println("xx");
 Board board = new Board(grid);
       
        //for (int i=0; i<grid.length; i++) {
          //  for (int j=0; j<grid.length; j++){
               // System.out.println(grid[i][j]);
           // }
       // }
      //  System.out.println("xxx");
    //    for (int i=0; i<Board.tilesthing.length; i++) {
       //     System.out.println(board.tilesthing[i]);
  //      }

      // System.out.println("xxxx");

     //   System.out.println(board.toString());

        /*System.out.println("xxxxx");

        System.out.println(board.hamming());

        System.out.println("xxxxxx");

        System.out.println(board.manhattan());

        System.out.println("xxxxxxx");

        System.out.println(board.isGoal());

        System.out.println("xxxxxxxx");

        System.out.println(board.equals(board));

        System.out.println("xxxxxxxx"); */

        board.neighbors();
        board.isSolvable();


  //      System.out.println("xxxxxxxxx");

        //System.out.println(board.tilesthingcopy);


    }

    

}
