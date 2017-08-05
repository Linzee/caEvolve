//
// Created by ienze on 7/4/17.
//

#ifndef CAEVOLVE_BOARD_HPP
#define CAEVOLVE_BOARD_HPP

#include "Ca.hpp"
#include <stdexcept>
#include <vector>

template<unsigned stateCount, unsigned visibleRadius>
class Board {
    std::vector<unsigned> data;

    std::size_t index(unsigned x, unsigned y) const {
        x = (width + x % width) % width;
        y = (height + y % height) % height;
        return y * width + x;
    }

public:
    const unsigned width;
    const unsigned height;

    Board(unsigned width, unsigned height) : data(width * height, 0), width(width), height(height) {
        random();
    }

    Board(const Board& other) : data(other.data), width(other.width), height(other.height) {}

    unsigned get(unsigned x, unsigned y) const {
        return data[index(x, y)];
    }

    void set(unsigned x, unsigned y, unsigned value) {
        data[index(x, y)] = value;
    }

    void random() {
        for(unsigned x=0; x<width; x++) {
            for(unsigned y=0; y<height; y++) {
                data[index(x, y)] = rand() % stateCount;
            }
        }
    }

    void step(CA&ca) {
        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                std::size_t localState = 0;
                unsigned i = 0;

                for(int dx=-static_cast<int>(visibleRadius); dx<=static_cast<int>(visibleRadius); dx++) {
                    for(int dy=-static_cast<int>(visibleRadius); dy<=static_cast<int>(visibleRadius); dy++) {
                        int g = get(x + dx, y + dy);
                        localState += pow(stateCount, i) * get(x + dx, y + dy);
                        i++;
                    }
                }

                set(x, y, ca.get(localState));
            }
        }
    }
};


#endif //CAEVOLVE_BOARD_HPP
