package com.stacktips.chat.repository;

import com.stacktips.chat.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderEmail(String senderEmail);
}
