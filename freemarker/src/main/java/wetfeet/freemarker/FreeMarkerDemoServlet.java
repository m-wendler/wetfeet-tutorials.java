package wetfeet.freemarker;

import java.io.PrintWriter;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A mini servlet for trying out the web.xml-less servlet configuration.
 */
@WebServlet (urlPatterns = {"/FreeMarkerDemoServlet"} )
@SuppressWarnings("serial")
public class FreeMarkerDemoServlet 
	extends HttpServlet 
{
		
	public static final String CONTENT_TYPE_JSON = "application/json";

	private Configuration cfg;
	
	public void doGet (final HttpServletRequest pRequest, final HttpServletResponse pResponse) 
		throws ServletException 
	{
		processRequest (pRequest, pResponse);		
	}

	public void doPost (final HttpServletRequest pRequest, final HttpServletResponse pResponse) 
		throws ServletException
	{
		processRequest (pRequest, pResponse);		
	}

	public void init() {
		// Initialize the FreeMarker configuration;
		// - Create a configuration instance
//		cfg = new Configuration();
//		// - Templates are stoted in the WEB-INF/templates directory of the Web app.
//		cfg.setServletContextForTemplateLoading (getServletContext(), "WEB-INF/templates");
		
		// In a real-world application various other settings should be explicitly
		// set here, but for the sake of brevity we leave it out now. See the
		// "webapp2" example for them.
	}
	
	public void processRequest (final HttpServletRequest pRequest, final HttpServletResponse pResponse)
		throws ServletException 
	{
		PrintWriter out = null;
//		try {
//			Template t = cfg.getTemplate ("test.ftl");
//			pResponse.setContentType ("text/html; charset=" + t.getEncoding());
//			out = pResponse.getWriter ();
//
//			Map <String, String> root = new HashMap <String, String> ();
//			root.put("message", "Hello servlet template world, Mike");
//			
//			try {
//				t.process(root, out);
//			} catch (TemplateException e) {
//				// TODO Auto-generated catch block
//				throw new ServletException("templating error ...", e);
//			}
//			
//			out.flush ();			
//		} catch (IOException e) {
////			getContext ().log ("exception while trying to serve back the result", 
////					           e);
//		} finally {
//			out.close ();
//		}
	}

	
}




/*
 public class HelloServlet extends HttpServlet {
16.private Configuration cfg;
17. 
18.public void init() {
19.// Initialize the FreeMarker configuration;
20.// - Create a configuration instance
21.cfg = new Configuration();
22.// - Templates are stoted in the WEB-INF/templates directory of the Web app.
23.cfg.setServletContextForTemplateLoading(
24.getServletContext(), "WEB-INF/templates");
25.// In a real-world application various other settings should be explicitly
26.// set here, but for the sake of brevity we leave it out now. See the
27.// "webapp2" example for them.
28.}
29. 
30.protected void doGet(HttpServletRequest req, HttpServletResponse resp)
31.throws ServletException, IOException {
32. 
33.// Build the data-model
34.Map root = new HashMap();
35.root.put("message", "Hello World!");
36. 
37.// Get the templat object
38.Template t = cfg.getTemplate("test.ftl");
39. 
40.// Prepare the HTTP response:
41.// - Use the charset of template for the output
42.// - Use text/html MIME-type
43.resp.setContentType("text/html; charset=" + t.getEncoding());
44.Writer out = resp.getWriter();
45. 
46.// Merge the data-model and the template
47.try {
48.t.process(root, out);
49.} catch (TemplateException e) {
50.throw new ServletException(
51."Error while processing FreeMarker template", e);
52.}
53.}
54.} 
 */
