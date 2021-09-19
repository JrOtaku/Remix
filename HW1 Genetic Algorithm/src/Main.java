import java.util.BitSet;
import java.util.Random;
public class Main {
	
	public static void main(String[] args)
	{
		
		Population population = new Population();
		int numGen = 200;
		String[] label = {"Generation", "Maximum Fitness", "Minimum Fitness", "Average Fitness"};
		System.out.printf("%-5s,%-5s,%-5s,%-5s\n", label[0], label[1], label[2], label[3]);
		int[] genData = new int[4];
		for(int i=0; i< numGen; i++)
		{
			//The first data point is the generation number
			genData[0] = i+1;
			
			//The second data point is the max
			genData[1] = population.getMax();
			
			//The third data point is the min
			genData[2] = population.getMin();
			
			//The last data point is the average
			genData[3] = population.getAverage();
			
			population.getNextGen();
			
			System.out.printf("%d,%d,%d,%d\n", genData[0], genData[1], genData[2], genData[3]);
		}
		
		
		
		
//		System.out.println(population.getMax());
//		System.out.println(population.getMin());
//		System.out.println(population.getAverage());
		population.getNextGen();
//		System.out.println(population.getMax());
//		System.out.println(population.getMin());
//		System.out.println(population.getAverage());
		
		
		
//		Random rand = new Random(2);
//		Individual individual = new Individual(rand);
//		System.out.println(individual.getFitness());
//		individual.mutate(rand);
//		System.out.println(individual.getFitness());
	}

}
