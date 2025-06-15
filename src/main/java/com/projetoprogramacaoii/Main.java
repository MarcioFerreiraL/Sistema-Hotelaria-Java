package com.projetoprogramacaoii;

import com.projetoprogramacaoii.controller.HotelController;
import com.projetoprogramacaoii.view.MainView;

public class Main {
    public static void main(String[] args) {
        MainView view = new MainView();
        HotelController controller = new HotelController(view);
        controller.iniciar();
    }
}