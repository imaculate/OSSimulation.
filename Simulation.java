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
            else if(pdata[0].equals("I/O")){
               kernel.syscall( Kernel.MAKE_DEVICE, Integer.parseInt(pdata[1]) ,pdata[2]);
            
            
            
            
            
            }
         //setting systemtimer to zero.
         }
         timer.setSystemTime(0);
      
      
      
      
         do{
            if(queue.poll().getTime() >= timer.getSystemTime()){
               Event e = queue.peek();
               if(e.getClass() == ExecveEvent.class){
               
                  kernel.syscall(2, ((ExecveEvent)e).getProgramName());
               
               }
               else if(e.getClass() == TimeOutEvent.class){
                  kernel.interrupt(Kernel.TIME_OUT,((TimeOutEvent)e).getProcess().getID());
               }
               else if(e.getClass() == WakeUpEvent.class){
               
                  WakeUpEvent w = (WakeUpEvent)e;
                  kernel.interrupt(Kernel.WAKE_UP, w.getDevice().getID(), w.getProcess().getID());
               
               }
            
            }  	  
         
         
         }while(!queue.isEmpty() && !cpu.isIdle());
      
      
         System.out.println("System Time:   "+ timer.getSystemTime());
         System.out.println("Kernel Time:   "+ timer.getKernelTime());
         System.out.println("User Time:     "+ timer.getUserTime());
         System.out.println("CPU Idle Time: " + timer.getIdleTime());
      
         System.out.println("Context Switches: " + kernel.getContextSwiches());
         double util = (1.0*timer.getUserTime())/timer.getSystemTime();
         
         System.out.println("CPU Utilization :" + util*100);
      
      
      
      
      
      }
      catch(IOException e){
         e.printStackTrace();
      }
   	
   	
   	
   	
   			
   				
   
   }
}
