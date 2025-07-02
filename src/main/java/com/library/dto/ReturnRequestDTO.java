//src/main/java/com/library/dto/ReturnRequestDTO.java
package com.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnRequestDTO {
    @NotNull(message = "ID bản ghi mượn không được để trống")
    private Integer borrowRecordId;
}