package cell.geneticcode;

public class Chromosome
{
	private DNA dna;
	
	public Chromosome()
	{
		dna = new DNA(6000000, true);
	}
	
	public DNA getDNA()
	{
		return dna;
	}
	
	public void setDNA(DNA newDNA)
	{
		dna = newDNA;
	}
}