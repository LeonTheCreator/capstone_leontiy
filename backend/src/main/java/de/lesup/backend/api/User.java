package de.lesup.backend.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
   String username;
   String password;
}
