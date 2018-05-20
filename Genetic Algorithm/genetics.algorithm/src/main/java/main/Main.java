package main;

import org.apache.log4j.Logger;

import constants.Constants;
import flight.Flight;
import population.Population;

public class Main {

	final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		Population pop = new Population();
		pop.generatePopulation();
		Flight fittest = pop.getFittest();
		logger.info("Population size : " + pop.getFlights().size());
		logger.info("Fitness threshold : " + Constants.FITNESS_THRESHOLD);
		logger.info("***********************Generation : 0**************************");
		logger.info("Fittest : " + fittest.getFitness());
		logger.info("Fare : $" + fittest.getPrice());
		logger.info("Duration : " + fittest.getTime() + " hours");
		int tries = 1;
		while (tries <= Constants.MIN_GENERATIONS || pop.getFittest().getFitness() < Constants.FITNESS_THRESHOLD) {
			pop.evolve();
			tries++;
		}
		Flight finalFittest = pop.getFittest();
		logger.info("****************************************************");
		logger.info("Number of generations : " + (tries - 1));
		logger.info("Optimal flight fitness : " + finalFittest.getFitness());
		logger.info("Fare : $" + finalFittest.getPrice());
		logger.info("Duration : " + finalFittest.getTime() + " hours");

	}

}
