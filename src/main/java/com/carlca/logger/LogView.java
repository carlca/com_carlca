package com.carlca.logger;

public class LogView {
    
    Receiver receiver;
    
    public LogView() {
        receiver = new Receiver();
    }
    
    public static void main(String[] args) {
        // Create an object of class Main (This will call the constructor)
        LogView logView = new LogView();
    }
}
