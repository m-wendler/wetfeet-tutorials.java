package wetfeet.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;

/**
 * Demonstrates the use of logback. 
 * 
 * @author Mike Wendler
 */
public class LogbackDemo {

	/**
	 * Instantiates a logger object and logs a few messages using MDC.
	 * We configure the layout forth and back and have a closer look at 
	 * the logging context.
	 * 
	 * @param args command line arguments
	 */
	public static void main (final String[] args) {
		System.out.println ("******************************* wetfeet.logback STARTED ... *************************************\n");
		// get a logger object (this is plain slf4j)
		Logger logger = LoggerFactory.getLogger (LogbackDemo.class);

		// let's get a logger context too for later use
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

		// we can have a closer look at the context now ...
		StatusPrinter.print (loggerContext);

		// or we add a listener to the status manager which informs us about any changes in the future ... 
		StatusManager statusManager            = loggerContext.getStatusManager ();
		OnConsoleStatusListener statusListener = new OnConsoleStatusListener ();
		statusManager.add (statusListener);


		// o.k. let's get into logging finally, shall we?
		logger.info ("alrighty, let's see some logback logging ...");
		
		// place a value into the mapped diagnostic context, e.g. a user id
		MDC.put ("userID", "101");
		
		// now log something
		logger.debug ("did something");
		
		// doesn't look a lot different from before, does it?
		// that's because the default logging configuration doesn't know about our intentions.
		// we need to configure the logging layout to make use of our custom information

		// it's more common to configure logging with an external config file
		// but for the sake of brevity let's do this programmatically here:
		ConsoleAppender<ILoggingEvent> appender = configureLayout4MDCprogrammatically (loggerContext);


		// now let's log again
		logger.debug ("did something");

		// let's pretend there is another user
		MDC.put ("userID", "102");

		// this logging happens now 'in the context of the second user'
		logger.debug ("did another something");





		// let's configure back to the default layout
		configureDefaultLayoutAgain  (loggerContext, appender);

		logger.info ("switched back to default layout");
		logger.info ("over and out ...");

		loggerContext.stop ();

		System.out.println ("\n******************************* wetfeet.logback DONE ... *************************************");
	}


	/**
	 * Switches the pattern of this appender back to the default.
	 * 
	 * @param loggerContext the logger context we want to configure 
	 * @param appender the appender who's layout pattern we want to change
	 */
	protected static void configureDefaultLayoutAgain (final LoggerContext loggerContext, 
		final ConsoleAppender<ILoggingEvent> appender) 
	{
		String defaultPattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n";

		PatternLayoutEncoder layout = new PatternLayoutEncoder ();
		layout.setContext (loggerContext);
		layout.setPattern (defaultPattern);
		layout.start ();

		appender.setEncoder (layout);
		appender.start ();
	}


	/**
	 * Configures the root logger to use a custom layout which makes use of our context data.
	 * 
	 * @param loggerContext the logger context we want to configure
	 * @return the newly created and configured appender
	 */
	protected static ConsoleAppender<ILoggingEvent> configureLayout4MDCprogrammatically (final LoggerContext loggerContext) {
		loggerContext.reset();

		// create and configure a custom layout
		PatternLayoutEncoder layout = new PatternLayoutEncoder();
		layout.setContext (loggerContext);
		layout.setPattern ("user: %X{userID} - %m%n");
		layout.start ();

		// create and configure a custom appender to use our new layout
		ConsoleAppender <ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
		appender.setContext (loggerContext);
		appender.setEncoder (layout);
		appender.start ();

		// cast root logger to c.q.logback.classic.Logger so that we can attach an appender to it
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger (Logger.ROOT_LOGGER_NAME);
		root.addAppender(appender);

		return appender;
	}		

	
}
