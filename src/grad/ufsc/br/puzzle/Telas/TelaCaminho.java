package grad.ufsc.br.puzzle.Telas;

import grad.ufsc.br.puzzle.Entidades.Nodo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaCaminho {
    private JFrame window = new JFrame("Caminho");
    private JButton buttons[] = new JButton[9];
    private JButton botaoAnterior = new JButton("Anterior");
    private JButton botaoPosicao = new JButton("/");
    private JButton botaoProximo = new JButton("Próximo");
    private ArrayList<Nodo> caminho;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private Nodo nodoAtual;
    private int posicaoAtual =1;

    public TelaCaminho(Nodo nodoObjetivo) {
        for (int i = 0; i <= buttons.length - 1; i++) {
            buttons[i] = new JButton();
            window.add(buttons[i]);
            Font font = buttons[i].getFont();
            buttons[i].setFont(new Font(font.getName(), font.getStyle(), 40));
        }
        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(4, 3);
        window.setLayout(gridLayout);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        this.caminho = nodoObjetivo.getCaminhoDoNodo();
        nodoAtual = caminho.get(posicaoAtual);
        setButtonsValues(nodoAtual.getValoresNodo());
        botaoPosicao.setText(posicaoAtual+"/" + this.caminho.size());

        botaoAnterior.addActionListener(e -> anterior());
        botaoProximo.addActionListener(e-> proximo());
        window.add(botaoAnterior);
        window.add(botaoPosicao);
        window.add(botaoProximo);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }


    public void setButtonsValues(Integer[] valores) {
        for (int i = 0; i < buttons.length; i++) {
            if (valores[i] == 0) {
                this.buttons[i].setText("");
            } else {
                this.buttons[i].setText(Integer.toString(valores[i]));
            }
        }

    }

    public void proximo(){
        if(this.posicaoAtual <this.caminho.size()) {
            this.posicaoAtual++;
            this.nodoAtual = this.caminho.get(posicaoAtual-1);
            setButtonsValues(nodoAtual.getValoresNodo());
            botaoPosicao.setText(posicaoAtual+"/"+this.caminho.size());
        } else {
            JOptionPane.showMessageDialog(window,"Fim do caminho");
        }
    }

    public void anterior(){
        if(this.posicaoAtual != 1) {
            this.posicaoAtual--;
            this.nodoAtual = this.caminho.get(posicaoAtual -1);
            setButtonsValues(nodoAtual.getValoresNodo());
            botaoPosicao.setText(posicaoAtual+"/"+this.caminho.size());
        }else{
            JOptionPane.showMessageDialog(window,"Você ja está na posição Inicial");
        }
    }
}
