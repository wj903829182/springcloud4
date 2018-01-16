package com.jack.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by jack on 2018/1/13.
 */
@Data
public class SessionInfo implements Serializable{
    private String username;
    private String sessionId;
}
