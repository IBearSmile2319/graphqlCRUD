package dev.maicol.graphqlCRUD.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FCMService {
    public void sendNoti(List<String> tokens, String titulo, String mensaje)
            throws FirebaseMessagingException {
        for (String token : tokens) {
            Message message = Message.builder()
                    .setNotification(Notification.builder()
                            .setTitle(titulo)
                            .setBody(mensaje)
                            .build())
                    .setToken(token)
                    .build();

            FirebaseMessaging.getInstance().send(message);
            // Puedes realizar acciones adicionales con la respuesta del envío si lo deseas
        }
    }
}
