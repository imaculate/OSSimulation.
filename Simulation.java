public class Simulation{
	public static void main(String[] args){
		String configfile = args[0];

		int slicelength =  Integer.parseInt(arg[1]);
		int overhead = Integer.parseInt(args[2]);
		static SystemTimerImpl timer = new SystemTimer();
	
		
        try 
        {   
          

            // Prepare to read from the file, using a Scanner object
            File file = new File(configfile);
            Scanner in = new Scanner(file);
			
		EventQueue queue = new EventQueue();

            
            // Read each line until end of file is reached
            while (in.hasNextLine())
            {
             
                String line = in.nextLine();
		String[] pdata = line.split(" ");
		if(pdata[0].equals("PROGRAM")){	
			LoadProgramEvent event = new LoadProgramEvent(Integer.parseInt(pdata[1]), pdata[2]);
			
			queue.add(event);
		}else if(pdata[0].equals("I/O")){
			IODeviceImpl device  = new IODeviceImpl(pdata[2], Integer.parseInt(pdata[1]));
			
		
			
			
		}
		//setting systemtimer to zero.
	}
		timer.setSystemTime(0);
		CPUImpl  cpu = new CPUImpl();
		
		

		while(!queue.isEmpty() && cpu.isIdle() ){
			
			
			
		}

		

		
		
		
		
				
					
	
	}
}
