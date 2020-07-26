package io.muic.ooc.fab;


import io.muic.ooc.fab.Factory.Animal;
import io.muic.ooc.fab.Factory.FieldPopulator;
import io.muic.ooc.fab.Factory.Methods;
import io.muic.ooc.fab.Factory.Species;
import io.muic.ooc.fab.view.SimulatorView;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

public class Simulator {

    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;
//    // The probability that a fox will be created in any given grid position.
//    private static final double FOX_CREATION_PROBABILITY = 0.02;
//    // The probability that a rabbit will be created in any given position.
//    private static final double RABBIT_CREATION_PROBABILITY = 0.08;

    // Lists of animals in the field.
    private List<Methods> animals;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private SimulatorView view;
    // Random generator
    private static final Random RANDOM = new Random();

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be >= zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        animals = new ArrayList<>();
        field = new Field(depth, width);
        view = new SimulatorView(depth, width);
        Species[] animalTypes = Species.values();
        for (int i = 0; i < animalTypes.length; i++) {
            view.setColor(animalTypes[i].getAnimalClass(), animalTypes[i].getColor());
        }

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation for the given number of steps. Stop before the given
     * number of steps if it ceases to be viable.
     *
     * @param numSteps The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && view.isViable(field); step++) {
            simulateOneStep();
            delay(1000);   // uncomment this to run more slowly
        }
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;
        List<Methods> newAnimals = new ArrayList<>();
        for (Iterator<Methods> it = animals.iterator(); it.hasNext();) {
            Animal animal = (Animal) it.next();
            animal.run(newAnimals);
            if (!animal.isAlive()) {
                it.remove();
            }
        }
        animals.addAll(newAnimals);

        view.showStatus(step, field);
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        animals.clear();
        new FieldPopulator().populate(field, animals);
//        populate();

        // Show the starting state in the view.
        view.showStatus(step, field);
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
//    private void populate() {
//
//        field.clear();
//        for (int row = 0; row < field.getDepth(); row++) {
//            for (int col = 0; col < field.getWidth(); col++) {
//                if (RANDOM.nextDouble() <= FOX_CREATION_PROBABILITY) {
//                    Location location = new Location(row, col);
//                    Fox fox = new Fox(true, field, location);
//                    foxes.add(fox);
//                } else if (RANDOM.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
//                    Location location = new Location(row, col);
//                    Rabbit rabbit = new Rabbit(true, field, location);
//                    rabbits.add(rabbit);
//                }
//                // else leave the location empty.
//            }
//        }
//    }

    /**
     * Pause for a given time.
     *
     * @param millisec The time to pause for, in milliseconds
     */
    private void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ignored) {
        }
    }
}
