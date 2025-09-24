package vn.maxtrann.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Response {
    private Boolean status;
    private String message;
    private Object body;
}
