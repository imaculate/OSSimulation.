
/**
 * A SystemTimer (i) provides support for simulated time, and (ii) support for
 * the setting of timeout interrupts by kernel code. 
 * 
 * @author Stephan Jamieson 
 * @version 8/3/2015
 */
public interface SystemTimer  {

    /** 
     * Time cost of a system call.
     */
    final static int SYSCALL_COST = 2;
    
    /** 
     * Obtain the current system time.
     */
    long getSystemTime();
    
    /**
     * Obtain the amount of time the CPU has been idle.
     */
    long getIdleTime();

    /**
     * Obtain the amount of time the system has spent executing in user space.
     */
    long getUserTime();

    /**
     * Obtain the amount of time the system has spent executing in kernel space.
     */
    long getKernelTime();

    /**
     * Set the current system time.
     */
    void setSystemTime(long systemTime);
    
    /**
     * Advance system time by the given amount.
     */
    void advanceSystemTime(long time);
    
    /**
     * Advance user time and system time by the given amount.
     */
    void advanceUserTime(long time);
    
    /**
     * Advance kernel time and system time by the given amount.
     */
    void advanceKernelTime(long time);
    
    /**
     * Schedule a timer interrupt for <code>timeUnits</code> time units in the future 
     * for the given pricess.
     */
    void scheduleInterrupt(int timeUnits, int processID);
    
    /**
     * Cancel timer interrupt for the given process.
     */
    void cancelInterrupt(int processID);
}
