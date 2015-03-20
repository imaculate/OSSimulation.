
/**
 * Interface to the simulated kernel.
 * 
 * Provides two sub interfaces: a system call interface, and an interrupt handler.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public interface Kernel extends SystemCall, InterruptHandler { }
