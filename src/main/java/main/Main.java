package main;

import factory.Factory;
import view.MainFrame;

public class Main {
    public static void main(String[] args) {
        FactoryConfig parser = new FactoryConfig();
        Factory factory = new Factory(parser);
        new Thread(new MainFrame(parser, factory)).start();
        factory.start();
    }
}
