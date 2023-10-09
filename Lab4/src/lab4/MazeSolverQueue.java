/**
 * @author Moe Ariyoshi
 * Spring 2023
 */

package lab4;

public class MazeSolverQueue extends MazeSolver {
    MyQueue<Square> worklist;

    MazeSolverQueue(Maze maze) {
        super(maze);
    }

    @Override
    void makeEmpty() {
        this.worklist = new MyQueue<Square>();

    }

    @Override
    boolean isEmpty() {
        if (this.worklist.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void add(Square sq) {
        this.worklist.enqueue(sq);
    }

    @Override
    Square next() {
        return this.worklist.dequeue();
    }

}
