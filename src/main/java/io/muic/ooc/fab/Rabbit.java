package io.muic.ooc.fab;

import io.muic.ooc.fab.Factory.Animal;
import io.muic.ooc.fab.Factory.Species;

public class Rabbit extends Animal {
    @Override
    public Location moveToNewLocation() {
        return field.freeAdjacentLocation(getLocation());
    }
    @Override
    protected int getMaxAge() {
        return 40;
    }
    @Override
    protected double getBreedingProbability() {
        return Species.RABBIT.getBreedingProbability();
    }
    @Override
    protected int getMaxLiterSize() {
        return 4;
    }
    @Override
    protected int getBreedingAge() {
        return 5;
    }
}
