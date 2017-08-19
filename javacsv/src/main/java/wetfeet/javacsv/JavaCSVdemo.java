package wetfeet.javacsv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;


/**
 * Demonstrates the use of JavaCSV for writing and reading comma-separated values. 
 * 
 * @author Mike Wendler
 */
public class JavaCSVdemo {

	/**
	 * Writes and reads comma separated values.
	 * 
	 * @param args command line arguments (no-op)
	 * @throws IOException if the csv data could not be written or read
	 */
	public static void main (final String[] args) 
		throws IOException 
	{
		System.out.println ("******************************* wetfeet.JavaCSV STARTED ... *************************************\n");
		
		
		System.out.println ("1. write csv values ... ");
		
		// create writer objects for generating csv values
		StringWriter writer = new StringWriter ();		
		CsvWriter csvWriter = new CsvWriter (writer, ';');
		
		csvWriter.writeRecord (new String [] { "productID", "count", "price" } );
		csvWriter.writeRecord (new String [] { "1234", "2", "3.99" } );
		csvWriter.endRecord ();
		csvWriter.flush ();
		
		// flush the values written to the writer
		String csvValues = writer.toString ();
		System.out.println (csvValues);
		
		// clean up writers
		csvWriter.close ();
		writer.close ();
		
		
		System.out.println ("2. parse csv values from a file ... ");
		FileInputStream fileInputStream = new FileInputStream (new File ("src/main/resources/CSVdemo.csv") );
		InputStreamReader fileReader    = new InputStreamReader (fileInputStream);
		CsvReader csvReader             = new CsvReader (fileReader, ';');
		boolean isDataAvailable         = true;
		
		while (isDataAvailable) {
			isDataAvailable = csvReader.readRecord ();
			
			if (isDataAvailable) {
				String [] line = csvReader.getValues ();
				
				for (int i = 0; i < line.length; i++) {
					System.out.print (String.format ("%s ... ", line [i]) ); 
				}
				
				System.out.println ();
			}
		}

		
		// clean up readers
		csvReader.close ();
		fileReader.close ();
		fileInputStream.close ();

		
		System.out.println ("\n\n******************************* wetfeet.JavaCSV DONE ... *************************************");
	}

}
