package jimo.care.care_note.info;

import lombok.Data;

/***
 * 封装的与前端的交互信息！
 */
@Data
public class Message {
    private int status;
    private String result;
    private Object data;

    public Message() {
    }

    public Message(int status, String result, Object data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }
}
