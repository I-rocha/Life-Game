/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package highlife.serial;

/**
 *
 * @author Israel
 */
public class Map {
    private static final int LENGTH = 2048;
    public static final int HIGHLIFE = 0;
    public static final int JOGO_DA_VIDA = 1;
    private static final int MORTO = 0;
    private static final int VIVO = 1;
    private int[][] grid;
    
    public Map(){
        grid = new int[LENGTH][LENGTH];
    }
    
    public void show(int init, int qtd){
        for(int i = init; i < qtd; i++){
            for(int j = init; j < qtd; j++){
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("\n");
    }
    
    public int rp(int pos){
        int limited_n;
        
        limited_n = pos%this.LENGTH;
        
        if(limited_n < 0)
            return (this.LENGTH - Math.abs(limited_n));
        else
            return limited_n;
    }
    
    public void createFirstGrid(){
        //GLIDER
        int lin = 1, col = 1;
        
        grid[lin  ][col+1] = VIVO;
        grid[lin+1][col+2] = VIVO;
        grid[lin+2][col  ] = VIVO;
        grid[lin+2][col+1] = VIVO;
        grid[lin+2][col+2] = VIVO;
        
        //R-pentomino
        lin =10; col = 30;
       
        grid[ lin ][col+1] = VIVO;
        grid[ lin ][col+2] = VIVO;
        grid[lin+1][col  ] = VIVO;
        grid[lin+1][col+1] = VIVO;
        grid[lin+2][col+1] = VIVO;

    }
    
    public int getNeighbours(int i, int j){
        int ng = 0;
        
        ng += grid[rp(i-1)][rp(j-1)];
        ng += grid[rp(i-1)][rp( j )];
        ng += grid[rp(i-1)][rp(j+1)];
        
        ng += grid[rp( i )][rp(j-1)];
        ng += grid[rp( i )][rp(j+1)];
        
        ng += grid[rp(i+1)][rp(j-1)];
        ng += grid[rp(i+1)][rp( j )];
        ng += grid[rp(i+1)][rp(j+1)];
        
        return ng;
    }
    
    public int applyRule(int ng, int i, int j, int flag){
        int newState = 0;
        
	newState = grid[i][j];
	if (grid[i][j] == VIVO) {
		// Se vivo
		
		if (ng < 2 || ng > 3) {
			// Se menos que 2 vizinhos ou acima de 3
			newState = MORTO;
		}
	}
	else if (grid[i][j] == MORTO) {
		// Se morto
		
		if (flag == JOGO_DA_VIDA) {
			// Versão jogo da vida

			if (ng == 3) {
				// Com 3 vizinhos
				newState = VIVO;
			}
		}
		else if (flag == HIGHLIFE) {
			// Versão Highlife

			if (ng == 3 || ng == 6) {
				// Com 3 vizinhos ou 6
				newState = VIVO;
			}
		}
	}
	else {
		System.out.println("Some error occured, cell with invalid state.\nCell will be considered DEAD.");
		newState = MORTO;
	}
	return newState;
    
    }
    public int[][] nextGen(int flag){
        int[][] newGrid;
        int newState, ng;
        
        newGrid = new int[LENGTH][LENGTH];
        
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                ng = getNeighbours(i,j);
                newState = applyRule(ng, i, j, flag);
                newGrid[i][j] = newState;
            }
        }
        
        return newGrid;
    }
    
    public void setGrid(int[][] newGrid){
        grid = newGrid;
    }
    
    public int NSociety(){
        int society = 0;
    
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                society += grid[i][j];
            }
        }
        return society;
    }
}
