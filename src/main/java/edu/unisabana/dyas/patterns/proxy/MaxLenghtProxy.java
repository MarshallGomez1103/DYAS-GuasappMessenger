package edu.unisabana.dyas.patterns.proxy;

import edu.unisabana.dyas.patterns.util.MessageSender;

public class MaxLenghtProxy implements MessageSender {

    private final MessageSender sender;

    public MaxLenghtProxy(MessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(String message) {
        if (message.length() > 200) {
            System.out.println("Mensaje bloqueado debido a que es muy largo.");
            return;
        }

        sender.sendMessage(message);
    }
}
