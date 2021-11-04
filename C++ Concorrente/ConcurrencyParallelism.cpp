// Concurrency and Parallelism
// Israel da Rocha


// C++	//
#include <iostream>
#include <omp.h>
#include <ctime> 
#include <chrono>


//	Personal	//
#include "mapCon.h"
#include "settings.h"

int main()
{
	MapCon* obj = new MapCon();
	int** newGrid;
	int i = 1;

	std::chrono::high_resolution_clock::time_point startTime = std::chrono::high_resolution_clock::now();


	omp_set_num_threads(N_TRHEADS);
	
	obj->setFirstGrid();
	std::cout << "Condicao inicial: " << obj->NSociety() << std::endl;
	
	std::chrono::high_resolution_clock::time_point startTimeGen = std::chrono::high_resolution_clock::now();
	for (i = 1; i < N_GEN; i++) {
		

		newGrid = obj->nextGen(JOGO_DA_VIDA);
		
		obj->setGrid(newGrid);

		if (i <= 5) {
			std::cout << "Geracao " << i << ": " << obj->NSociety() << std::endl;
			obj->showState(0, 50);
		}
	}
	std::chrono::high_resolution_clock::time_point endTimeGen = std::chrono::high_resolution_clock::now();
	

	std::cout << "Geracao " << i << ": " << obj->NSociety() << std::endl;

	std::chrono::high_resolution_clock::time_point endTime = std::chrono::high_resolution_clock::now();
	
	std::chrono::duration<double, std::milli> execTime = endTime - startTime;
	std::chrono::duration<double, std::milli> execTimeGen = endTimeGen - startTimeGen;

	std::cout << "Total execution time: " << execTime.count()/1000 << "s" << std::endl;
	std::cout << "Generation execution time: " << execTimeGen.count() / 1000 << "s" << std::endl;

	
}