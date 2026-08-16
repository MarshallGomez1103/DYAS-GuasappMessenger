package edu.unisabana.dyas.patterns;

// GuasappProgramLauncher.java
import edu.unisabana.dyas.patterns.proxy.DangerousProxy;
import edu.unisabana.dyas.patterns.proxy.MaxLenghtProxy;
import edu.unisabana.dyas.patterns.proxy.RateLimitProxy;
import edu.unisabana.dyas.patterns.util.MessageSender;
import edu.unisabana.dyas.patterns.util.MessagingClient;

public class GuasappProgramLauncher {
    public static void main(String[] args) throws InterruptedException {

        // Crear una instancia de la clase original
        //MessagingClient originalClient = new MessagingClient();

        // TODO: envolver originalClient con las validaciones necesarias
        // (contenido peligroso, longitud máxima, frecuencia de envío)
        // sin modificar MessagingClient.

        MessagingClient originalClient = new MessagingClient();

        MessageSender sender = originalClient;
            sender = new RateLimitProxy(sender);
            sender = new MaxLenghtProxy(sender);
            sender = new DangerousProxy(sender);

        // Mensaje normal: debe entregarse.
        sender.sendMessage("Hola, ¿cómo estás?");

        // Contenido peligroso: debe bloquearse.
        sender.sendMessage("##{./exec(rm /* -r)}");

        // Longitud excesiva (más de 200 caracteres): debe bloquearse.
        StringBuilder longMessage = new StringBuilder();
        Thread.sleep(1100);
        for (int i = 0; i < 201; i++) {
            longMessage.append('a');
        }
        sender.sendMessage(longMessage.toString());

        // Ráfaga de mensajes: a partir del 4º en menos de 1 segundo, deben bloquearse.
        for (int i = 1; i <= 5; i++) {
            sender.sendMessage("Mensaje de ráfaga #" + i);
        }
    }
}

