package ca.uqac.poo.tp2.model;

import java.util.Random;

/*
    Runnable class to set afraid static boolean of Pigeon to true or false randomly.
 */
public class AfraidRun implements Runnable {
    private Random random = new Random();
    private int speed;

    public AfraidRun(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            float prob = random.nextInt(100); // probability of being afraid (between 0.0 and 1.0)
            float occ = random.nextInt(100); // occurence
            try {
                if (occ <= prob) {
                    if (!Pigeon.isAfraid()) {
                        Pigeon.setAfraid(true);
                        System.out.println("[*] Pigeons are getting afraid!");
                    }
                } else {
                    if (Pigeon.isAfraid()) {
                        Pigeon.setAfraid(false);
                        System.out.println("[*] Pigeons stopped being afraid.");
                    }
                }
                Thread.sleep(10000 / speed); //10s/speed to avoid afraid bool to change to frequently

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
}
