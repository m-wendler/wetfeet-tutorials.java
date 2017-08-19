package wetfeet.rest.wink.api.rest.v1;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;


/**
 * Registers our resource explicitly. 
 * 
 * @author Mike Wendler
 */
public class WinkApplication 
	extends Application 
{

	/**
	 * Registers our resource.
	 * 
	 * @return the set of all RESTful resources.
	 */
	@Override
    public Set <Class <?> > getClasses () {
        Set <Class <?> > classes = new HashSet <Class <?> >();
        
        classes.add (Names.class);
        
        System.out.println ("\n\n\n * * *  added our resource  * * * \n\n\n");
        
        return classes;
    }

}
