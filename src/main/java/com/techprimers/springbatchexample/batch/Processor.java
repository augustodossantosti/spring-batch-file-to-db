package com.techprimers.springbatchexample.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.techprimers.springbatchexample.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

   private static final Map<String, String> DEPT_NAMES = new HashMap<>();

   public Processor() {
      DEPT_NAMES.put("001", "Technology");
      DEPT_NAMES.put("002", "Operations");
      DEPT_NAMES.put("003", "Accounts");
   }

   @Override
   public User process(User user) throws Exception {
      final String deptCode = user.getDept();
      final String dept = DEPT_NAMES.get(deptCode);
      user.setDept(dept);
      user.setTime(new Date());
      return user;
   }
}
