#include "CaPool.hpp"
#include "Ca.hpp"
#include "Board.hpp"
#include "BoardPrinter.hpp"

int main() {

    CaPool<3, 1> caPool(10);

    bool running = true;

    while(running) {
        caPool.calculateFitness();
        caPool.nextGeneration();
    }

//    DeterministicCA<2, 1> dca;
//    Board<2, 1> board(10, 10);
//    BoardPrinter<2, 1> boardPrinter;
//
//    boardPrinter.print(board, "out.bmp");
//
//    for(int i=0; i<10; i++) {
//        board.step(static_cast<CA&>(dca));
//        boardPrinter.print(board, std::string("out") + std::to_string(i) + std::string(".bmp"));
//    }
}