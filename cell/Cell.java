package cell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import cell.geneticcode.Chromosome;
import cell.geneticcode.DNA;

public class Cell
{
	private String SAVE_LOCATION = Run.getSaveLocation(0) + "/";
	private int ID;
	private int numChromosomes;
	private int halfNumChromosomes;
	private Chromosome[] parent1;
	private Chromosome[] parent2;
	
	public Cell(int halfNumChromosomes, int id)
	{
		Run.cellID[id] = this;
		ID = id;
		this.halfNumChromosomes = halfNumChromosomes;
		numChromosomes = halfNumChromosomes * 2;
		parent1 = new Chromosome[halfNumChromosomes];
		parent2 = new Chromosome[halfNumChromosomes];
		for(int i = 0; i < halfNumChromosomes; i++)
		{
			parent1[i] = new Chromosome();
			parent2[i] = new Chromosome();
		}
		if(!getSaveData())
		{
			printData();
		}
	}
	
	public Cell(int halfNumChromosomes)
	{
		this(halfNumChromosomes, Run.getFreeCellID());
	}
	
	public Chromosome getChromosome1(int index)
	{
		return parent1[index];
	}
	
	public Chromosome getChromosome2(int index)
	{
		return parent2[index];
	}
	
	public Chromosome[] getChromosomes1()
	{
		return parent1;
	}
	
	public Chromosome[] getChromosomes2()
	{
		return parent2;
	}
	
	public Chromosome[][] getAllChromosomes()
	{
		Chromosome[][] result = new Chromosome[halfNumChromosomes][2];
		for(int i = 0; i < result.length; i++)
		{
			result[i][0] = parent1[i];
			result[i][1] = parent2[i];
		}
		return result;
	}
	
	public void setChromosome1(Chromosome c, int index)
	{
		parent1[index] = c;
	}
	
	public void setChromosome2(Chromosome c, int index)
	{
		parent2[index] = c;
	}
	
	public void setChromosomes1(Chromosome[] c)
	{
		parent1 = c;
	}
	
	public void setChromosomes2(Chromosome[] c)
	{
		parent2 = c;
	}
	
	public void setAllChromosomes(Chromosome[][] c)
	{
		for(int i = 0; i < halfNumChromosomes; i++)
		{
			parent1[i] = c[i][0];
			parent2[i] = c[i][1];
		}
	}
	
	public void setAllChromosomes(Cell cell)
	{
		setAllChromosomes(cell.getAllChromosomes());
	}
	
	public int getNumChromosomess()
	{
		return numChromosomes;
	}
	
	public int getHalfNumChromosomess()
	{
		return halfNumChromosomes;
	}
	
	private boolean getSaveData()
	{
		try
		{
			File file = new File(SAVE_LOCATION + ID + ".txt");
			String[] allDNA = new String[numChromosomes];
			BufferedReader br = new BufferedReader(new FileReader(file));
			for(int i = 0; i < numChromosomes; i++)
			{
				System.out.println("getting data from line " + (i + 1) + " of file " + (ID + 1));
				allDNA[i] = br.readLine();
			}
			br.close();
			
			for(int i = 0; i < halfNumChromosomes; i++)
			{
				DNA dna = new DNA(6000000);
				DNA dna2 = new DNA(6000000);
				dna.setActiveSide(allDNA[i].toCharArray());
				dna2.setActiveSide(allDNA[i + halfNumChromosomes].toCharArray());
				parent1[i].setDNA(dna);
				parent2[i].setDNA(dna2);
			}
			return true;
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
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
		    for(int i = 0; i < halfNumChromosomes; i++)
		    {
		    	out.write(String.valueOf(parent1[i].getDNA().getActiveSide()) + newLine);
		    }
		    for(int i = 0; i < halfNumChromosomes; i++)
		    {
		    	out.write(String.valueOf(parent2[i].getDNA().getActiveSide()) + newLine);
		    }
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
}