package com.edu.agh.kis.automaton.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("automatonGUI.fxml"));

        Pane pane = loader.load();

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Smoke Spreading Model");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//Dosyć mocno opisane zjawisko a nie automatu

// Wyglda na to że symulacja jest db
// Wczytanie i zapis modelu do pliku
// Opis jak działa ten model
// Co oddać na spotkanie
// kod źrówłowy, wersje wykonywalną, narania z działania projektu - ciekawe przykłady
// Raport - najważniejszy -> wstęp, orzegląd który już był, model szczegółowo jak działa, dokładnie opisanie rgół przejścia, testy - jak to działa, chyba poprostu puszczania
// -> validacja - testy porównanie do rzeczwuistych postaci, kalibracja - ustawienie parametrów modelu - i testy dla różnych parametrów i napisac dla jakich były jakie rezultaty i jakie wybrałyśmy
// -> żeby te parametry w modelu nie brały się z nikąd
// -> jaka technologia, jakie języki, jakie optymalizacje, diagramy działania programu - sekwencja, klas -> do cora, gui można pominąć,
// -> jak działa gui
// -> statystyki - po jakim czasie jaki procent komurek jest po jakim stopniu zadymienia
// sprawozdania ok, 20 stron razem z obrazkami
