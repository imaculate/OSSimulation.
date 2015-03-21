
/**
 * A SystemTimer (i) provides support for simulated time, and (ii) support for
 * the setting of timeout interrupts by kernel code. 
 * 
 * @author Stephan Jamieson 
 * @version 8/3/2015
 */
public class SystemTimerImpl  {

    /** 
     * Time cost of a system call.
     */
   final static int SYSCALL_COST = 2;
   long systemTime = 0, userTime = 0, kernelTime =0;
	
	
    
    /** 
     * Obtain the current system time.
     */
   long getSystemTime(){
      return systemTime;
   }
    
    /**
     * Obtain the amount of time the CPU has been idle.
     */
   long getIdleTime(){
      //calculate this.
         }

    /**
     * Obtain the amount of time the system has spent executing in user space.
     */
   long getUserTime(){
      return userTime;
   }

    /**
     * Obtain the amount of time the system has spent executing in kernel space.
     */
   long getKernelTime(){
      return kernelTime;
   	
   }

    /**
     * Set the current system time.
     */
   void setSystemTime(long systemTime){
      this.systemTime = systemTime;
   }
    
    /**
     * Advance system time by the given amount.
     */
   void advanceSystemTime(long time){
      systemTime += time;
   }
    
    /**
     * Advance user time and system time by the given amount.
     */
   void advanceUserTime(long time){
      userTime += time;
      systemTime +=time;
   
   }
    
    /**
     * Advance kernel time and system time by the given amount.
     */
   void advanceKernelTime(long time){
      kernelTime+= time;
      systemTime +=time;
      
   }
    
    /**
     * Schedule a timer interrupt for <code>timeUnits</code> time units in the future 
     * for the given process.
     */
   void scheduleInterrupt(int timeUnits, int processID){
      Process p = getProcess(processID);
      Event t = new TimeOutEvent(timeUnits, p);
      Simulation.queue.add(t);
         
   }
    
    /**
     * Cancel timer interrupt for the given process.
     */
   void cancelInterrupt(int processID){
      Process p = getProcess(processID);
      Iterator<Event> i = Simulation.queue.iterator();
      while(i.hasNext()){
         Event e = i.next();
         if((next.getClass()== TimeOutEvent.class) && (e.getProcess().getID == processID)){
            Simulation.queue.remove(e);
            break;
         }
            
      }
      
   
         
      
            
        
      
   }
   
   Process getProcess(int id){
      Iterator<Process> i = Simulation.readyQueue.iterator();
        
      while(i.hasNext()){
         Process next = i.next();
         if(next.getID()==processID()){
            return next;
         }
      }
   
      
   }
}
