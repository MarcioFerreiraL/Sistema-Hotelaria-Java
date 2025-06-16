package com.projetoprogramacaoii;

import com.projetoprogramacaoii.controller.HotelController;
import com.projetoprogramacaoii.view.MainView;

/**
 * Classe principal que inicia a aplicação do sistema de hotelaria.
 * Responsável por instanciar a View e o Controller e dar início à execução do programa.
 */
public class Main {
    /*
     * Ponto de entrada (entry point) do programa.
     */
    public static void main(String[] args) {
        // Instancia a view, que é responsável pela interação com o usuário.
        MainView view = new MainView();
        // Instancia o controller, passando a view para que ele possa controlá-la.
        HotelController controller = new HotelController(view);
        // Inicia o loop principal do sistema através do controller.
        controller.iniciar();
    }
}