//
// Created by ienze on 6/25/17.
//

#ifndef CAEVOLVE_CAPOOL_HPP
#define CAEVOLVE_CAPOOL_HPP

#include "CaEvolveUtils.hpp"
#include "BoardPrinter.hpp"
#include <algorithm>
#include <vector>

template<unsigned stateCount, unsigned visibleRadius>
class CaPool {
    typedef DeterministicCA<stateCount, visibleRadius> ca_t;

    unsigned poolSize;
    std::vector<ca_t> dcas;

public:

    CaPool(unsigned poolSize) : poolSize(poolSize), dcas(poolSize) { }

    void nextGeneration() {

        //semi sort by fitness
        std::sort(dcas.begin(), dcas.end(), [](ca_t& a, ca_t& b){
            if(rand() % 2 == 0) {
                return 0;
            } else {
                if(a.fitness > b.fitness) return 1;
                if(a.fitness < b.fitness) return -1;
                return 0;
            }
        });

        //new generation
        std::vector<ca_t> newGeneration(poolSize);

        //crossover
        for(int i=0; i<poolSize; i+=2) {
            ca_t& ca1 = dcas[rand() % poolSize / 2];
            ca_t& ca2 = dcas[rand() % poolSize / 2];
            CaEvolveUtils::crossover(ca1, ca2);
            newGeneration[i] = ca1;
            newGeneration[i+1] = ca2;
        }

        dcas = newGeneration;

        //mutate
        for(int i=0; i<poolSize; ++i) {
            if(rand() % 10 == 0) {
                CaEvolveUtils::mutate(dcas[i]);
            }
        }
    }

    void calculateFitness() {
        BoardPrinter<stateCount, visibleRadius> boardPrinter;

        for(int i=0; i<poolSize; ++i) {

            Board<stateCount, visibleRadius> board(10, 10);

            for(int j=0; j<10; ++j) {
                board.step(static_cast<CA&>(dcas[i]));
            }

            boardPrinter.print(board, std::string("out") + std::to_string(i+1) + std::string(".bmp"));

            double sum = 0;
            for(int x=0; x<board.width; x++) {
                for(int y=0; y<board.height; y++) {
                    sum += board.get(x, y);
                }
            }

            dcas[i].fitness = sum / (board.width * board.height);
        }
    }
};


#endif //CAEVOLVE_CAPOOL_HPP
