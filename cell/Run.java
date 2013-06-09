package cell;

import java.io.*;
import java.util.Scanner;

import cell.cells.Bacteria;
import cell.cells.Cell;
import cell.cells.RNABacteria;
import cell.codereader.GeneticsDecoder;
import cell.geneticcode.DNA;
import cell.geneticcode.RNA;
import cell.util.IBacteria;
import cell.util.IVirus;
import cell.viruses.VirusA;
import cell.viruses.VirusB;

public class Run
{
	public static Cell[][] cellID = new Cell[512][32];
	public static IBacteria[][] bacteriaID = new IBacteria[2048][32];
	public static IVirus[] virusID = new IVirus[8192];
	
	public static void main(String[] args)
	{
		//resetSaves();
		
		makeDir("saves");
		makeDir(getSaveLocation(0));
		makeDir(getSaveLocation(1));
		makeDir(getSaveLocation(2));
		
		Scanner scan = new Scanner(System.in);
		System.out.println("how many cells?");
		int numCells = scan.nextInt();
		System.out.println("how many chromosomes total per cell?");
		int halfChrome = scan.nextInt() / 2;
		for(int i = 0; i < numCells; i++)
		{
			Cell c = new Cell(halfChrome);
		}
		System.out.println("how many DNA bacteria?");
		int j = scan.nextInt();
		for(int i = 0; i < j; i++)
		{
			Bacteria b = new Bacteria();
		}
		System.out.println("how many RNA bacteria?");
		j = scan.nextInt();
		for(int i = 0; i < j; i++)
		{
			RNABacteria b = new RNABacteria();
		}
		System.out.println("how many DNA viruses?");
		j = scan.nextInt();
		for(int i = 0; i < j; i++)
		{
			VirusB v = new VirusB();
		}
		System.out.println("how many RNA viruses?");
		j = scan.nextInt();
		for(int i = 0; i < j; i++)
		{
			VirusA v = new VirusA();
		}
		scan.close();
		
		//Cell cell = new Cell(4);
		//char[] data = "HELLO! THIS IS A TeSt".toCharArray();
		//VirusB virus = new VirusB(data);
		//cell.infect(virus);
		//DNA dna = new DNA(10000, true);
		//GeneticsDecoder.printProteins("proteins", GeneticsDecoder.getProteins(GeneticsDecoder.getCodons(GeneticsDecoder.getmRNA(dna))));
		/*Cell cell0 = new Cell(1);
		printProteins("protein1", cell0.getChromosome1(0).getDNA());
		printProteins("protein2", cell0.getChromosome1(1).getDNA());
		printProteins("protein3", cell0.getChromosome2(0).getDNA());
		printProteins("protein4", cell0.getChromosome2(1).getDNA());*/
		/*for(int i = 0; i < 10; i++)
		{
			Cell cell = new Cell(4);
			for(int j = 0; j < 2; j++)
			{
				Bacteria b = new Bacteria();
				for(int k = 0; k < 2; k++)
				{
					Virus v = new Virus();
				}
			}
		}*/
	}
	
	public static void makeDir(String location)
	{
		File f = new File(location);
		if(f.mkdir())
		{
			System.out.println("save directory created");
		}
		else if(f.exists())
		{
			System.out.println("save directory already exists");
		}
		else
		{
			System.err.println("ERROR: Unable to create directory");
		}
	}
	
	@SuppressWarnings("unused")
	private static void printProteins(String file, DNA dna)
	{
		GeneticsDecoder.printProteins(file, GeneticsDecoder.getProteins(GeneticsDecoder.getCodons(GeneticsDecoder.getmRNA(dna))));
	}
	
	@SuppressWarnings("unused")
	private static void printProteins(String file, RNA rna)
	{
		GeneticsDecoder.printProteins(file, GeneticsDecoder.getProteins(GeneticsDecoder.getCodons(rna)));
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
	
	public static int getFreeCellID()
	{
		for(int i = 0; i < cellID.length; i++)
		{
			if(cellID[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int getFreeBacteriaID()
	{
		for(int i = 0; i < bacteriaID.length; i++)
		{
			if(bacteriaID[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int getFreeVirusID()
	{
		for(int i = 0; i < virusID.length; i++)
		{
			if(virusID[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 
	 * @param type 0 = cells, 1 = bacteria, 2 = viruses
	 * @return save location
	 */
	public static String getSaveLocation(int type)
	{
		String result = "";
		switch(type)
		{
		case 0:
			result = "saves/Cells";
			break;
		case 1:
			result = "saves/Bacteria";
			break;
		case 2:
			result = "saves/Viruses";
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private static void resetSaves()
	{
		File f = new File("saves");
		delete(f);
	}
	
	public static void delete(File file)
	{
		if(file.isDirectory())
		{
			if(file.list().length==0)
			{
				file.delete();
			}
			else
			{
				String files[] = file.list();
				for (String temp : files)
				{
					File fileDelete = new File(file, temp);
					delete(fileDelete);
				}
				if(file.list().length==0)
				{
					file.delete();
				}
			}
		}
		else
		{
			file.delete();
		}
	}
	
	public static String getAge(int age)
	{
		int years = age / 360;
		int days = age % 360;
		return years + "." + days;
	}
}