//
// Created by ienze on 6/25/17.
//

#ifndef CAEVOLVE_CAEVOLVEUTILS_HPP
#define CAEVOLVE_CAEVOLVEUTILS_HPP

#include "Ca.hpp"
#include <random>

namespace CaEvolveUtils {

    template <unsigned stateCount, unsigned size>
    void static init(DeterministicCA<stateCount, size>& ca) {
        ca.fitness = 0U;
        for(unsigned i=0U; i<ca.possibleStatesCount; ++i) {
            ca.dca[i] = rand() % stateCount;
        }
    }

    template <unsigned stateCount, unsigned size>
    void static mutate(DeterministicCA<stateCount, size>& ca) {
        ca.dca[rand() % ca.possibleStatesCount] = rand() % stateCount;
    }

    template <unsigned stateCount, unsigned size>
    void static crossover(DeterministicCA<stateCount, size>& ca1, DeterministicCA<stateCount, size>& ca2) {
        for(unsigned i=0U; i<ca1.possibleStatesCount; ++i) {
            if(rand() % 2 == 0) {
                unsigned temp = ca1.dca[i];
                ca1.dca[i] = ca2.dca[i];
                ca2.dca[i] = temp;
            }
        }
    }

};


#endif //CAEVOLVE_CAEVOLVEUTILS_HPP
