package com.poladmahmudov.contactlist.service;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class User {

    String name;

    String avatar;
}
