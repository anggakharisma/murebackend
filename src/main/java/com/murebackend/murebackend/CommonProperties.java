package com.murebackend.murebackend;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class CommonProperties {
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
