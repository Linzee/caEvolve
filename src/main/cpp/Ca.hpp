//
// Created by ienze on 6/25/17.
//

#ifndef CAEVOLVE_CA_HPP
#define CAEVOLVE_CA_HPP

#include <vector>
#include <array>

template<typename T>
T constexpr templated_pow(T base, T exponent) {
    return exponent == 0 ? 1 : base * templated_pow(base, exponent - 1);
}

class CA {
public:
    double fitness;

    CA() : fitness(0) {}

    virtual unsigned get(std::size_t localState) const = 0;
};

template<unsigned stateCount, unsigned visibleRadius>
class DeterministicCA : public CA {
public:
    static constexpr unsigned possibleStatesCount = static_cast<unsigned>(templated_pow(stateCount, (visibleRadius*2+1)*(visibleRadius*2+1) ));
    std::array<unsigned, possibleStatesCount> dca;

    DeterministicCA() {
        for(unsigned i=0U; i<possibleStatesCount; ++i) {
            dca[i] = rand() % stateCount;
        }
    }



    unsigned get(std::size_t localState) const override {
        return dca[localState];
    }
};

#endif //CAEVOLVE_CA_HPP
