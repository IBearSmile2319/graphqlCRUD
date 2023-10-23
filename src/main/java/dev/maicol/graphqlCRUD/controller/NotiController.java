package dev.maicol.graphqlCRUD.controller;
import dev.maicol.graphqlCRUD.request.NotiRequest;
import dev.maicol.graphqlCRUD.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.firebase.messaging.FirebaseMessagingException;

@Controller
@RequestMapping("/noti")
public class NotiController {
    private final FCMService fcmService;

    @Autowired
    public NotiController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping(path = "/enviar", produces = "application/json")
    public ResponseEntity<String> enviarNotificacion(
            @RequestBody NotiRequest notificationRequest) throws FirebaseMessagingException {
        try {

            fcmService.sendNoti(
                    notificationRequest.getTokens(),
                    notificationRequest.getTitulo(),
                    notificationRequest.getMensaje());
            return ResponseEntity.ok("Notificacion enviada");
        } catch (Exception e) {
            return ResponseEntity.ok("Error al enviar notificacion");
        }
    }
}
