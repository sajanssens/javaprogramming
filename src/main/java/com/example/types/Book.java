package com.example.types;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor class Book {
    long id;
    String title;
}