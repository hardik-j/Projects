package edu.neu.ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import constants.Constants;
import flight.Flight;
import population.Population;

public class GaTest {

    @Test
    public void testFitness() {
        Flight flight = new Flight();
        assertEquals(flight.getFitness(), 0);
    }

    @Test
    public void testFitness1() {
        
    	int[] priceArray = new int[] {0,0,0,0,0,1,1,0,0,1};
    	int[] timeArray = new int[] {0,0,1,0,0,0,0,0,0,1};
        Flight flight = new Flight();
        flight.setPriceArray(priceArray);
        flight.setTimeArray(timeArray);
        assertEquals(flight.getFitness(), 5);
    }

    @Test
    public void testFitness2() {
    	int[] priceArray = {1,1,0,0,0,1,1,0,0,1};
    	int[] timeArray = {1,0,1,0,0,0,0,0,0,1};
        Flight flight = new Flight();
        flight.setPriceArray(priceArray);
        flight.setTimeArray(timeArray);
        assertEquals(flight.getFitness(), 8);
       
        
    }

    @Test
    public void testPopulation() {
    	Population pop = new Population();
    	pop.generatePopulation();
    	assertEquals(pop.getFlights().size(), Constants.POPULATION_SIZE);
    	int tries = 1;
    	while (tries <= Constants.MIN_GENERATIONS || pop.getFittest().getFitness() < Constants.FITNESS_THRESHOLD) {
			pop.evolve();
			tries++;
		}
	    assertTrue(pop.getFittest().getFitness() >= Constants.FITNESS_THRESHOLD);
        
    }

	
}