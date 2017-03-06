package ca.uqac.poo.tp2.model;

import java.util.Random;

public class CrazyRun implements Runnable {
    private Random random = new Random();
    private int speed;

    public CrazyRun(int speed) {
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            int prob = random.nextInt(100);
            int occ = random.nextInt(100);
            try {
                if (occ <= prob) {
                    if (!Pigeon.isCrazy()) {
                        Pigeon.setCrazy(true);
                        System.out.println("[*] Pigeons are getting crazy!");
                    }
                } else {
                    if (Pigeon.isCrazy()) {
                        Pigeon.setCrazy(false);
                        System.out.println("[*] Pigeons stopped being crazy.");
                    }
                }
                Thread.sleep(10000 / speed);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }
}
