public class Simulation{
   public static void main(String[] args){
      String configfile = args[0];
   
      static int slicelength =  Integer.parseInt(arg[1]);
      static int overhead = Integer.parseInt(args[2]);
      static SystemTimerImpl timer = new SystemTimer();
      File file;
      Scanner in;
      static int numOfProcesses = 0;
      static KernelImpl kernel = new KernelImpl();
      static CPUImpl cpu = new CPUImpl();
      static PrioirityQueue<Process> readyQueue = new PriorityQueue();
   	
      try 
      {   
          
      
            // Prepare to read from the file, using a Scanner object
         file = new File(configfile);
         in = new Scanner(file);
      }
      catch(IOException e){
         e.printStackTrace();
      }
      EventQueue queue = new EventQueue();
   
            
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
            kernel.syscall( MAKE_DEVICE, Integer.parseInt(pdata[1]) ,pdata[2]);
            
            
         
         
         
         }
      //setting systemtimer to zero.
      }
      timer.setSystemTime(0);
      
   	
   	
   
      do{
         if(queue.poll().time >= timer.getSystemTime()){
            Event e = queue.peek();
            if(e.getClass() == EvecveEvent.class){
               
               kernel.syscall(2, e.getProgramName());
               
            }
            else if(e.getClass() == TimeOutEvent.class){
               kernel.interrupt(Kernel.TIME_OUT, e.getProcess().getID());
            }
            else if(e.getClass() == WakeUpEvent.class){
                  kernel.interrupt(Kernel.WAKE_UP, e.getDevice().getID(), e.getProcess().getID());
               
            }
            
         }  	  
         
      	
      }while(!queue.isEmpty() && !cpu.isIdle());
   
   
        System.out.pt
   	
   
   	
   	
   	
   	
   			
   				
   
   }
}
