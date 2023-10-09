package lab2;

import java.util.Random;

public class FightDriver {

    private static void performAttack(Fighter attacker, Fighter defender, int attack, boolean blocked)
            throws InterruptedException {
        int attackPoints = Fighter.points[attack];

        if (attack == Fighter.BLOCK) {
            System.out.printf("  %s blocks\n", attacker.name());
            return;
        }

        System.out.printf("  %s uses %s which costs %d energy and...", attacker.name(), Fighter.names[attack],
                attackPoints);
        attacker.takeDamage(attackPoints);
        Thread.sleep(500);

        double roll = Math.random();
        if (roll < .4) {
            System.out.println("misses!");
        } else {
            int multiplier = 2;
            if (roll < .9) {
                if (blocked) {
                    System.out.println("hits, but is blocked");
                    multiplier = 1;
                } else {
                    System.out.println("hits!");
                }
            } else {
                System.out.println("scores a CRITICAL HIT!!!");
                multiplier = 3;
            }
            int damage = attackPoints * multiplier;
            defender.takeDamage(damage);
            System.out.printf("  %s loses %d energy\n", defender.name(), damage);
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Fighter[] fighters = new Fighter[3];

        fighters[0] = new Pyro();
        fighters[1] = new KickyPunchy();
        fighters[2] = new FightingSquirrel();

        Random r = new Random();
        int randa = r.nextInt(3);
        int randb = r.nextInt(3);
        while (randb == randa) {
            randb = r.nextInt(3);
        }

//        System.out.println(" " + randa + " " + randb);

        Fighter fighter1 = fighters[randa];
        Fighter fighter2 = fighters[randb];

        System.out.println(fighter1.name() + " is fighting " + fighter2.name());

        int round = 0;
        while (fighter1.energy() > 0 && fighter2.energy() > 0) {
            round++;
            if (round > 1) {
                System.out.println("  Both fighters recover some energy\n");
                fighter1.giveEnergy();
                fighter2.giveEnergy();
            }

            System.out.printf("ROUND %d FIGHT!\n", round);
            System.out.printf("  %s has %d energy\n", fighter1.name(), fighter1.energy());
            System.out.printf("  %s has %d energy\n", fighter2.name(), fighter2.energy());
            Thread.sleep(500);

            int f1attack = fighter1.fight();
            int f2attack = fighter2.fight();

            FightDriver.performAttack(fighter1, fighter2, f1attack, f2attack == Fighter.BLOCK);
            Thread.sleep(500);
            FightDriver.performAttack(fighter2, fighter1, f2attack, f1attack == Fighter.BLOCK);
        }
        System.out.println();
        if (fighter1.energy() <= 0 && fighter2.energy() <= 0) {
            System.out.println("Double KO!");
        } else if (fighter1.energy() <= 0) {
            System.out.printf("%s wins!\n", fighter2.name());
            System.out.printf("%s says \"%s\"\n", fighter2.name(), fighter2.winning());
            System.out.printf("%s says \"%s\"", fighter1.name(), fighter1.losing());
        } else {
            System.out.printf("%s wins!\n", fighter1.name());
            System.out.printf("%s says \"%s\"\n", fighter1.name(), fighter1.winning());
            System.out.printf("%s says \"%s\"", fighter2.name(), fighter2.losing());
        }
    }

}
