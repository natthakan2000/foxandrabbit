package io.muic.ooc.fab;

import io.muic.ooc.fab.Factory.Animal;
import io.muic.ooc.fab.Factory.Methods;
import io.muic.ooc.fab.Factory.Species;

import java.util.List;

public class Hunter extends Animal {
    private int foodLevel;
    @Override
    public void initialise(boolean randomAge, Field field, Location location) {
        super.initialise(randomAge, field, location);
    }
    @Override
    protected int getMaxAge() {
        return Integer.MAX_VALUE;
    }
    protected double getBreedingProbability() {
        return Species.HUNTER.getBreedingProbability();
    }
    @Override
    public Location moveToNewLocation() {
        Location newLocation = findFood();
        if (newLocation == null) {
            newLocation = field.freeAdjacentLocation(getLocation());
        }
        return newLocation;
    }
    @Override
    public void run(List<Methods> newAnimals) {
        //incrementHunger();
        super.run(newAnimals);
    }
//    public void incrementHunger() {
//        foodLevel--;
//        if (foodLevel <= 0) {
//            setDead();
//        }
//    }
    public Location findFood() {
        List<Location> adjacent = field.adjacentLocations(getLocation());
        for (Location where : adjacent) {
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = Species.RABBIT.getFoodValue();
                    return where;
                }
            } else if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = Species.FOX.getFoodValue();
                    return where;
                }
            } else if (animal instanceof Tiger) {
                Tiger tiger = (Tiger) animal;
                if (tiger.isAlive()) {
                    tiger.setDead();
                    foodLevel = Species.TIGER.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }
    @Override
    protected int getMaxLiterSize() {
        return 2;
    }
}
