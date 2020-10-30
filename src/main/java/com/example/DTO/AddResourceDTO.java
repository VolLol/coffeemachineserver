package com.example.DTO;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddResourceDTO {

    @Getter
    @Setter
    private String message;
}
