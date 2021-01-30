package com.poladmahmudov.contactlist;

import com.poladmahmudov.contactlist.config.PersistenceConfiguration;
import com.poladmahmudov.contactlist.config.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import( {PersistenceConfiguration.class, WebConfiguration.class})
public class ContactListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactListApplication.class, args);
    }

}
