/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package highlifeconcurrency;

/**
 *
 * @author Israel
 */
public class HighLifeConcurrency {
    private static final int N_GEN = 2000;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
          
        int[][] newGrid;
        int i = -1;
        
        Map mm = new Map();
        
        mm.createFirstGrid();
        System.out.println("Condicao inicial :" + mm.NSociety());

        long startTimeGen = System.currentTimeMillis();
        for(i = 1; i < N_GEN; i++){
            newGrid = new int[Map.LENGTH][Map.LENGTH];
            Evolve.nextGen(mm.grid, newGrid, Map.HIGHLIFE );
            mm.setGrid(newGrid);
            
            if(i <= 5){
                System.out.println("Geracao " + i + ": " + mm.NSociety());
                mm.show(0, 50);
            }
        }
        
        long endTime = System.currentTimeMillis();
        long endTimeGen = System.currentTimeMillis();
        System.out.println("Geracao " + i + ": " + mm.NSociety());

        double execTime = (endTime-startTime);
        double genTime = (endTimeGen-startTimeGen);
        
        System.out.println("Total execution time: " + execTime + " ms");
        System.out.println("Total generation time: " + genTime + " ms");
        
    }
    
}
