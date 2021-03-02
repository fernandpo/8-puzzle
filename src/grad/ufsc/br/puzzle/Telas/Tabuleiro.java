package grad.ufsc.br.puzzle.Telas;

        import grad.ufsc.br.puzzle.Controladores.ControladorNodo;
        import grad.ufsc.br.puzzle.Entidades.Nodo;
        import grad.ufsc.br.puzzle.Entidades.VariacoesAlgoritimo;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseEvent;
        import java.sql.Time;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Date;
        import java.util.Timer;

public class Tabuleiro {

    private JFrame window = new JFrame("8-puzzle²");
    private JButton buttons[] = new JButton[9];
    private JButton botaoResolver = new JButton("Resolver");
    private JButton botaoMudarHeuristica = new JButton("Mudar Heurística");
    private JButton botaoLimpar = new JButton("Limpar");
    private JMenuBar menubar = new JMenuBar();
    private JMenu menuInformacoes = new JMenu("Informações");
    private JMenu menuDificuldade = new JMenu("Dificuldade");
    private JMenuItem totalDeNodosVisitados = new JMenuItem("Total de nodos visitados: ");
    private JMenuItem totalDeNodosExpandidos = new JMenuItem("Total de Nodos expandidos: ");
    private JMenuItem tamanhoDaMaiorFronteira = new JMenuItem("Tamanho da maior fronteira: ");
    private JMenuItem tamanhoDoCaminho = new JMenuItem("Tamanho do caminho: ");
    private JMenuItem heuristica = new JMenuItem("Heurística: ");
    private JMenuItem tempo = new JMenuItem("Tempo em segundos: ");
    private JMenuItem facilMenu = new JMenuItem("Fácil");
    private JMenuItem medioMenu = new JMenuItem("Médio");
    private JMenuItem dificilMenu = new JMenuItem("Difícil");
    private Integer[] facil = {1, 5, 2,
                               4, 3, 0,
                               7, 8, 6 };

    private Integer[] medio = {5, 0, 4,
                               1, 3, 2,
                               7, 8, 6 };

    private Integer[] dificil = {5, 6, 1,
                                 4, 2, 3,
                                 7, 8, 0 };

    private ControladorNodo controladorNodo;

    Integer[] objetivo = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


