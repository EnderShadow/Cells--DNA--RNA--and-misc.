package cell.geneticcode;

public class RNA extends GeneticCode
{
	private char[] activeSide;
	
	private int RNAlength = 0;
	
	public RNA(int length, boolean autoGenerate)
	{
		length = (length / 3) * 3;
		RNAlength = length;
		if(autoGenerate)
		{
			GeneRandomizer gr = new GeneRandomizer(length);
			RNA temp = gr.randomizeGenesRNA();
			activeSide = temp.activeSide;
		}
		else
		{
			activeSide = new char[length];
		}
	}
	
	public RNA(int length)
	{
		this(length, false);
	}
	
	public int getLength()
	{
		return RNAlength;
	}
	
	public char[] getRNA()
	{
		return activeSide;
	}
	
	public void setRNA(char[] genes)
	{
		activeSide = genes;
	}
	
	public void setRNA(int index, char value)
	{
		activeSide[index] = value;
	}
	
	public char getRNA(int index)
	{
		return activeSide[index];
	}
	
	public void setRNA(RNA genes)
	{
		activeSide = genes.getRNA();
	}
}