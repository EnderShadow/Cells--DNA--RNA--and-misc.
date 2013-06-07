package cell.geneticcode;


public class DNA extends GeneticCode
{
	private char[] activeSide;
	private char[] inactiveSide;
	
	private int DNAlength = 0;
	
	public DNA(int length, boolean autoGenerate)
	{
		length = (length / 3) * 3;
		DNAlength = length;
		if(autoGenerate)
		{
			GeneRandomizer gr = new GeneRandomizer(length);
			DNA temp = gr.randomizeGenesDNA();
			activeSide = temp.activeSide;
			inactiveSide = temp.inactiveSide;
		}
		else
		{
			activeSide = new char[length];
			inactiveSide = new char[length];
		}
	}
	
	public DNA(int length)
	{
		this(length, false);
	}
	
	public int getLength()
	{
		return DNAlength;
	}
	
	public char[] getActiveSide()
	{
		return activeSide;
	}
	
	public char[] getInactiveSide()
	{
		return inactiveSide;
	}
	
	public char[][] getDNA()
	{
		char[][] genes = new char[activeSide.length][2];
		for(int i = 0; i < activeSide.length; i++)
		{
			genes[i][0] = activeSide[i];
			genes[i][1] = inactiveSide[i];
		}
		return genes;
	}
	
	public void setActiveSide(char[] genes)
	{
		activeSide = genes;
		inactiveSide = getOpposite(genes);
		DNAlength = genes.length;
	}
	
	public void setActiveSide(int index, char value)
	{
		activeSide[index] = value;
		inactiveSide[index] = getOpposite(value);
	}
	
	public void setInactiveSide(char[] genes)
	{
		inactiveSide = genes;
		activeSide = getOpposite(genes);
		DNAlength = genes.length;
	}
	
	public void setInactiveSide(int index, char value)
	{
		inactiveSide[index] = value;
		activeSide[index] = getOpposite(value);
	}
	
	public char getActiveSide(int index)
	{
		return activeSide[index];
	}
	
	public char getInactiveSide(int index)
	{
		return inactiveSide[index];
	}
	
	public void setGenes(char[][] genes)
	{
		for(int i = 0; i < genes.length; i++)
		{
			activeSide[i] = genes[i][0];
			inactiveSide[i] = genes[i][1];
		}
		DNAlength = genes.length;
	}
	
	public void setGenes(DNA genes)
	{
		activeSide = genes.getActiveSide();
		inactiveSide = genes.getInactiveSide();
		DNAlength = genes.getLength();
	}
	
	public static char getOpposite(char c)
	{
		if(c == 'A')
		{
			return 'T';
		}
		else if(c == 'C')
		{
			return 'G';
		}
		else if(c == 'T')
		{
			return 'A';
		}
		return 'C';
	}
	
	public static char[] getOpposite(char[] c)
	{
		char[] result = new char[c.length];
		for(int i = 0; i < c.length; i++)
		{
			result[i] = getOpposite(c[i]);
		}
		return result;
	}
}