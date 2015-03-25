import java.util.*;
import java.io.*;
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
      String details = null;
      switch(number){
         case MAKE_DEVICE:
            {	details=String.format("MAKE_DEVICE, %s,\"%s\"", varargs[0], varargs[1]);
               int deviceID = (Integer)varargs[0];
               String deviceName  = (String)varargs[1];
               IODeviceImpl device = new IODeviceImpl(deviceName, deviceID);
            
               devices.add(device);
              
               System.out.printf("Time: %010d SysCall(%s)\n", Simulation.timer.getSystemTime(), details);
               break;
            		
            
            }
         
         case EXECVE:
            {	
               details=String.format("EXECVE, \"%s\"", varargs[0]);
            
               System.out.printf("Time: %010d SysCall(%s)\n", Simulation.timer.getSystemTime(), details);
             
               Process p = new Process((String)varargs[0]);
               p.setState(ProcessControlBlock.State.READY);
               File file;
               Scanner in;
               try{
                  file = new File((String)varargs[0]);
                  in = new Scanner(file);
                  
                  String line;
                  ArrayList<Instruction> inst = new ArrayList<Instruction>();
               
                  while(in.hasNextLine()){
                     line = in.nextLine();
                                          //System.out.println(line);
                  
                     String[] data = line.split(" ");
                     if(data[0].charAt(0) != '#'){
                        if(data[0].equals("CPU")){
                           CPUInstruction c = new CPUInstruction(Integer.parseInt(data[1]));
                           inst.add(c);
                        }
                        else if(data[0].equals("IO")){//io instruction
                           IOInstruction i = new IOInstruction(Integer.parseInt(data[1]), Integer.parseInt(data[2]));//how to get device id
                           inst.add(i);
                        }
                     
                     }
                     else{
                        continue;
                     }
                  
                  }
                  p.inst = inst; 
                   
               
               
                  Simulation.readyQueue.add(p);
                 
                 
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , p);
               
                     if(Simulation.cpu.isIdle() && !Simulation.readyQueue.isEmpty()){
                 
                           Simulation.cpu.contextSwitch(Simulation.readyQueue.poll());
                     }
               
               }
               catch(IOException e){
                  e.printStackTrace();
                  System.out.println("Couldn't open class");
               } 
               
               Simulation.timer.advanceKernelTime(SystemTimer.SYSCALL_COST);
            
               break;
            
            }
         
         case IO_REQUEST:
         
            {
               details=String.format("IO_REQUEST, %s, %s", varargs[0], varargs[1]);
               System.out.printf("Time: %010d SysCall(%s)\n", Simulation.timer.getSystemTime(), details);
               int device = (Integer)varargs[0];
               int duration = (Integer)varargs[1];
             
               Process po =(Process)varargs[2];
               
               
               
            
               IODevice d = getDevice(device);
               
            
               d.requestIO(duration, po);
               long time = Math.max(d.getFreeTime() , Simulation.timer.getSystemTime() );
                  WakeUpEvent e = new WakeUpEvent(time + duration , d, po  );
                  Simulation.queue.add(e);
                 /*interrupt(WAKE_UP, po.getID());
               Process next =null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyQueue.peek();
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  long time = (d.getFreeTime() > Simulation.timer.getSystemTime() )? d.getFreeTime(): Simulation.timer.getSystemTime();
                  Simulation.timer.scheduleInterrupt(time +Simulation.slicelength , next);
               
                  Simulation.cpu.contextSwitch(next);
               }*/
            
                          
              
            
                           
            //timer
            
               break;
            
            }
         
         case TERMINATE_PROCESS:
         
         
            {
               details="TERMINATE_PROCESS";	
               System.out.printf("Time: %010d SysCall(%s)\n", Simulation.timer.getSystemTime(), details);
       
               Process po = (Process)(Simulation.cpu.getProcess());
               Simulation.timer.cancelInterrupt(po);
            
               po.setState(ProcessControlBlock.State.TERMINATED);
               /*Process next  = null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyQueue.poll();
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , next.getID());
               
               }
               Simulation.cpu.contextSwitch(next);*/
               break;
            
               
            //timer
            
            }
            
         default:
            {
               details="ERROR_UNKNOWN_NUMBER";
               System.out.printf("Time: %010d SysCall(%s)\n", Simulation.timer.getSystemTime(), details);
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
      return null;
         // assuming the device will always be found.
   }
   public void interrupt(int interruptType, Object... varargs){
      String details = null;
      switch(interruptType){
         case TIME_OUT:
            {
               details=String.format("TIME_OUT, %s", varargs[0]);
               System.out.printf("Time: %010d Interrupt(%s)\n", Simulation.timer.getSystemTime(), details);
                Simulation.timer.advanceKernelTime(SystemTimer.SYSCALL_COST);
               Process po = (Process)varargs[0];
             
               if(Simulation.cpu.getProcess()==null){
                  Simulation.cpu.currP = po;
               }
            
               int rem =  Simulation.cpu.execute(Simulation.slicelength);
               if(rem>0){
                  //next event is IO
                  
                  po.nextInstruction();
                  if(po.processNumber == po.inst.size()){
                     syscall(Kernel.TERMINATE_PROCESS);
                  
                  }
                  else{
                     syscall(IO_REQUEST, ((IOInstruction)po.getInstruction()).getDeviceID(), po.getInstruction().getDuration(),po);
                                          
                  
                  
                  }
               }else{
                  Simulation.readyQueue.add(po);
                   Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , po);
                   
                  
               }
                  
                
               
            
               //po.setState(ProcessControlBlock.State.READY);
               
               Process next = null;
               if(!Simulation.readyQueue.isEmpty()){
                 
                  next = Simulation.readyQueue.poll();
                  if(!next.equals(po)){
                    
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , next);
                  }else{
                     next = null;
                  }
               
               }
                  Simulation.cpu.contextSwitch(next);
                   
               break;
             
             
             
             
             
            
            
            
            
            }
         case WAKE_UP:
            {
                Simulation.timer.advanceKernelTime(SystemTimer.SYSCALL_COST);
               details=String.format("WAKE_UP, %s, %s", varargs[0], varargs[1]);
               System.out.printf("Time: %010d Interrupt(%s)\n", Simulation.timer.getSystemTime(), details);
               int device = (Integer)varargs[0];
               Process process = (Process)varargs[1];
                               IODeviceImpl d = (IODeviceImpl)getDevice(device);
              
               
                     d.deviceQueue.remove(process);
                 
             
               process.setState(ProcessControlBlock.State.READY);
            
              
            
               process.nextInstruction();
               Simulation.readyQueue.add(process);
        
               Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , process);


                Process next = null;
               if(!Simulation.readyQueue.isEmpty()){
                  next = Simulation.readyQueue.poll();
                 
                  Simulation.timer.advanceKernelTime(Simulation.overhead);
                  Simulation.timer.scheduleInterrupt(Simulation.timer.getSystemTime() + Simulation.slicelength , next);
                
               
               }
                 Simulation.cpu.contextSwitch(next);

               break;
            }
            
         default:
            details="ERROR_UNKNOWN_NUMBER";
            System.out.println(details);
         
         
         
      }
   
   }
   
   
   
 
}
