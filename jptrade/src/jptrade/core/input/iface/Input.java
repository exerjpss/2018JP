package jptrade.core.input.iface;

/*
 * Interface for receiving input
 * 
 */
public interface Input 
{
	/*
	 * Use to initialise input
	 */
	boolean initialise (String args);
	/*
	 * Return a line or return null
	 */
	String getLine ();
	
}
