package cell.codereader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import cell.geneticcode.DNA;
import cell.geneticcode.RNA;


public class GeneticsDecoder
{
	@Deprecated
	public static String getDNA(DNA dnaSource)
	{
		return String.valueOf(dnaSource.getActiveSide());
	}
	
	public static String getmRNA(DNA dnaSource)
	{
		char[] dna = dnaSource.getActiveSide();
		char[] mrna = new char[dnaSource.getLength()];
		String mRNA = "";
		
		for(int i = 0; i < dna.length; i++)
		{
			if(dna[i] == 'A')
			{
				mrna[i] = 'U';
			}
			else if(dna[i] == 'C')
			{
				mrna[i] = 'G';
			}
			else if(dna[i] == 'T')
			{
				mrna[i] = 'A';
			}
			else
			{
				mrna[i] = 'C';
			}
		}
		
		mRNA = String.valueOf(mrna);
		return mRNA;
	}
	
	public static String getmRNA(String FILE)
	{
		String mRNA = "";
		char[] mrna;
		String DNA = "";
		//read file
		//36400 lines
		
		File file = new File(FILE);
		try
		{
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(raf.length() - 600000);
			DNA = raf.readLine();
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
		
		char[] dna = DNA.toCharArray();
		mrna = new char[dna.length];
		
		for(int i = 0; i < dna.length; i++)
		{
			if(dna[i] == 'A')
			{
				mrna[i] = 'U';
			}
			else if(dna[i] == 'C')
			{
				mrna[i] = 'G';
			}
			else if(dna[i] == 'T')
			{
				mrna[i] = 'A';
			}
			else
			{
				mrna[i] = 'C';
			}
		}
		
		mRNA = String.valueOf(mrna);
		
		return mRNA;
	}
	
	public static void printmRNA(String outputFile, String mRNA)
	{
		try
		{
		    FileWriter fstream = new FileWriter(outputFile, true); //true tells to append data.
		    BufferedWriter out = new BufferedWriter(fstream);
		    out.write(mRNA);
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static String[] getCodons(RNA rna)
	{
		String mRNA = String.valueOf(rna.getRNA());
		
		if(mRNA.contains("T"))
		{
			System.err.println("INVALID GENETIC CODE TYPE");
			return null;
		}
		
		String[] codons = new String[mRNA.length() / 3];
		
		for(int i = 0; i < mRNA.length() / 3; i++)
		{
			codons[i] = mRNA.substring(i, i + 3);
		}
		
		return codons;
	}
	
	public static String[] getCodons(String mRNA)
	{
		if(mRNA.contains("T"))
		{
			System.err.println("INVALID GENETIC CODE TYPE");
			return null;
		}
		
		String[] codons = new String[mRNA.length() / 3];
		
		for(int i = 0; i < mRNA.length() / 3; i++)
		{
			codons[i] = mRNA.substring(i, i + 3);
		}
		
		return codons;
	}
	
	public static String[] getProteins(String[] codons)
	{
		String[] proteins = new String[codons.length / 2];
		String[] proteinsFinal;
		
		int location = 0;
		
		for(int i = 0; i < codons.length; i++)
		{
			if(codons[i].equals("AUG"))
			{
				String[] string = CodonWheel.getProteinFromCodon(codons, i);
				proteins[location] = string[0];
				location++;
				i = Integer.valueOf(string[1]);
			}
		}
		
		proteinsFinal = new String[location];
		
		for(int i = 0; i < proteinsFinal.length; i++)
		{
			proteinsFinal[i] = proteins[i];
		}
		
		return proteinsFinal;
	}
	
	public static void printGetmRNA(String outputFile, String sourceFile)
	{
		printmRNA(outputFile, getmRNA(sourceFile));
	}
	
	public static void printCodons(String outputFile, String[] codonArray)
	{
		try
		{
		    FileWriter fstream = new FileWriter(outputFile, true); //true tells to append data.
		    BufferedWriter out = new BufferedWriter(fstream);
		    for(int i = 0; i < codonArray.length; i++)
		    {
		    	out.write(codonArray[i] + " ");
		    }
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void printProteins(String outputFile, String[] proteinArray)
	{
		String newLine = System.getProperty("line.separator");
		try
		{
		    FileWriter fstream = new FileWriter(outputFile + ".txt", true); //true tells to append data.
		    BufferedWriter out = new BufferedWriter(fstream);
		    for(int i = 0; i < proteinArray.length; i++)
		    {
		    	out.write(proteinArray[i] + newLine);
		    }
		    out.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
	}
}