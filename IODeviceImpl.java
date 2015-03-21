import java.util.*;
public class IODeviceImpl implements IODevice{
	
	int ID;
	String name;
	LinkedList<Process> deviceQueue;
	
	public IODeviceImpl(String name, int ID){
		this.ID= ID;
		this.name = name;
		deviceQueue = new LinkedList<Process>();
		
	}

    public int getID(){
		return ID;
			
	}
    
    
    /**
     * Obtain the device type name.
     */
    public String getName(){
		return name;
		
	}
    
    /**
     * Obtain the time at which the device will have completed all its current requests.
     */
    public long getFreeTime(){
      
        Iterator<Process> i = deviceQueue.iterator();
        long freeTime = Simulation.timer.getSystemTime();
      while(i.hasNext()){
         freeTime += ((IOInstruction)i.next().getInstruction()).getDuration();

       }
      
		return freeTime;
	
	}
    
    /**
     * Place the given process on the device queue.
     */
    public long requestIO(int duration, ProcessControlBlock process){
      
      deviceQueue.add((Process)process);
      return getFreeTime() +duration;//anticipated time of execution.
         

	}

	}
