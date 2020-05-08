import java.util.ArrayList;
import java.util.Iterator;

public class PriorityQueue <T extends Comparable<T>> extends ArrayList<T> {
	
	public PriorityQueue()
	{
		super(11);
	}
	
	public boolean add(T e)
	{
		if(e == null) throw new NullPointerException();
		super.add(e);
		T temp;
		for(int i = super.size()-1; i != 0; i/=2)
		{
			if (this.get(i).compareTo(this.get((i - 1) / 2)) < 0) {
				temp = this.get((i - 1) / 2);
				this.set((i - 1) / 2, this.get(i));
				this.set(i, temp);
			} else {
				break;
			}

		}
		return true;
	} 
	
	public void clear()
	{
		super.clear();
	}

	public boolean  contains (Object o)
	{
		return super.contains(o);
	}
	
	@Override
	public Iterator<T> iterator() {
		return null;
	}
	
	public boolean offer(T e)
	{
		if(e==null) return false;
		return add(e);
	}
	
	public T peek()
	{
		if (size() == 0) return null;
		return super.get(0);
	}
	
	public T poll()
	{
		if(size()==0) return null;
		T root = this.get(0);
		removeAt(0);
		return root;
	}
	
	public boolean remove(Object o)
	{
		int index = -1;
		for(int i = 0; i < size(); i++)
		{
			if(o.equals(this.get(i))) index = i;
		}
		if(index != -1) 
		{
			removeAt(index);
			return true;
		}
		return false;
	}
	
	private void removeAt(int i)
	{
		super.set(i, super.get(size()-1));
		super.remove(size()-1);
		T temp;
		int j = 0;
		while(j < size())
		{
			if ((j * 2) + 2 < size()) 
			{
				// both children exist
				if (this.get((j * 2) + 1).compareTo(this.get((j * 2) + 2)) < 0) 
				{
					if (this.get((j * 2) + 1).compareTo(this.get(j)) < 0) 
					{
						temp = this.get(j);
						this.set(j, this.get((j * 2) + 1));
						this.set((j * 2) + 1, temp);
						j = (j * 2) + 1;
					}
					else 
					{
						break;
					}
				} 
				else 
				{
					if (this.get((j * 2) + 2).compareTo(this.get(j)) < 0) 
					{
						temp = this.get(j);
						this.set(j, this.get((j * 2) + 2));
						this.set((j * 2) + 2, temp);
						j = (j * 2) + 2;
					}
					else
					{
						break;
					}
				}
			}

			// one child exists
			else if ((j * 2) + 1 < size()) {
				if (this.get((j * 2) + 1).compareTo(this.get(j)) < 0) {
					temp = this.get(j);
					this.set(j, this.get((j * 2) + 1));
					this.set((j * 2) + 1, temp);
					j = (j * 2) + 1;
				}
				else
				{
					break;
				}
			}

			// no children exist
			else {
				break;
			}
			
		}
	}
	
	public int size()
	{
		return super.size();
	}
	
	public Object[] toArray()
	{
		return super.toArray();
	}
	
	public T[] toArray(T[] a)
	{
		return super.toArray(a);
	}

}
