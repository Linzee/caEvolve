//
// Created by ienze on 7/4/17.
//

#ifndef CAEVOLVE_BOARDPRINTER_HPP
#define CAEVOLVE_BOARDPRINTER_HPP

#include "Board.hpp"
#include "bitmap_image.hpp"
#include <string>

template<unsigned stateCount, unsigned visibleRadius>
class BoardPrinter {
public:
    void print(const Board<stateCount, visibleRadius>& board, const std::string path) {
        bitmap_image image(board.width, board.height);

        rgb_t color;
        for(unsigned x=0; x<board.width; x++) {
            for(unsigned y=0; y<board.height; y++) {
                unsigned char c = static_cast<unsigned char>((static_cast<float>(board.get(x, y)) / (stateCount-1)) * 255);
                color = {c, c, c};
                image.set_pixel(x, y, color);
            }
        }

        image.save_image(path);
    }

};


#endif //CAEVOLVE_BOARDPRINTER_HPP
