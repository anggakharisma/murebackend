package com.murebackend.murebackend;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Data
@Setter
public class Model {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
