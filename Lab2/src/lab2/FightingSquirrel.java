package lab2;

/**
 *I have adhered to the Honor Code in this assignment.
 * 
 * @author Moe Ariyoshi
 * Spring 2023
 */

public class FightingSquirrel extends Fighter {
    
    int index = 0;
           
    @Override
    public int fight() {
       int move = index;
       index++;
       if (index == 4) {
           index = 0;
       }
       return move;
       
    }

    @Override
    public String name() {
        return "Yeobie!";
    }

    @Override
    public String winning() {
        return "Go Yeobie!";
    }

    @Override
    public String losing() {
        return "Aw nuts!";
    }
}
