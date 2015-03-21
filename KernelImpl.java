import java.util.*;
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
   ArrayList<IODevice> devices = new ArrayList<IODevice>();
   
   public int syscall(int number, Object... varargs){
      switch(number){
         case MAKE_DEVICE:
            {	
               int deviceID = (Integer)varargs[0];
               String deviceName  = (String)varagrs[1];
               IODeviceImpl device = new IODeviceImpl(deviceName, deviceID);
            
               devices.add(device);
            		
            
            }
         
         case EXECVE:
            {	
               Simulation.timer.advanceTime(SYSCALL_COST);
               Process p = new Process(varargs[0]);
               p.setState(State.READY);
               File file;
               Scanner in;
               try{
                  file = new File(varargs[0]);
                  in = new Scanner(file);
               
               
               }
               catch(IOException e){
                  e.printStackTrace();
               } 
            
               String line;
               ArrayList<Instruction> inst;
            
               while(in.hasNextLine()){
                  line = in.nextLine();
                  String[] data = data.split(" ");
                  if(!data[0].charAt(0) == '#'){
                     if(data[0].equals("CPU")){
                        CPUInstruction c = new CPUInstruction(Integer.parseInt(data[1]), process);
                        inst.add(c);
                     }
                     else if(data[0].equals("IO")){//io instruction
                        IOInstruction i = new IOInstruction(Integer.parseInt(data[1]), process, Integer.parseInt(data[2]));//how to get device id
                        inst.add(i);
                     }
                  
                  }
                  else{
                     continue;
                  }
               
               }
               process.inst = inst; 
               
               Simulation.readyqueue.add(process);
            
            
            
            }
         
         case IO_REQUEST:
            {	int device = (Integer)varargs[0];
               int duration = (Integer)varargs[1];
               Simulation.timer.advanceTime(SYSCALL_COST);
            
               Process po = Simulation.cpu.currentProcess();
               Simulation.timer.cancelInterrupt(po.getID());
            
               Device d = getDevice(device);
            
               de.requestIO(duration, po);
               po.setState(State.TERMINATED);
            
               interupt(WAKE_UP, po.getID());
               Process next =null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyqueue.peek();
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  int time = (d.getFreeTime() > Simuation.timer.getSystemTime() )? d.getFreeTime(): Simuation.timer.getSystemTime();
                  Simulation.timer.scheduleInterrupt(time +next.getInstruction().getDuration() , next.getID());
               
               
               }
            
                          
               Simulation.cpu.contextSwitch(next);
                           
            //timer
            
            
            }
         
         case TERMINATE_PROCESS:
         
         
            {	
               Simulation.timer.advanceTime(SYSCALL_COST);
               Process po = Simulation.cpu.currentProcess();
               Simulation.timer.cancelInterrupt(po.getID);
            
               po.setState(State.TERMINATED);
               Process next  = null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyqueue.peek();
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + next.getInstruction().getDuration() , next.getID());
               
               }
            
               Simulation.cpu.contextSwitch(next);
               
            
            
            //timer
            
            }
      }
      return 0;
   }

	/**
     * Identifies a time_out interrupt. 
     * Optionally, a call to the <code>interrupt</code> with this code,  may provide 
     * the ID of the process to be preempted - should correspond to that currently executing!
     */
    //final static int TIME_OUT = 0;
    
    /**
     * Identifies completion of a device I/O request. 
     * Device ID and Process ID are required parameters.
     */
    //final static int WAKE_UP = 1;
    
    /**
     * Invoke the interrupt handler, providing the interrupt type and zero or more arguments.
     */
     
   private IODevice getDevice(int id){
      for(int i = 0; i< devices.size(); i++){
         if(devices.get(i).getID()==id){
            return devices.get(i);
         }
            
      }
         // assuming the device will always be found.
   }
   public void interrupt(int interruptType, Object... varargs){
      switch(interruptType){
         case TIME_OUT:
            {
               int id = (Integer)varargs[0];
               Process po = Simulation.cpu.currentProcess();
               return id = po.getID();
               
               
               po.setState(State.READY);
               
               Process next = null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyqueue.peek();
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + next.getInstruction().getDuration() , next.getID());
               
               
               }
               Simulation.cpu.contextSwitch(next);
            
               Simulation.readyqueue.add(po);
             
             
             
             
             
            
            
            
            
            }
         case WAKE_UP:
            {
               int device = (Integer)varargs[0];
               int process = (Integer)varargs[1];
            
               IODevice d = getDevice(device);
               Process p = null;
                  Iterator<Process> i = d.queue.iterator();
               while(i.hasNext()){
                  p = i.next();
                  if(p.getID() = process){
                     d.queue.remove(e);
                     break;
                  }
               }
               
               p.setState(State.READY);
            
               d.deviceQueue.remove(p);
            
               p.nextInstruction();
               Simulation.readyQueue.add(p);
            }
         
         
         
      }
   }
}
