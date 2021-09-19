import java.util.BitSet;
import java.util.Random;

public class Individual implements Comparable<Individual>{
	
	BitSet chromosome= new BitSet(100);
	public Individual(Random rand)
	{
		for (int i=0; i<100; i++)
		{
			chromosome.set(i, rand.nextBoolean());
		}
	}
	
	public Individual(Individual p1, Individual p2, Random rand)
	{
		int crossoverpoint = rand.nextInt(100);
		for (int i=0; i < 100; i++)
		{
			if(i < crossoverpoint)
			{
				this.chromosome.set(i, p1.chromosome.get(i));
			}
			else
			{
				this.chromosome.set(i, p2.chromosome.get(i));
			}
		}
	}
	
	public Individual(Individual p, Random rand)
	{
		for(int i=0; i<100; i++)
		{
			this.chromosome.set(i, p.chromosome.get(i));
		}
		for(int i=0; i<100; i++)
		{
			if(rand.nextInt(100)==0)
			{
				chromosome.flip(i);
			}
		}
	}
	
	public int getFitness()
	{
		return chromosome.cardinality();
	}

	@Override
	public int compareTo(Individual o) {
		if (getFitness() < o.getFitness())
		{
			return 1;
		}
		else if(getFitness() > o.getFitness())
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}

}
