//package hseneca.personal_website.utils;
//
//import hseneca.personal_website.model.request.AgeGroupStatsDto;
//import hseneca.personal_website.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class StatsWebSocketHandler extends TextWebSocketHandler {
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        new Thread(() -> {
//            try {
//                while (session.isOpen()) {
//                    List<AgeGroupStatsDto> stats = userService.countUsersByAgeGroup();
//                    session.sendMessage(new TextMessage(stats.toString()));
//                    Thread.sleep(10000); // Gửi mỗi 10 giây
//                }
//            } catch (InterruptedException | IOException e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // Xử lý tin nhắn từ client
//        String clientMessage = message.getPayload();
//        System.out.println("Received message: " + clientMessage);
//
//        // Gửi phản hồi lại client (nếu cần)
//        session.sendMessage(new TextMessage("Server received: " + clientMessage));
//    }
//}
