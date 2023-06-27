package com.nit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//1
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
   private String username;
   private String password;
}
