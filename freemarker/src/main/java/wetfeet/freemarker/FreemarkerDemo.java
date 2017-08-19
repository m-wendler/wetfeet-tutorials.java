package wetfeet.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * Demonstrates the use of freemarker templates. 
 * 
 * @author Mike Wendler
 */
public class FreemarkerDemo {

	/**
	 * Loads a freemarker template and instantiates it with some data.
	 * 
	 * @param args command line arguments (no-op)
	 * @throws TemplateException if instantiating the template didn't work
	 * @throws IOException if the template could not be loaded
	 */
	public static void main (final String[] args) 
		throws TemplateException, IOException 
	{
		System.out.println ("******************************* wetfeet.freemarker STARTED ... *************************************\n");
		
		
		System.out.println ("load freemarker template ...");
		Configuration config = new Configuration ();
		config.setDirectoryForTemplateLoading (new File ("src/main/resources") );
		Template template = config.getTemplate ("template.ftl");
		
		
		System.out.println ("create some model data which is to be merged with the template ...");
		Map <String, String> data = new HashMap <String, String> ();
		data.put ("userName", "Mike");
		data.put ("today", new Date ().toString () );

		
		System.out.println ("merge template with model data and flush it ...\n");
		Writer writer = new OutputStreamWriter (System.out);
		template.process (data, writer);
		writer.flush ();
		
		System.out.println ("\n\n******************************* wetfeet.freemarker DONE ... *************************************");
	}

}
