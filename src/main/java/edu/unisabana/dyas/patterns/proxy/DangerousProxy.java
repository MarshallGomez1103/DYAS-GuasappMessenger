package edu.unisabana.dyas.patterns.proxy;

import edu.unisabana.dyas.patterns.util.MessageSender;

public class DangerousProxy implements MessageSender {

    private final MessageSender sender;

    public DangerousProxy(MessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(String message) {
        if (message.contains("##{")) {
            System.out.println("Mensaje bloqueado debido a contenido peligroso.");
            return;
        }

        sender.sendMessage(message);
    }
}
