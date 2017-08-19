package wetfeet.javacsv;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

/**
 * Tests writing and reading some comma-separated values.
 * 
 * @author Mike Wendler
 */
public class JavaCSVtest {


	/**
	 * Tests writing some comma separated values.
	 * 
	 * @throws IOException if the writing of the values failed
	 */
	@Test
	public void writeCSValues () 
		throws IOException 
	{
		StringWriter stringWriter = new StringWriter ();
		CsvWriter csvWriter       = new CsvWriter (stringWriter, ';');
		
		csvWriter.write ("one");
		csvWriter.write ("two");

		assertEquals ("one;two", stringWriter.toString () );		
	}

	/**
	 * Tests reading some comma separated values.
	 * 
	 * @throws IOException if the reading of the values failed
	 */
	@Test
	public void readCSValues () 
		throws IOException 
	{
		StringReader stringReader = new StringReader ("one;two;three");
		CsvReader csvReader       = new CsvReader (stringReader, ';');
		
		csvReader.readRecord ();
		String [] csValues = csvReader.getValues ();
		assertEquals ("one",   csValues [0]);
		assertEquals ("two",   csValues [1]);
		assertEquals ("three", csValues [2]);
	}
	
}
