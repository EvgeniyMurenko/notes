package com.notes.repos;

import com.notes.domain.Message;
import org.springframework.data.repository.CrudRepository;


public interface MessageRepo extends CrudRepository<Message, Long> {

    Iterable<Message> findByTag(String tag);
}
