package lab2;

/**
 *I have adhered to the Honor Code in this assignment.
 * 
 * @author Moe Ariyoshi
 * Spring 2023
 */

public class KickyPunchy extends Fighter {
    
    private int moves = 0; 
    private int fight;

    @Override
    public int fight() {
        if (moves % 2 != 0) {
            fight = 1;
        } else {
            fight = 2;;
        }
        moves++;
        return fight;
        
//        switch(expression) {
//        case x:
//          // code block
//          break;
//        case y:
//          // code block
//          break;
//        default:
//          // code block
//        }
        
    }

    @Override
    public String name() {
        return "KickyPunchy";
    }
    
    @Override
    public String winning() {
        return "Kicking and punching towards Victory!";
    }

    @Override
    public String losing() {
        return "KO!";
    }

}
