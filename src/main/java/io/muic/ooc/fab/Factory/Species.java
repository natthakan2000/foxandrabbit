package io.muic.ooc.fab.Factory;

import io.muic.ooc.fab.Fox;
import io.muic.ooc.fab.Hunter;
import io.muic.ooc.fab.Rabbit;
import io.muic.ooc.fab.Tiger;

import java.awt.*;

public enum Species {
    RABBIT(0.1,Rabbit .class, Color.BLUE, 8),
    FOX(0.1, Fox.class, Color.green, 20),
    TIGER(0.03, Tiger.class, Color.YELLOW, 60), //change from 40 to 60
    HUNTER(0.0009, Hunter.class, Color.RED, 0);
    private double breedingProbability;
    private Class animalClass;
    private Color color;
    private int foodValue;
    Species(double breedingProbability, Class animalClass, Color color, int foodValue) {
        this.breedingProbability = breedingProbability;
        this.animalClass = animalClass;
        this.color = color;
        this.foodValue = foodValue;
    }
    public double getBreedingProbability() {
        return breedingProbability;
    }
    public Class getAnimalClass() {
        return animalClass;
    }
    public Color getColor() {
        return color;
    }
    public int getFoodValue() {
        return foodValue;
    }
}
