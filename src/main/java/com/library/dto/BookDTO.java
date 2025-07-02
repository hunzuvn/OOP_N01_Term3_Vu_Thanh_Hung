//src/main/java/com/library/dto/BookDTO.java
package com.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Integer id;

    @NotBlank(message = "Tiêu đề sách không được để trống")
    private String title;

    @NotBlank(message = "Tác giả sách không được để trống")
    private String author;

    private String category;

    @NotNull(message = "Trạng thái khả dụng không được để trống")
    private Boolean available;

    private String publisher;
    private Integer publicationYear;
    private String isbn;
    private String description;
}