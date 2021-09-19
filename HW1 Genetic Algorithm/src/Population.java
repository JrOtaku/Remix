import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Population {
	
	Individual[] population = new Individual[100];
	Random rand = new Random(3);
	public Population()
	{
		for (int i=0; i<100; i++)
		{
			population[i] = new Individual(rand);
		}
	}
	
	public void getNextGen()
	{
		Individual[] oldGen = truncate();
		ArrayList<Individual> nextGen = new ArrayList<Individual>();
		for(int i=0; i<50; i++)
		{
			
			nextGen.add(new Individual(oldGen[i], rand));
			nextGen.add(new Individual(oldGen[i], rand));
		}
		
		for(int i=0; i<100; i++)
		{
			population[i] = nextGen.get(i);
		}
	}
	
	public void getNextGenElite()
	{
		Individual[] oldGen = truncate();
		ArrayList<Individual> nextGen = new ArrayList<Individual>();
		nextGen.add(oldGen[0]);
		nextGen.add(new Individual(oldGen[0], rand));
		for(int i=1; i<50; i++)
		{
			
			nextGen.add(new Individual(oldGen[i], rand));
			nextGen.add(new Individual(oldGen[i], rand));
		}
		
		for(int i=0; i<100; i++)
		{
			population[i] = nextGen.get(i);
		}
	}
	
	public void getNextGenRankedSelection()
	{
		Individual[] oldGen = rankedSelection();
		ArrayList<Individual> nextGen = new ArrayList<Individual>();
		for(int i=0; i<50; i++)
		{
			
			nextGen.add(new Individual(oldGen[i], rand));
			nextGen.add(new Individual(oldGen[i], rand));
		}
		
		for(int i=0; i<100; i++)
		{
			population[i] = nextGen.get(i);
		}
	}
	
	public void getNextGenCrossover()
	{
		Individual[] oldGen = truncate();
		Individual[] nextGen = crossover(oldGen);
		//the following code has a chance of doing a point mutation
//		for(int i= 0; i<100; i++)
//		{
//			nextGen[i] = new Individual(nextGen[i], rand);
//		}
		
		for(int i=0; i<100; i++)
		{
			population[i] = nextGen[i];
		}
	}
	
	public Individual[] rankedSelection()
	{
		Arrays.sort(population);
		Individual[] ranked = new Individual[50];
		for(int j=0; j<50; j++)
		{
			int index = rand.nextInt(5050);
			for(int i=0; i<100; i++)
			{
				if(index > (100 - i))
				{
					index = index - (100 - i);
				}
				else
				{
					ranked[j] = population[i];
				}
			}
		}
		return ranked;
	}
	
	public Individual[] crossover(Individual[] oldGen)
	{
		Individual chromosome1;
		Individual chromosome2;
		Individual[] nextGen = new Individual[100];
		for(int i=0; i<100; i++)
		{
			//select two random chromosomes
			int index1 = rand.nextInt(50);
			int index2 = rand.nextInt(50);
			chromosome1 = oldGen[index1];
			chromosome2 = oldGen[index2];
			//use two selected chromosomes and cross over
			nextGen[i] = new Individual(chromosome1, chromosome2, rand);
		}
		return nextGen;
	}
	
	public Individual[] truncate()
	{
		Arrays.sort(population);
		Individual[] truncatedGen = new Individual[50];
		for(int i=0; i<50; i++)
		{
			truncatedGen[i] = population[i];
		}
		return truncatedGen;
	}
	
	public int getMax()
	{
		int max = population[1].getFitness();
		for(int i=0; i<100; i++)
		{
			if (population[i].getFitness() > max)
			{
				max = population[i].getFitness();
			}
		}
		return max;
	}
	
	public int getMin()
	{
		int min = population[1].getFitness();
		for(int i=0; i<100; i++)
		{
			if(population[i].getFitness() < min)
			{
				min = population[i].getFitness();
			}
		}
		return min;
	}
	
	public int getAverage()
	{
		int total = 0;
		for(int i=0; i<100; i++)
		{
			total += population[i].getFitness();
		}
		return total/population.length;
	}
}
