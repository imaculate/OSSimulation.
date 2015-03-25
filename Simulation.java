import java.util.*;
import java.io.*;
public class Simulation{
   static int slicelength;
   static int overhead;
   static int numOfProcesses = 0;
   static KernelImpl kernel = new KernelImpl();
   static CPUImpl cpu = new CPUImpl();
   static LinkedList<Process> readyQueue = new LinkedList<Process>();
   static SystemTimerImpl timer = new SystemTimerImpl();
   static EventQueue queue = new EventQueue();

   public static void main(String[] args){
      String configfile = args[0];
   
      slicelength =  Integer.parseInt(args[1]);
      overhead = Integer.parseInt(args[2]);
      
      File file;
      Scanner in;
         	
      try 
      {   
          
      
            // Prepare to read from the file, using a Scanner object
         file = new File(configfile);
         in = new Scanner(file);
      
      
      
            
            // Read each line until end of file is reached
         while (in.hasNextLine())
         {
             
            String line = in.nextLine();
            String[] pdata = line.split(" ");
            if(pdata[0].equals("PROGRAM")){	
               ExecveEvent event = new ExecveEvent(Integer.parseInt(pdata[1]), pdata[2]);
            
               queue.add(event);
            }
            else if(pdata[0].equals("DEVICE")){
               kernel.syscall( Kernel.MAKE_DEVICE, Integer.parseInt(pdata[1]) ,pdata[2]);
            
            
            
            
            
            }
         //setting systemtimer to zero.
         }
         timer.setSystemTime(0);
      
      
      
         
         
            
            
         
          
           //(queue.peek().getTime()>= timer.getSystemTime()) 
            while(  !queue.isEmpty() ){
               
               Event e = queue.poll();
              
               
               if(e.getClass() == ExecveEvent.class){
                  if(timer.getSystemTime() < e.getTime()){
                     timer.setSystemTime(e.getTime());
                  }
                  
                  kernel.syscall(2, ((ExecveEvent)e).getProgramName());
                  
               
               }
               else if(e.getClass() == TimeOutEvent.class){
                  TimeOutEvent t = (TimeOutEvent)e;
                
                
                  kernel.interrupt(Kernel.TIME_OUT,t.getProcess());
               }
               else if(e.getClass() == WakeUpEvent.class){
                 
                  WakeUpEvent w = (WakeUpEvent)e;
                  kernel.interrupt(Kernel.WAKE_UP, w.getDevice().getID(), w.getProcess());
               
               }
               
                          
              
               
            
            
            }
          	  
         
         
         
      
      
         System.out.println("System Time:   "+ timer.getSystemTime());
         System.out.println("Kernel Time:   "+ timer.getKernelTime());
         System.out.println("User Time:     "+ timer.getUserTime());
         System.out.println("CPU Idle Time: " + timer.getIdleTime());
      
         System.out.println("Context Switches: " + cpu.getContextSwiches());
         double util = (1.0*timer.getUserTime())/timer.getSystemTime();
         
         System.out.println("CPU Utilization :" + util*100);
      
      
      
      
      
      }
      catch(IOException e){
         e.printStackTrace();
      }
   	
   	
   	
   	
   			
   				
   
   }
}
