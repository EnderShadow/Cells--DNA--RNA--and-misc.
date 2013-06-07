package cell.cells;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import cell.Run;
import cell.geneticcode.DNA;
import cell.geneticcode.GeneticCode;
import cell.util.IBacteria;
import cell.util.IDNA;
import cell.util.IInfectable;
import cell.util.IRNA;
import cell.util.IVirus;

public class Bacteria implements IDNA, IInfectable, IBacteria
{
	private String SAVE_LOCATION = Run.getSaveLocation(1) + "/";
	private int ID;
	private DNA dna;
	private DNA viralDNA = new DNA(0);
	
	public Bacteria(int id)
	{
		Run.bacteriaID[id] = this;
		ID = id;
		dna = new DNA(900000, true);
		if(!getSaveData())
		{
			printData();
		}
	}
	
	public Bacteria()
	{
		this(Run.getFreeBacteriaID());
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
		printData();
	}
	
	private boolean getSaveData()
	{
		try
		{
			File file = new File(SAVE_LOCATION + ID + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			System.out.println("getting data from line " + 1 + " of file " + (ID + 1));
			dna.setActiveSide(br.readLine().toCharArray());
			viralDNA.setActiveSide(br.readLine().toCharArray());
			br.close();
			return true;
		}
		catch(Exception e)
		{
			//System.err.println(e.getMessage());
		}
		return false;
	}
	
	private void printData()
	{
		String newLine = System.getProperty("line.separator");
		try
		{
		    FileWriter fstream = new FileWriter(SAVE_LOCATION + ID + ".txt", false); //true tells to append data.
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write(String.valueOf(dna.getActiveSide()) + newLine);
		    out.write(String.valueOf(viralDNA.getActiveSide()) + newLine);
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
	
	@Override
	public double getImmuneLevel()
	{
		return 10.0D;
	}
	
	@Override
	public void infect(IVirus virus)
	{
		Random rand = new Random();
		if(rand.nextDouble() > getImmuneLevel() / 100)
		{
			if(virus instanceof IRNA)
			{
				
			}
			else if(virus instanceof IDNA)
			{
				char[] infection = virus.getVirusData();
				DNA oldViralDNA = viralDNA;
				viralDNA = new DNA(oldViralDNA.getLength() + infection.length);
				for(int i = 0; i < oldViralDNA.getLength(); i++)
				{
					viralDNA.setActiveSide(i, oldViralDNA.getActiveSide(i));
				}
				for(int i = oldViralDNA.getLength(); i < viralDNA.getLength(); i++)
				{
					viralDNA.setActiveSide(i, infection[i - oldViralDNA.getLength()]);
				}
			}
			printData();
		}
	}
	
	@Override
	public DNA getViralDNA()
	{
		return viralDNA;
	}
	
	@Override
	public void setViralDNA(GeneticCode newDNA)
	{
		viralDNA = (DNA)newDNA;
		printData();
	}
}