package grad.ufsc.br.puzzle.Entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Nodo {



    private int custo;
    private Integer[] valoresNodo;
    private ArrayList<Nodo> nodosFilhos;
    private ArrayList<Nodo> caminhoDoNodo;
    private Integer [] objetivo;

    public ArrayList<Nodo> getCaminhoDoNodo() {
        return caminhoDoNodo;
    }


    public Nodo(int custo, Integer [] valoresNodo) {
        this.custo = custo;
        this.valoresNodo = valoresNodo;
        this.nodosFilhos = new ArrayList<>();
        this.caminhoDoNodo = new ArrayList<>();
    }

    // Mudar para Heuristicas diferentes
    public int getCustoByAlgoritimo(VariacoesAlgoritimo variacoesAlgoritimo) {
        switch (variacoesAlgoritimo) {
            case CUSTO_UNIFORME:
                return custo;
            case A_ESTRELA_SIMPLES:
                int custoFinal = 0;
                for (int i = 0; i < objetivo.length; i++) {
                    if (valoresNodo[i] != objetivo[i]) {
                        custoFinal++;
                    }
                }
                return custoFinal;
            case A_ESTRELA_MANHATTAN:
                int custoManhattan = 0;
                for (int i = 0; i < objetivo.length; i++) {
                    if (valoresNodo[i] != objetivo[i]) {
                        Integer posicaoObjetivo = getPosicaoObjetivo(valoresNodo[i]);
                        custoManhattan += Math.abs((posicaoObjetivo - 1)/3) + Math.abs((posicaoObjetivo - 1)%3);
                    }
                }
                return custoManhattan;
        }
        return custo;
    }

    public Integer getPosicaoObjetivo(Integer valor){
        if(valor == 0) {
            return 8;
        } else {
            return valor - 1;
        }
    }

    public Integer [] getValoresNodo() {
        return valoresNodo;
    }


    public ArrayList<Nodo> getNodosFilhos(VariacoesAlgoritimo variacoesAlgoritimo) {
        if(!nodosFilhos.isEmpty()){
            return nodosFilhos;
        } else {
            int[] posicoesAdjacentes = this.getPosicoesAdjacentes();
            Arrays.stream(posicoesAdjacentes).forEach(posicaoAdjacente -> {
                Integer [] valoresNodoFilho = new Integer[9];
                System.arraycopy(this.getValoresNodo(),0,valoresNodoFilho,0,9);
                int valorNaPosicaoAdjacente = valoresNodoFilho[posicaoAdjacente];
                valoresNodoFilho[posicaoAdjacente] = 0;
                valoresNodoFilho[this.getPosicaoVazia()] = valorNaPosicaoAdjacente;
                Nodo nodoFilho = new Nodo(this.getCustoByAlgoritimo(variacoesAlgoritimo) + 1, valoresNodoFilho);
                if(this.caminhoDoNodo.isEmpty()){
                    nodoFilho.caminhoDoNodo.add(this);
                } else {
                    nodoFilho.caminhoDoNodo.addAll(this.caminhoDoNodo);
                    nodoFilho.caminhoDoNodo.add(this);
                }
                if(!nodoEstaNoCaminho(this.getCaminhoDoNodo(),nodoFilho.valoresNodo)){
                    nodosFilhos.add(nodoFilho);
                }

            });
            return nodosFilhos;
        }
    }


    public int getPosicaoVazia() {
        return Arrays.asList(this.valoresNodo).indexOf(0);
    }

    public int getCusto() {
        return custo;
    }

    public int [] getPosicoesAdjacentes() {
        int [] adjacentes = new int [] {};
        switch (this.getPosicaoVazia()) {
            case 0:
                adjacentes = new int []{1,3};
                break;
            case 1:
                adjacentes =  new int []{0,2,4};
                break;
            case 2:
                adjacentes = new int []{1,5};
                break;
            case 3:
                adjacentes =  new int []{0,4,6};
                break;
            case 4:
                adjacentes =  new int []{1,3,5,7};
                break;
            case 5:
                adjacentes = new int []{2,4,8};
                break;
            case 6:
                adjacentes = new int []{3,7};
                break;
            case 7:
                adjacentes = new int []{4,6,8};
                break;
            case 8:
                adjacentes = new int []{5,7};
                break;
        }
        return adjacentes;
    }
    // Verifica se o nodo ja esta no caminho
    public boolean nodoEstaNoCaminho (ArrayList<Nodo> caminhos, Integer [] noNodoFilho){
        return caminhos.stream().filter(nodo ->{
            for (int i = 0; i < (nodo.valoresNodo.length - 1); i++){
                if(!(nodo.valoresNodo[i] == (noNodoFilho[i]))){
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList()).size() > 0;
    }

    public Nodo setObjetivo(Integer[] objetivo) {
        this.objetivo = objetivo;
        return this;
    }
    public String toString(){
        String retorno = " ";
        for (int i =0;i< this.getValoresNodo().length;i++){
            retorno += valoresNodo[i] +", ";
            if(i == 2 || i == 5){
                retorno += "\n ";
            }
        }
        return retorno;
        //return Arrays.toString(this.getValoresNodo());
    }
}
