package lab2;

/**
 *I have adhered to the Honor Code in this assignment.
 * 
 * @author Moe Ariyoshi
 * Spring 2023
 */

public class Pyro extends Fighter {
    @Override
    public int fight() {
        if (energy() > 30) {
           return 3;
        } else {
            return 0;
        }
    }

    @Override
    public String name() {
        return "Pyro";
        //return this.getClass().getName();
    }

    @Override
    public String winning() {
        return "Flame on!";
    }
    
    @Override
    public String losing() {
        return "Fire Extinguished :(";
    }

}
