package web.karrot.controller.dto;

import lombok.Getter;

@Getter
public class ProductAddRequestDTO {
    public String category;
    public Long price;
    public String image;
    public String title;
    public String contents;
}
