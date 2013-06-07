package cell.util;

import cell.geneticcode.GeneticCode;

public interface IInfectable
{
	/**
	 * the percentage chance that the organism will fight off an infection
	 * @return
	 */
	public double getImmuneLevel();
	
	public void infect(IVirus virus);
	
	public GeneticCode getViralDNA();
	
	public void setViralDNA(GeneticCode newCode);
}