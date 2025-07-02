//src/main/java/com/library/dto/BorrowRecordDTO.java
package com.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowRecordDTO {
    @NotNull(message = "ID sách không được để trống")
    private Integer bookId;

    @NotNull(message = "ID người dùng không được để trống")
    private Integer userId;
}