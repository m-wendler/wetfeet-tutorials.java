package wetfeet.freemarker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Tests instantiating a freemarker template.
 * 
 * @author Mike Wendler
 */
public class FreemarkerTest {

	/** the map for the data that is to merged into the template */
	private Map <String, String> modelData = new HashMap <String, String> ();

	/** a writer to put the instantiated template text into */
	private Writer writer = new StringWriter ();
	
	/**
	 * Tests instantiating a template, the positive case.
	 * 
	 * @throws TemplateException if instantiating the template didn't work
	 * @throws IOException if the template could not be loaded
	 */
	@Test
	public void testTemplateInstantiation () 
		throws IOException, TemplateException 
	{
		Template template = getTemplate (); 
		assertNotNull (template);
		
		modelData.put ("message", "hello Freemarker!");		

		template.process (modelData, writer);		
		assertEquals ("<text>hello Freemarker!</text>", writer.toString () );
	}

	/**
	 * Tests instantiating a template, the negative case. 
	 * I add the model data with a wrong key in order to provoke 
	 * an exception.
	 * 
	 * @throws TemplateException if instantiating the template didn't work
	 * @throws IOException if the template could not be loaded
	 */
	@Test (expected=TemplateException.class)
	public void testInstantiationException () 
		throws IOException, TemplateException 
	{
		Template template = getTemplate (); 		
		assertNotNull (template);
		
		// add a value for a key that isn't used in the template
		modelData.put ("wrongKeyName", "hello Freemarker!");
		
		// will throw an exception since the variable in the template can not be resolved. 
		template.process (modelData, writer);
	}
	
	/**
	 * @return the loaded template.
	 * @throws IOException if the template location does not exist or the desired template 
	 *   could not be found there
	 */
	private Template getTemplate () 
		throws IOException 
	{
		Configuration config = new Configuration ();
		config.setDirectoryForTemplateLoading (new File ("src/test/resources") );
		return config.getTemplate ("test.ftl");
	}

}
