package lab2;

/**
 * An abstract class that defines the public methods a fighter can use.
 */
public abstract class Fighter {
    final static int[] points = new int[] { 0, 10, 15, 20 };
    final static String[] names = new String[] { "Block", "Punch", "Kick", "Fireball" };
    final static int BLOCK = 0;
    final static int PUNCH = 1;
    final static int KICK = 2;
    final static int FIREBALL = 3;
    final static int MAX_ENERGY = 100;

    private int energy = Fighter.MAX_ENERGY;

    /**
     * Get the fighter's energy.
     * 
     * @return The fighter's energy.
     */
    
    public int energy() {
        return energy;
    }

    /**
     * Damage the fighter, decreasing its energy by <code>ouch</code>.
     * 
     * @param ouch The amount the energy is decreased by.
     */
    public void takeDamage(int ouch) {
        energy -= ouch;
    }

    /**
     * Give the fighter 15 energy.
     */
    public void giveEnergy() {
        energy = Math.min(energy + 15, MAX_ENERGY);
    }

    /**
     * Returns the fighter's next move.
     * 
     * @return Returns one of <code>BLOCK</code>, <code>PUNCH</code>,
     *         <code>KICK</code>, or <code>FIREBALL</code>.
     */
    public abstract int fight();

    /**
     * Returns the name of the fighter.
     * 
     * @return The fighter's name.
     */
    public abstract String name();

    /**
     * Returns the fighter's winning catchphrase.
     * 
     * @return The fighter's winning catchphrase.
     */
    public abstract String winning();

    /**
     * Returns the fighter's losing catchphrase.
     * 
     * @return The fighter's losing catchphrase.
     */

    public abstract String losing();
}
