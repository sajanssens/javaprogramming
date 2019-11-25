package com.example.types;

import lombok.Value;

@Value(staticConstructor = "of") class ISBN {
    String value;
}