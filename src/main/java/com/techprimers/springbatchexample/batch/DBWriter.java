package com.techprimers.springbatchexample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample.model.User;
import com.techprimers.springbatchexample.repository.UserRepository;

@Component
public class DBWriter implements ItemWriter<User> {

   private final UserRepository userRepository;

   public DBWriter(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public void write(List<? extends User> users) throws Exception {
      userRepository.saveAll(users);
   }
}
