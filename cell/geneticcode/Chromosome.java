package cell.geneticcode;

import cell.util.IDNA;

public class Chromosome implements IDNA
{
	private DNA dna;
	
	public Chromosome()
	{
		dna = new DNA(6000000, true);
	}
	
	@Override
	public DNA getDNA()
	{
		return dna;
	}
	
	@Override
	public void setDNA(DNA newDNA)
	{
		dna = newDNA;
	}
}