package com.rabbiter.dm.dto;

/**
 * @author xianxian
 * @date 2022-01-02
 */
public class Message {
    private int type;

    private String title;

    private String from;

    private String to;

    private String messageBody;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", messageBody='" + messageBody + '\'' +
                '}';
    }
}
