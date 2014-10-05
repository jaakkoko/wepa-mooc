package wad.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Message;
import wad.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @Transactional
    public void addMessage(Message message) {
        message.setPerson("You");
        messageRepository.save(message);
        try{
            Message reply = chatService.getAnswerToMessage(message);
            reply.setPerson("Seppo");
            messageRepository.save(reply);
        }catch (IOException e){
            System.err.println("Caught IOException: " + e.getMessage());
        }
    }
}
