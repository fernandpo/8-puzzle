package grad.ufsc.br.puzzle.Controladores;

import grad.ufsc.br.puzzle.Entidades.Nodo;
import grad.ufsc.br.puzzle.Entidades.VariacoesAlgoritimo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class ControladorNodo {
    private ArrayList<Nodo> fronteira;
    private int totalNodosVisitados;
    private int tamanhoDaMaiorFronteira;
    private int totalDeNodosExpandidos;
    private VariacoesAlgoritimo variacoesAlgoritimo;


    public ControladorNodo() {
        variacoesAlgoritimo = VariacoesAlgoritimo.CUSTO_UNIFORME;
        totalNodosVisitados = 0;
        tamanhoDaMaiorFronteira= 0;
        totalDeNodosExpandidos= 0;
        fronteira = new ArrayList<>();
    }

    public void zerarTotais(){
        totalNodosVisitados = 0;
        tamanhoDaMaiorFronteira= 0;
        totalDeNodosExpandidos= 0;
    }


    public Nodo verificaObjetivo(Nodo nodo, Integer[] objetivo) {
        Nodo nodoObjetivo = null;
        Nodo nodoAtual = nodo;
        while (nodoObjetivo == null) {
            nodoAtual.setObjetivo(objetivo);
            totalNodosVisitados++;
            if (arraysSaoIguais(objetivo, nodoAtual.getValoresNodo())) {
                nodoObjetivo = nodoAtual;
            } else {
                if (fronteira != null) {
                    if (Objects.isNull(nodoObjetivo)) {
                        ArrayList<Nodo> nodosFilhos = nodoAtual.getNodosFilhos(this.getVariacoesAlgoritimo());
                        fronteira.addAll(nodosFilhos);
                        fronteira.sort(Comparator.comparingInt(Nodo::getCusto));
                        totalDeNodosExpandidos += nodosFilhos.size();
                        if (!fronteira.isEmpty()) {
                            nodoAtual = fronteira.get(0);
                            if(fronteira.size() > tamanhoDaMaiorFronteira){
                                tamanhoDaMaiorFronteira = fronteira.size();
                            }
                            fronteira.remove(0);
                        }
                    }
                }
            }
        }
        return nodoObjetivo;
    }



    public boolean arraysSaoIguais(Integer [] array1, Integer [] array2){
        boolean arraysSaoIguais = true;
        for (int i =0; i<array1.length; i++){
            if(array1[i] != array2[i]){
                arraysSaoIguais = false;
            }
        }
        return  arraysSaoIguais;
    }

    public int getTotalNodosVisitados(){
        return this.totalNodosVisitados;
    }


    public int getTamanhoDaMaiorFronteira() {
        return tamanhoDaMaiorFronteira;
    }

    public int getTotalDeNodosExpandidos() {
        return totalDeNodosExpandidos;
    }

    public VariacoesAlgoritimo getVariacoesAlgoritimo() {
        return variacoesAlgoritimo;
    }

    public ControladorNodo setVariacoesAlgoritimo(VariacoesAlgoritimo variacoesAlgoritimo) {
        this.variacoesAlgoritimo = variacoesAlgoritimo;
        return this;
    }

    public void setFronteira(ArrayList<Nodo> fronteira) {
        this.fronteira = fronteira;
    }
}
