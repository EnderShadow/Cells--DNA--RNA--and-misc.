package cell.codereader;

public class CodonWheel
{
	public static String[] getProteinFromCodon(String[] codon, int codonStartIndex)
	{
		String[] aminoAcid = new String[500000];
		String[] aminoAcidFinal;
		String proteinFinal = "";
		int aminoAcidIndex = 0;
		int nextValue = 0;
		
		for(int i = codonStartIndex; i < codon.length; i++)
		{
			if(codon[i].equals("UAG") || codon[i].equals("UGA") || codon[i].equals("UAA"))
			{
				nextValue = i;
				i = codon.length + 1;
			}
			else
			{
				aminoAcid[aminoAcidIndex] = getAminoAcidName(codon[i]);
				aminoAcidIndex++;
			}
		}
		
		
		aminoAcidFinal = new String[aminoAcidIndex];
		
		for(int i = 0; i < aminoAcidFinal.length; i++)
		{
			aminoAcidFinal[i] = aminoAcid[i];
		}
		
		for(int i = 0; i < aminoAcidFinal.length - 1; i++)
		{
			aminoAcidFinal[i] = getCommonAminoAcidName(aminoAcid[i]);
		}
		
		aminoAcidFinal[aminoAcidFinal.length - 1] = aminoAcidFinal[aminoAcidFinal.length - 1].toLowerCase() + "e";
		aminoAcidFinal[0].toCharArray()[0] = 'M';
		
		for(int i = 0; i < aminoAcidFinal.length; i++)
		{
			proteinFinal = proteinFinal + aminoAcidFinal[i];
		}
		
		proteinFinal = proteinFinal.substring(0, proteinFinal.length() - 1);
		
		String[] result = {proteinFinal, Integer.toString(nextValue)};
		
		return result;
	}
	
	private static String getAminoAcidName(String codon)
	{
		String protein;
		if(codon.equals("GCA") || codon.equals("GCU") || codon.equals("GCC") || codon.equals("GCG"))
		{
			protein = "Alanine";
		}
		else if(codon.equals("CGA") || codon.equals("CGU") || codon.equals("CGC") || codon.equals("CGG") || codon.equals("AGA") || codon.equals("AGG"))
		{
			protein = "Arginine";
		}
		else if(codon.equals("AAU") || codon.equals("AAC"))
		{
			protein = "Asparagine";
		}
		else if(codon.equals("GAU") || codon.equals("GAC"))
		{
			protein = "Aspartate";
		}
		else if(codon.equals("UGU") || codon.equals("UGC"))
		{
			protein = "Cysteine";
		}
		else if(codon.equals("CAA") || codon.equals("CAG"))
		{
			protein = "Glutamine";
		}
		else if(codon.equals("GAA") || codon.equals("GAG"))
		{
			protein = "Glutamate";
		}
		else if(codon.equals("GGA") || codon.equals("GGC") || codon.equals("GGU") || codon.equals("GGG"))
		{
			protein = "Glycine";
		}
		else if(codon.equals("CAU") || codon.equals("CAC"))
		{
			protein = "Histidine";
		}
		else if(codon.equals("AUU") || codon.equals("AUC") || codon.equals("AUA"))
		{
			protein = "Isoleucine";
		}
		else if(codon.equals("AUG"))
		{
			protein = "Methionine";
		}
		else if(codon.equals("CUU") || codon.equals("CUA") || codon.equals("CUC") || codon.equals("CUG") || codon.equals("UUA") || codon.equals("UUG"))
		{
			protein = "Leucine";
		}
		else if(codon.equals("AAA") || codon.equals("AAG"))
		{
			protein = "Lysine";
		}
		else if(codon.equals("UUU") || codon.equals("UUC"))
		{
			protein = "Phenylalanine";
		}
		else if(codon.equals("CCA") || codon.equals("CCU") || codon.equals("CCC") || codon.equals("CCG"))
		{
			protein = "Proline";
		}
		else if(codon.equals("UCA") || codon.equals("UCU") || codon.equals("UCC") || codon.equals("UCG") || codon.equals("AGU") || codon.equals("AGC"))
		{
			protein = "Serine";
		}
		else if(codon.equals("ACA") || codon.equals("ACU") || codon.equals("ACC") || codon.equals("ACG"))
		{
			protein = "Threonine";
		}
		else if(codon.equals("UGG"))
		{
			protein = "Tryptophan";
		}
		else if(codon.equals("UAU") || codon.equals("UAC"))
		{
			protein = "Tyrosine";
		}
		else if(codon.equals("GUA") || codon.equals("GUU") || codon.equals("GUC") || codon.equals("GUG"))
		{
			protein = "Valine";
		}
		else
		{
			protein = "";
			System.err.println("Invalid codon");
		}
		
		return protein;
	}
	
	private static String getCommonAminoAcidName(String name)
	{
		String commonName;
		if(name.equals("Alanine"))
		{
			commonName = "alanyl";
		}
		else if(name.equals("Arginine"))
		{
			commonName = "arginyl";
		}
		else if(name.equals("Asparagine"))
		{
			commonName = "asparaginyl";
		}
		else if(name.equals("Aspartate"))
		{
			commonName = "aspartyl";
		}
		else if(name.equals("Cysteine"))
		{
			commonName = "cysteinyl";
		}
		else if(name.equals("Glutamine"))
		{
			commonName = "glutaminyl";
		}
		else if(name.equals("Glutamate"))
		{
			commonName = "glutamyl";
		}
		else if(name.equals("Glycine"))
		{
			commonName = "glycyl";
		}
		else if(name.equals("Histidine"))
		{
			commonName = "histidyl";
		}
		else if(name.equals("Isoleucine"))
		{
			commonName = "isoleucyl";
		}
		else if(name.equals("Methionine"))
		{
			commonName = "methionyl";
		}
		else if(name.equals("Leucine"))
		{
			commonName = "leucyl";
		}
		else if(name.equals("Lysine"))
		{
			commonName = "lysyl";
		}
		else if(name.equals("Phenylalanine"))
		{
			commonName = "phenyl";
		}
		else if(name.equals("Proline"))
		{
			commonName = "prolyl";
		}
		else if(name.equals("Serine"))
		{
			commonName = "seryl";
		}
		else if(name.equals("Threonine"))
		{
			commonName = "threonyl";
		}
		else if(name.equals("Tryptophan"))
		{
			commonName = "tryptophyl";
		}
		else if(name.equals("Tyrosine"))
		{
			commonName = "tyrosyl";
		}
		else if(name.equals("Valine"))
		{
			commonName = "valyl";
		}
		else
		{
			commonName = "";
			System.err.println("Invalid codon");
		}
		
		return commonName;
	}
}