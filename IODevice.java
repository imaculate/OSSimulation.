
/**
 * Representation of an I/O device and queue.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public interface IODevice {

    /**
     * Obtain the device ID. 
     */
    int getID();
    
    
    /**
     * Obtain the device type name.
     */
    String getName();
    
    /**
     * Obtain the time at which the device will have completed all its current requests.
     */
    long getFreeTime();
    
    /**
     * Place the given process on the device queue.
     */
    long requestIO(int duration, ProcessControlBlock process);
}
