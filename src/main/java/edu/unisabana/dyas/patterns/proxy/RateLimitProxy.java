package edu.unisabana.dyas.patterns.proxy;

import edu.unisabana.dyas.patterns.util.MessageSender;

public class RateLimitProxy implements MessageSender {

    private final MessageSender sender;
    private long windowStart;
    private int messagesSent;

    public RateLimitProxy(MessageSender sender) {
        this.sender = sender;
        this.messagesSent = 0;
        this.windowStart = System.currentTimeMillis();
    }

    @Override
    public void sendMessage(String message){
        long now = System.currentTimeMillis();

        if (now - windowStart >= 1000) {
            windowStart = now;
            messagesSent = 0;
        }

        if (messagesSent >= 3) {
            System.out.println("Mensaje bloqueado por que esta enviando muchos y peta el server");
            return;
        }

        messagesSent++;
        sender.sendMessage(message);
    }
}
