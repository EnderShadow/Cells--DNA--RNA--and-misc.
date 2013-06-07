package cell.viruses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import cell.Run;
import cell.geneticcode.DNA;
import cell.util.IDNA;
import cell.util.IVirus;

public class VirusB implements IDNA, IVirus
{
	private String SAVE_LOCATION = Run.getSaveLocation(2) + "/";
	private int ID;
	private DNA dna;
	
	public VirusB(int id)
	{
		Run.virusID[id] = this;
		ID = id;
		dna = new DNA(60000, true);
		if(!getSaveData())
		{
			printData();
		}
	}
	
	public VirusB()
	{
		this(Run.getFreeVirusID());
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
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
	
	@Override
	public char[] getVirusData()
	{
		return dna.getActiveSide();
	}
}