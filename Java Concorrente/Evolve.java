/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package highlifeconcurrency;
import java.util.*;
/**
 *
 * @author Israel
 */
public class Evolve implements Runnable{
    public int[][] grid, newGrid;
    private final int iinit, iqtd, flag;
    private static final int N_THREADS = 8;
    
    Evolve(int iinit, int iqtd ,int[][] grid, int[][] newGrid, int flag){
        this.iinit = iinit;
        this.iqtd = iqtd;
        this.grid = grid;
        this.newGrid = newGrid;
        this.flag = flag;
    }
    
    public static void nextGen(int[][] grid, int[][] newGrid, int flag){
        int n_cell_per_thread = 0;
        ArrayList<Thread> threads = new ArrayList<>();
        n_cell_per_thread = Map.LENGTH/N_THREADS;
        int limiar = 0;
        String t_name;
        
        
        
        for(int th = 0; th < N_THREADS; th++){ 
            limiar = th* n_cell_per_thread;
            t_name = ("Thread " + th);
            Thread t = new Thread(new Evolve(limiar, n_cell_per_thread, grid, newGrid, flag), t_name);
            threads.add(t);
            t.start();
        }
        for(int th = 0; th < N_THREADS; th++){
            try{threads.get(th).join();}
            catch (InterruptedException ex) {System.out.println("Thread waiting lead to an error");}
        }
        
    }
    
    
    @Override
    public void run(){
        int ng;
        int newState;
        Object o = new Object();

        
        for(int i = iinit; i < (iinit + iqtd); i++){
            for(int k = 0; k < Map.LENGTH; k++){
               
                ng = Map.getNeighbours(i,k, grid);
                newState = Map.applyRule(ng, i, k, flag, grid);
                newGrid[i][k] = newState;
            }
        }
    }
}
