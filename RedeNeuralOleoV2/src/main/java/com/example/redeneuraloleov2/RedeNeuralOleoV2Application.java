package com.example.redeneuraloleov2;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RedeNeuralOleoV2Application extends Application {



    @Override
    public void start(Stage stage) throws IOException {

        /*
        //Base de treinamento:  35 amostras
        Double[][] x_VetoresTreinamento = new Double[][] {    {0.4329, -1.3719, 0.7022, -0.8535, 1d},
            {0.3024, 0.2286, 0.863, 2.7909 , 1d},
            {0.1349, -0.6445, 1.053, 0.5687 , 1d},
            {0.3374, -1.7163, 0.367, -0.6283 , 1d},
            {1.1434, -0.0485 ,0.6637, 1.2606 , 1d},
            {1.3749, -0.5071, 0.4464, 1.3009 , 1d},
            {0.7221, -0.7587 ,0.7681, -0.5592 , 1d},
            {0.4403, -0.8072 ,0.5154, -0.3129 , 1d},
            {-0.5231, 0.3548, 0.2538, 1.5776 , 1d},
            {0.3255, -2d, 0.7112, -1.1209 , 1d},
            {0.5824, 1.3915, -0.2291, 4.1735 , 1d},
            {0.134, 0.6081, 0.445, 3.223 , 1d},
            {0.148, -0.2988, 0.4778, 0.8649 , 1d},
            {0.7359, 0.1869 ,-0.0872, 2.3584 , 1d},
            {0.7115, -1.1469, 0.3394, 0.9573 , 1d},
            {0.8251, -1.284 ,0.8452, 1.2382 , 1d},
            {0.1569, 0.3712, 0.8825, 1.7633 , 1d},
            {0.0033, 0.6835, 0.5389, 2.8249 , 1d},
            {0.4243, 0.8313, 0.2634 ,3.5855 , 1d},
            {1.049, 0.1326 ,0.9138 ,1.9792 , 1d},
            {1.4276, 0.5331, -0.0145, 3.7286 , 1d},
            {0.5971, 1.4865, 0.2904 ,4.6069 , 1d},
            {0.8475, 2.1479, 0.3179, 5.8235 , 1d},
            {1.3967, -0.4171, 0.6443 ,1.3927 , 1d},
            {0.0044, 1.5378, 0.6099, 4.7755 , 1d},
            {0.2201, -0.5668, 0.0515, 0.7829 , 1d},
            {0.63, -1.248, 0.8591, 0.8093 , 1d},
            {-0.2479, 0.896, 0.0547, 1.7381 , 1d},
            {-0.3088, -0.0929, 0.8659, 1.5483 , 1d},
            {-0.518, 1d, 0.5453, 2.3993 , 1d},
            {0.6833, 0.8266, 0.0829, 2.8864 , 1d},
            {0.4353, -1.4066, 0.4207, -0.4879 , 1d},
            {-0.1069, -3.2329, 0.1856, -2.4572 , 1d},
            {0.4662, 0.6261, 0.7304, 3.437 , 1d},
            {0.8298, -1.4089, 0.3119, 1.3235 , 1d}};

        // 35 saidas desejadas: 1 para cada amostra de treinamento
        Double[] y_SaidaDesejada = new Double[] { 1d, -1d, -1d, -1d, 1d, 1d, 1d, 1d, -1d, 1d, -1d, -1d, 1d, 1d, -1d, -1d, 1d, -1d, -1d, 1d, 1d, -1d, -1d, 1d, -1d, 1d, -1d, 1d, -1d, 1d, 1d, 1d, -1d, -1d, - 1d };

        Integer qtdPadroesDeTreinamento = x_VetoresTreinamento.length; //erro aqui <<<====================
        Double potencialDeAtivacao = 0d;
        Double[] w_Pesos = {0.0125, 0.1, 0.55, 0.005, 0.22};
        Double taxaAprendizagem = 0.02;
        Double somaErroQuadCiclo = 0d;
        Double erroMin = 0d;
        Double epoca = 0d;
        Double erroQuadMedio = 0d;
        Double erroQuadMedioAnterior = 0d;
        Double erroQuadMedioAtual = 0d;
        int qtdMaxCiclos = 10000;
        int ciclos = 0;

        String[] cicloPesosGrid = new String[6]; //em cada ciclo, mostrar os pesos no grid (tabela)

        while (((erroQuadMedio - erroQuadMedioAnterior) <= erroMin) && (ciclos < qtdMaxCiclos)) {

            somaErroQuadCiclo = 0d;
            erroQuadMedioAnterior = erroQuadMedioAtual; //recebe o que estava no ciclo anterior

            //Para cada linha da matriz da amostra de treinamento, atualizamos os pesos
            for (int i = 0; i < qtdPadroesDeTreinamento; i++) {
                potencialDeAtivacao = 0d;

                //Teste da rede com os pesos atuais
                for (int j = 0; j < 5; j++) {
                    potencialDeAtivacao += w_Pesos[j] * x_VetoresTreinamento[i][j]; //somatória
                }
                erroQuadMedio += Math.pow((y_SaidaDesejada[i] - potencialDeAtivacao), 2);

                //Atualização dos pesos
                for (int k = 0; k < w_Pesos.length; k++) {
                    w_Pesos[k] += taxaAprendizagem * Math.pow((y_SaidaDesejada[k] - potencialDeAtivacao), 2) * x_VetoresTreinamento[i][k];
                }

                //Cálculo do erro quadrático
                somaErroQuadCiclo += Math.pow((y_SaidaDesejada[i] - potencialDeAtivacao), 2);
            }

            erroQuadMedio = erroQuadMedio / qtdPadroesDeTreinamento;
            erroQuadMedioAtual = erroQuadMedio;
            ciclos++;
        }


        //Definindo os axis X e Y
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        //Configurando os labels para os axis
        xAxis.setLabel("Ciclos");
        yAxis.setLabel("Erro Quadrático Médio");


         */




        FXMLLoader fxmlLoader = new FXMLLoader(RedeNeuralOleoV2Application.class.getResource("redeneuraloleov2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 710, 400);
        stage.setTitle("Rede Neural Óleo V2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}