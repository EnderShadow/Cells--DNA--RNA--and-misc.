package cell.cells;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import cell.Run;
import cell.geneticcode.GeneticCode;
import cell.geneticcode.RNA;
import cell.util.IBacteria;
import cell.util.IDNA;
import cell.util.IInfectable;
import cell.util.IRNA;
import cell.util.IVirus;

public class RNABacteria implements IRNA, IInfectable, IBacteria
{
	private String SAVE_LOCATION = Run.getSaveLocation(1) + "/";
	private int ID;
	private RNA rna;
	private RNA viralRNA = new RNA(0);
	
	public RNABacteria(int id)
	{
		Run.bacteriaID[id][0] = this;
		ID = id;
		rna = new RNA(300000, true);
		if(!getSaveData())
		{
			printData();
		}
	}
	
	public RNABacteria()
	{
		this(Run.getFreeBacteriaID());
	}
	
	@Override
	public RNA getRNA()
	{
		return rna;
	}
	
	@Override
	public void setRNA(RNA newRNA)
	{
		rna = newRNA;
		printData();
	}
	
	private boolean getSaveData()
	{
		try
		{
			File file = new File(SAVE_LOCATION + ID + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			System.out.println("getting data from line " + 1 + " of file " + (ID + 1));
			rna.setRNA(br.readLine().toCharArray());
			viralRNA.setRNA(br.readLine().toCharArray());
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
		    out.write(String.valueOf(rna.getRNA()) + newLine);
		    out.write(String.valueOf(viralRNA.getRNA()) + newLine);
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
		return 5.0D;
	}
	
	@Override
	public void infect(IVirus virus)
	{
		Random rand = new Random();
		if(rand.nextDouble() > getImmuneLevel() / 100)
		{
			if(virus instanceof IRNA)
			{
				char[] infection = virus.getVirusData();
				RNA oldViralRNA = viralRNA;
				viralRNA = new RNA(oldViralRNA.getLength() + infection.length);
				for(int i = 0; i < oldViralRNA.getLength(); i++)
				{
					viralRNA.setRNA(i, oldViralRNA.getRNA(i));
				}
				for(int i = oldViralRNA.getLength(); i < viralRNA.getLength(); i++)
				{
					viralRNA.setRNA(i, infection[i - oldViralRNA.getLength()]);
				}
			}
			else if(virus instanceof IDNA)
			{
				Bacteria b = new Bacteria(ID);
				b.setDNA(((IDNA) virus).getDNA());
				ID = -1;
				return;
			}
			printData();
		}
	}
	
	@Override
	public RNA getViralDNA()
	{
		return viralRNA;
	}
	
	@Override
	public void setViralDNA(GeneticCode newRNA)
	{
		viralRNA = (RNA)newRNA;
		printData();
	}
	
	public boolean replicate()
	{
		for(int i = 0; i < Run.bacteriaID[ID].length; i++)
		{
			if(Run.bacteriaID[ID][i] == null)
			{
				try
				{
					Run.bacteriaID[ID][i] = (Bacteria)this.clone();
				}
				catch(CloneNotSupportedException e)
				{
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		return false;
	}
}