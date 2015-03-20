public class KernelImpl  implements Kernel{
	//final static int MAKE_DEVICE = 1;
    
    /**
     * Execute a program. Program name is a required argument. 
     * Process ID is returned when the call completes.
     */
    //final static int EXECVE = 2;
    
    /**
     * Perform IO request. Device ID is a required argument.
     */
    //final static int IO_REQUEST = 3;
    
    /**
     * Terminate current process.
     */
    //final static int TERMINATE_PROCESS = 4;
    
    
    /**
     *  Invoke the system call with the given number, providing zero or more arguments.
    
     */
    public int syscall(int number, Object... varargs){
	switch(number){
		case MAKE_DEVICE:
		{	
			int deviceID = (Integer)varargs[0];
			String deviceName  = (String)varagrs[1];
			IODeviceImpl device = new IODeviceImpl(deviceName, deviceID);
			//create a queue of devices. 
			
			
	
		}

		case EXECVE:
		{	
			
		}

		case IO_REQUEST:
		{	
			
		}

		case TERMINATE_PROCESS:
		{	
			
		}
	}
	
	}

	/**
     * Identifies a time_out interrupt. 
     * Optionally, a call to the <code>interrupt</code> with this code,  may provide 
     * the ID of the process to be preempted - should correspond to that currently executing!
     */
    final static int TIME_OUT = 0;
    
    /**
     * Identifies completion of a device I/O request. 
     * Device ID and Process ID are required parameters.
     */
    final static int WAKE_UP = 1;
    
    /**
     * Invoke the interrupt handler, providing the interrupt type and zero or more arguments.
     */
    public void interrupt(int interruptType, Object... varargs);
}
