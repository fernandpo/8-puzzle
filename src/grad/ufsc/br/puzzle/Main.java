package grad.ufsc.br.puzzle;

import grad.ufsc.br.puzzle.Entidades.Tabuleiro;

public class Main {
    public static void  main(String [] args) {


        Integer[] entrada = {1, 2, 3,
                         4, 5, 0,
                         7, 8, 6 };

        Integer[] entrada2 = {1, 2, 0,
                         4, 5, 3,
                         7, 8, 6 };

        // Entrada com 31 passos
        Integer[] entrada3 = {8, 6, 7,
                         2, 5, 4,
                         3, 0, 1 };

        Integer[] entrada4 = {8, 5, 1,
                         2, 6, 7,
                         0, 4, 3 };

        Integer[] entrada5 = {5, 6, 1,
                         4, 2, 3,
                         7, 8, 0 };

        Integer[] objetivo = {1, 2, 3, 4, 5, 6, 7, 8, 0};

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.setButtonsValues(entrada2);

    }
}
