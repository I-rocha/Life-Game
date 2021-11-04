/*
 * Highlife
 * Jogo da Vida
 */
package highlife.serial;

/**
 *
 * @author Israel
 */

public class HighLifeSerial {
    private static int N_GEN = 2000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       long startTime = System.currentTimeMillis();
        Map mm = new Map();
        mm.createFirstGrid();
        int i = -1;
        
        System.out.println("Condicao inicial: " + mm.NSociety());
        
        long startTimeGen = System.currentTimeMillis();
        for(i = 1; i < N_GEN; i++){
            mm.setGrid(mm.nextGen(Map.HIGHLIFE));
            
            if(i <= 5){
                System.out.println("Geracao " + i + ": " + mm.NSociety());
                mm.show(0,50);
            }
        }
        
        long endTimeGen = System.currentTimeMillis();
        
        System.out.println("Geracao " + i + ": " + mm.NSociety());
        long endTime = System.currentTimeMillis();  
        
        double execTime = (endTime-startTime);
        double genTime = (endTimeGen-startTimeGen);
        
        System.out.println("Total execution time: " + execTime + " ms");
        System.out.println("Total generation time: " + genTime + " ms");
    }
    
}
