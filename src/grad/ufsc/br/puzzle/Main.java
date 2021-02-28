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

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.setButtonsValues(entrada2);

    }
}
