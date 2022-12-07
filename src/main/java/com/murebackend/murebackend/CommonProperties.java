package com.murebackend.murebackend;

import lombok.Data;
import lombok.Setter;

import java.sql.Timestamp;


@Data
@Setter
public class CommonProperties {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
