import edu.princeton.cs.algs4.*;
import java.util.*;
import java.util.ArrayList;

public class Solver {
private boolean solvable; 
private int counter;
private SearchNode solutionNode;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

         
    	if (initial == null) {

    		throw new IllegalArgumentException();
    	}
        if (initial.isSolvable() == false) {

            throw new IllegalArgumentException();
        }

        MinPQ<SearchNode> queue = new MinPQ<SearchNode>(); 

        SearchNode node= new SearchNode(initial,0,null);
        queue.insert(node);
        boolean isSolved = false; 
        while (!isSolved) {
       
            SearchNode smllestkey= queue.delMin();
            Board smallest = smllestkey.getCurrentBoard();
            ArrayList<Board> list= smallest.neighbors();
            counter=smllestkey.getNumMoves();
            isSolved=smallest.isGoal();
            //System.out.println(isSolved);
            if (!isSolved) {
                counter++;
            } 
            else {
                solutionNode=smllestkey;
            }

            for (int i=0; i<list.size(); i++) {
                SearchNode  x = new SearchNode(list.get(i), counter, smllestkey); 

                queue.insert(x);


            }


        }

    }



    // min number of moves to solve initial board
    public int moves() { 
        System.out.println("Number of smallest moves: "+counter); 

    	return counter;
    }

    // sequence of boards in a shortest solution
   public Iterable<Board> solution(){
        ArrayList<Board> x= new ArrayList<Board>();
        System.out.println("Key: ");
        while(solutionNode!=null) {
            x.add(solutionNode.getCurrentBoard());
            System.out.println(solutionNode.getCurrentBoard().toString());
            System.out.println("");
            solutionNode=solutionNode.getPrvNode();


        }
      //  System.out.println(x.toString());
        return x;



   }

    // test client (see below) 
    public static void main(String[] args){
        In file = new In(args[0]);
        int x=file.readInt();
        //int count=1;
       // System.out.println("x");


        int [][] grid = new int[x][x];
        for (int i=0; i<x; i++) {
            for (int j=0; j<x; j++) {
                grid[i][j]=file.readInt();
                
                //count++;
                //System.out.println(count);

            }
        }

        //for (int i=0; i<x ; i++)

        /*grid[0][0]=1;
        grid[0][1]=2;
        grid[1][0]=0;
        grid[1][1]=3;*/
       // System.out.println("xx");
        Board board = new Board(grid);
        System.out.println(board.toString());
        Solver hi = new Solver(board);
        hi.moves();
        hi.solution();


    }


    private class SearchNode implements Comparable<SearchNode>  {
        private Board currentBoard=null;
        private int numMoves=0;
        private SearchNode prvSearch = null;
        private int priority=0;

        public SearchNode(Board current, int moves, SearchNode prv) {
            currentBoard=current;
            numMoves=moves;
            prvSearch=prv;
            priority=currentBoard.hamming();



        }
        public int compareTo(SearchNode passed) {
            //System.out.println("hi");
            return this.priority-passed.priority;

        }

        public Board getCurrentBoard() {
            return currentBoard;

        }
        public int getNumMoves() {
            return numMoves;
            
        }
        public SearchNode getPrvNode() {
            return prvSearch;         
        }
        public int getPriority() {
            return priority;
            
        }





    }

} 