    public Tabuleiro() {

        window.setSize(500, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(4, 3);
        window.setLayout(gridLayout);
        window.setLocation(dim.width / 2 - window.getSize().width / 2, dim.height / 2 - window.getSize().height / 2);
        menuInformacoes.add(heuristica);
        menuInformacoes.add(totalDeNodosVisitados);
        menuInformacoes.add(totalDeNodosExpandidos);
        menuInformacoes.add(tamanhoDaMaiorFronteira);
        menuInformacoes.add(tamanhoDoCaminho);
        menuInformacoes.add(tempo);
        menubar.add(menuInformacoes);
        menuDificuldade.add(facilMenu);
        menuDificuldade.add(medioMenu);
        menuDificuldade.add(dificilMenu);
        menubar.add(menuDificuldade);
        window.setJMenuBar(menubar);
        this.controladorNodo = new ControladorNodo();


        for (int i = 0; i <= buttons.length - 1; i++) {
            buttons[i] = new JButton();
            window.add(buttons[i]);
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setButtonValue((JButton) e.getSource());
                }
            });
            Font font = buttons[i].getFont();
            buttons[i].setFont(new Font(font.getName(), font.getStyle(), 40));
        }

        facilMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setButtonsValues(facil);
            }
        });

        medioMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setButtonsValues(medio);
            }
        });

        dificilMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setButtonsValues(dificil);
            }
        });

        botaoResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorNodo.zerarTotais();
                controladorNodo.setFronteira(new ArrayList<>());
                if (buttonValuesAreValid()) {
                    Nodo nodo = new Nodo(0, getButtonValues());
                    botaoResolver.setText("Resolvendo...");
                    Long startTime = new Date().getTime();
                    Nodo nodoObjetivo = controladorNodo.verificaObjetivo(nodo, objetivo);
                    Long endTime = new Date().getTime();
                    Double finalTime = Double.valueOf(endTime - startTime);
                    ArrayList<Nodo> nodosAteObjetivo = nodoObjetivo.getCaminhoDoNodo();
                    nodosAteObjetivo.add(nodoObjetivo);
                    setButtonsValues(nodoObjetivo.getValoresNodo());
                    totalDeNodosVisitados.setText("Total de nodos visitados: ");
                    totalDeNodosExpandidos.setText("Total de Nodos expandidos: ");
                    tamanhoDaMaiorFronteira.setText("Tamanho da maior fronteira: ");
                    tamanhoDoCaminho.setText("Tamanho do caminho: ");
                    tempo.setText("Tempo em segundos: ");
                    heuristica.setText("Heurística: ");
                    heuristica.setText(heuristica.getText()+ controladorNodo.getVariacoesAlgoritimo().getDescricaoVariacaoAlgoritimo());
                    totalDeNodosVisitados.setText(totalDeNodosVisitados.getText() + controladorNodo.getTotalNodosVisitados());
                    totalDeNodosExpandidos.setText(totalDeNodosExpandidos.getText() + controladorNodo.getTotalDeNodosExpandidos());
                    tamanhoDaMaiorFronteira.setText(tamanhoDaMaiorFronteira.getText() + controladorNodo.getTamanhoDaMaiorFronteira());
                    tamanhoDoCaminho.setText(tamanhoDoCaminho.getText() + nodoObjetivo.getCaminhoDoNodo().size());
                    for(Nodo nodoNoCaminho: nodoObjetivo.getCaminhoDoNodo()){
                        System.out.println("Nodo:" + Arrays.toString(nodoNoCaminho.getValoresNodo()));
                        System.out.println(nodoNoCaminho);
                    }
                    tempo.setText(tempo.getText() + finalTime/1000);
                    botaoResolver.setText("Resolver");
                }
            }
        });

        botaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cleanButtonValues();
            }
        });

        botaoMudarHeuristica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {VariacoesAlgoritimo.CUSTO_UNIFORME.getDescricaoVariacaoAlgoritimo(), VariacoesAlgoritimo.A_ESTRELA_SIMPLES.getDescricaoVariacaoAlgoritimo(), VariacoesAlgoritimo.A_ESTRELA_MANHATTAN.getDescricaoVariacaoAlgoritimo()};
                VariacoesAlgoritimo variacoesAlgoritimo = VariacoesAlgoritimo.fromString((String) JOptionPane.showInputDialog(window, "Heurística", "Menu Heurística", JOptionPane.PLAIN_MESSAGE, null, options, controladorNodo.getVariacoesAlgoritimo().getDescricaoVariacaoAlgoritimo()));
                getControladorNodo().setVariacoesAlgoritimo(variacoesAlgoritimo);
            }
        });

        window.add(botaoResolver);
        window.add(botaoMudarHeuristica);
        window.add(botaoLimpar);

        window.setVisible(true);
    }

    public void cleanButtonValues() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
        }
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

    public void setButtonValue(JButton button) {
        String valorBotao = JOptionPane.showInputDialog("Insira o valor: ");
        if (valorBotao != null) {
            if (isButtonValueValid(Integer.parseInt(valorBotao))) {
                if (valorBotao.equals("0")) {
                    button.setText("");
                } else {
                    button.setText(valorBotao);
                }

            }
        }
    }

    public boolean isButtonValueValid(Integer value) {
        for (Integer buttonValue : getButtonValues()) {
            if (value.equals(buttonValue) && value != 0) {
                JOptionPane.showMessageDialog(window, "Valor ja existe!");
                return false;
            }
        }
        if (value < 0 || value > 8) {
            JOptionPane.showMessageDialog(window, "O valor deve ser entre 0 e 8");
            return false;
        }
        return true;
    }


    public boolean buttonValuesAreValid() {
        Integer[] buttonValues = this.getButtonValues();
        int contadorDeZeros = 0;
        for (int i = 0; i < buttonValues.length; i++) {
            if (buttonValues[i] == 0) {
                contadorDeZeros++;
            }
        }
        if (contadorDeZeros > 1) {
            JOptionPane.showMessageDialog(window, "Valores estao faltando!");
            return false;
        }
        return true;
    }

    public Integer[] getButtonValues() {
        Integer[] buttonValues = new Integer[9];
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getText().equals("")) {
                buttonValues[i] = 0;
            } else {
                buttonValues[i] = Integer.parseInt(buttons[i].getText());
            }
        }

        return buttonValues;
    }

    public ControladorNodo getControladorNodo() {
        return controladorNodo;
    }

}
