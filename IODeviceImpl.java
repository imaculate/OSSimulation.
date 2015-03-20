public class IODeviceImpl implements IODevice{
	
	int ID;
	String name;
	PriorityQueue<ProcessControlBlock> queue;
	
	public IODeviceImpl(String name, int ID){
		this.ID= ID;
		this.name = name;
		queue = new PriorityQueue<ProcessControlBlock>();
		
	}

    int getID(){
		return ID;
			
	}
    
    
    /**
     * Obtain the device type name.
     */
    String getName(){
		return name;
		
	}
    
    /**
     * Obtain the time at which the device will have completed all its current requests.
     */
    long getFreeTime(){
		
	
	}
    
    /**
     * Place the given process on the device queue.
     */
    long requestIO(int duration, ProcessControlBlock process){

	}

	}
