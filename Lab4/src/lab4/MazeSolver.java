/**
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab4;

import java.util.ArrayList;
import java.util.List;

abstract class MazeSolver {
    public static final int STACK = 1, QUEUE = 2;
    protected Maze maze;
    protected boolean finished = false;
    protected boolean pathFound = false;
    private Square endSquare;
    
    
    

    MazeSolver(Maze maze) {
       this.maze = maze;
       makeEmpty();
       add(maze.getStart());
    }

    //Creates empty work list
    abstract void makeEmpty();

    //Returns true if work list is empty
    abstract boolean isEmpty();

    //Adds square to work list
    abstract void add(Square sq);

    //Returns next item from work list 
    abstract Square next();

    //Solved the maze or not 
    public boolean isFinished() {
        return finished;
    }

    //if there is path or not. 
    public boolean pathFound() {
        return pathFound;
    }

    /*
     * makes a list of the squares on a path from the start square to the exit
     * square
     */
    public List<Square> getPath() {
        ArrayList<Square> reversePath = new ArrayList<Square>();
        Square currentSquare = this.endSquare;
        
        while(currentSquare != null) {
            currentSquare.setOnPath();
            reversePath.add(currentSquare);
            currentSquare = currentSquare.getPrevious();
//            if (currentSquare.getType() == 2) {
//                break;
//            }
        }
        
        ArrayList<Square> path = new ArrayList<Square>();
        
        for (int i = reversePath.size()-1; i >= 0; i--) {
            path.add(reversePath.get(i));
        }
        
        return path;
    }

    /* performs one step of the maze solver algorithm */
    public void step() {
        if (isEmpty()) {
            this.finished = true;
        } else {
            Square currentSquare = next();
            //currentSquare.mark();
            
            if (currentSquare.getType() == 3) {
                this.finished = true;
                this.pathFound = true;
                this.endSquare = currentSquare;
                
                
            } else {
                ArrayList<Square> neighbors = maze.getNeighbors(currentSquare);
                
                for (int i = 0; i < neighbors.size(); i++) {
                    Square neighbor = neighbors.get(i);
                    if (!neighbor.isMarked() && neighbor.getType() != 1 && neighbor.getType() != 2) {
                        add(neighbor);
                        neighbor.mark();
                        neighbor.setPrevious(currentSquare);
                    }
                }
            }
        }    
       
    }
}
