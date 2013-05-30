package cell;

import java.io.*;

import cell.codereader.GeneticsDecoder;
import cell.geneticcode.DNA;

public class Run
{
	public static Cell[] cellID = new Cell[4096];
	
	public static void main(String[] args)
	{
		File f = new File("existingCells");
		if(f.mkdir())
		{
			System.out.println("save directory created");
		}
		else
		{
			System.out.println("save directory already exists or could not be created");
		}
		//DNA dna = new DNA(10000, true);
		//GeneticsDecoder.printProteins("proteins", GeneticsDecoder.getProteins(GeneticsDecoder.getCodons(GeneticsDecoder.getmRNA(dna))));
		/*Cell cell0 = new Cell(1);
		printProteins("protein1", cell0.getChromosome1(0).getDNA());
		printProteins("protein2", cell0.getChromosome1(1).getDNA());
		printProteins("protein3", cell0.getChromosome2(0).getDNA());
		printProteins("protein4", cell0.getChromosome2(1).getDNA());*/
		Cell cell1 = new Cell(2);
		Cell cell2 = new Cell(2);
		Cell cell3 = new Cell(2);
		Cell cell4 = new Cell(2);
	}
	
	private static void printProteins(String file, DNA dna)
	{
		GeneticsDecoder.printProteins(file, GeneticsDecoder.getProteins(GeneticsDecoder.getCodons(GeneticsDecoder.getmRNA(dna))));
	}
	
	public static DNA getDNAFromFile(String FILE)
	{
		DNA finalDNA = new DNA(300000);
		String DNA1 = "";
		char[] dna1 = new char[300000];
		
		File file = new File(FILE);
		try
		{
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(raf.length() - 6000000);
		DNA1 = raf.readLine();
		raf.close();
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		
		dna1 = DNA1.toCharArray();
		
		finalDNA.setActiveSide(dna1);
		
		return finalDNA;
	}
	
	public static int getID(Cell c)
	{
		for(int i = 0; i < cellID.length; i++)
		{
			if(cellID[i] == null)
			{
				cellID[i] = c;
				return i;
			}
		}
		return -1;
	}
	
	public static String getAge(int age)
	{
		int years = age / 360;
		int days = age % 360;
		return years + "." + days;
	}
}