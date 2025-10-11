package org.example.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BookingService {
   private final static Logger log= LoggerFactory.getLogger(BookingService.class);

   private final JdbcTemplate jd;

   public BookingService(JdbcTemplate jd){
       this.jd=jd;
   }

   @Transactional
   public void book(String...persons){
       String query="insert into BOOKINGS(FIRST_NAME) values (?)";
       for(String person:persons){
           log.info("booking "+person);
           jd.update(query,person);
       }
   }

   public List<String> findAllBooks(){
       String query="select FIRST_NAME from BOOKINGS";
       List<String>list=jd.query(query,(rs,rowNum)->rs.getString("FIRST_NAME"));
       return list;
   }

}
