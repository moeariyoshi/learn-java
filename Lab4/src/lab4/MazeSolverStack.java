/**
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab4;

public class MazeSolverStack extends MazeSolver {
    MyStack<Square> worklist;

    MazeSolverStack(Maze maze) {
        super(maze);
    }

    @Override
    void makeEmpty() {
        this.worklist = new MyStack<Square>();
    }

    @Override
    boolean isEmpty() {
        if (worklist.isEmpty()) {
            return true;
        } else {
            return false;
        }
        
    }

    @Override
    void add(Square sq) {
        this.worklist.push(sq);

    }

    @Override
    Square next() {
        return this.worklist.pop();
    }
    
    

}
