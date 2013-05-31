package cell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import cell.geneticcode.RNA;
import cell.util.IRNA;

public class Virus implements IRNA
{
	private String SAVE_LOCATION = Run.getSaveLocation(2) + "/";
	private int ID;
	private RNA rna;
	
	public Virus(int id)
	{
		Run.virusID[id] = this;
		ID = id;
		rna = new RNA(30000, true);
		if(!getSaveData())
		{
			printData();
		}
	}
	
	public Virus()
	{
		this(Run.getFreeVirusID());
	}
	
	@Override
	public RNA getRNA()
	{
		return rna;
	}
	
	@Override
	public void setDNA(RNA newRNA)
	{
		rna = newRNA;
	}
	
	private boolean getSaveData()
	{
		try
		{
			File file = new File(SAVE_LOCATION + ID + ".txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			System.out.println("getting data from line " + 1 + " of file " + (ID + 1));
			rna.setRNA(br.readLine().toCharArray());
			br.close();
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
		    out.write(String.valueOf(rna.getRNA()) + newLine);
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
}