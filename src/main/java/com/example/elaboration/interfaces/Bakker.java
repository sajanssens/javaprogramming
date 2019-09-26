package com.example.app.interfaces;

import java.util.List;

public class Bakker {
    public void bak(Kneedbaar k) {
        if (k instanceof Deeg) {
            k.kneed();
            System.out.println("Deeg is gekneed");
        } else {
            System.out.printf("Dit kneedbaar materiaal (%s) is niet geschikt voor brood bakken.. \n", k.getClass().getSimpleName());
        }
    }

    public void bak(List<Kneedbaar> kneedbaars) {
        for (Kneedbaar kneedbaar : kneedbaars) {
            bak(kneedbaar);
        }
    }
}
