package cell.geneticcode;

import java.util.Random;


public class GeneRandomizer
{
	private int length;
	
	public GeneRandomizer(int length)
	{
		this.length = length;
	}
	
	public DNA randomizeGenesDNA(DNA dna1, DNA dna2)
	{
		DNA childDNA = new DNA(length);
		Random rand = new Random();
		
		if(dna1 != null && dna2 != null)
		{
			char[][] activeGenesBoth = new char[length][2];
			for(int i = 0; i < length; i++)
			{
				activeGenesBoth[i][0] = dna1.getActiveSide()[i];
				activeGenesBoth[i][1] = dna2.getActiveSide()[i];
			}
			char[] newActiveGenes = new char[length];
			for(int i = 0; i < length; i++)
			{
				newActiveGenes[i] = activeGenesBoth[i][rand.nextInt(2)];
			}
			childDNA.setActiveSide(newActiveGenes);
		}
		else
		{
			childDNA = randomizeGenesDNA(rand);
		}
		return childDNA;
	}
	
	public DNA randomizeGenesDNA(Random rand)
	{
		DNA childGenesDNA = new DNA(length);
		for(int j = 0; j < length; j++)
		{
			int temp = rand.nextInt(4);
			switch(temp)
			{
			case 0:
				childGenesDNA.setActiveSide(j, 'A');
				break;
			case 1:
				childGenesDNA.setActiveSide(j, 'C');
				break;
			case 2:
				childGenesDNA.setActiveSide(j, 'T');
				break;
			case 3:
				childGenesDNA.setActiveSide(j, 'G');
				break;
			default:
				System.err.println("invalid number");
			}
		}
		return childGenesDNA;
	}
	
	public DNA randomizeGenesDNA()
	{
		DNA childGenesDNA = new DNA(length);
		Random rand = new Random();
		for(int j = 0; j < length; j++)
		{
			int temp = rand.nextInt(4);
			switch(temp)
			{
			case 0:
				childGenesDNA.setActiveSide(j, 'A');
				break;
			case 1:
				childGenesDNA.setActiveSide(j, 'C');
				break;
			case 2:
				childGenesDNA.setActiveSide(j, 'T');
				break;
			case 3:
				childGenesDNA.setActiveSide(j, 'G');
				break;
			default:
				System.err.println("invalid number");
			}
		}
		return childGenesDNA;
	}
	
	public RNA randomizeGenesRNA()
	{
		RNA childGenesRNA = new RNA(length);
		Random rand = new Random();
		for(int j = 0; j < length; j++)
		{
			int temp = rand.nextInt(4);
			switch(temp)
			{
			case 0:
				childGenesRNA.setRNA(j, 'A');
				break;
			case 1:
				childGenesRNA.setRNA(j, 'C');
				break;
			case 2:
				childGenesRNA.setRNA(j, 'U');
				break;
			case 3:
				childGenesRNA.setRNA(j, 'G');
				break;
			default:
				System.err.println("invalid number");
			}
		}
		return childGenesRNA;
	}
	
	public String randomizeGender()
	{
		Random rand = new Random();
		if(rand.nextInt(2) == 0)
		{
			return "female";
		}
		else
		{
			return "male";
		}
	}
}