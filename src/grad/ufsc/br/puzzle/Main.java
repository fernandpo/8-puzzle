package grad.ufsc.br.puzzle;

import grad.ufsc.br.puzzle.Telas.Tabuleiro;

public class Main {
    public static void  main(String [] args) {

        Integer[] dificil = {5, 6, 1,
                4, 2, 3,
                7, 8, 0 };

        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.setButtonsValues(dificil);

    }
}
