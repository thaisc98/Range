



public class ExclusionMutua implements Runnable {


    private static int counter = 0;

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        int nucleos = runtime.availableProcessors();
        Thread[] hilos = new Thread[nucleos];

       for(int x = 0; x < hilos.length; x++){
           Runnable runnable = new ExclusionMutua();
           hilos[x] = new Thread(runnable);
           hilos[x].start();
       }


        for(int x = 0; x < hilos.length; x++){
            try{
                hilos[x].join();
            }catch (Exception e){}
        }

        System.out.print(counter);
    }

    @Override
    public void run() {
        for(int x = 0; x < 10000; x++){
            counter++;
        }
    }
}
