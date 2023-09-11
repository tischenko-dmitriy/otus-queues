package ru.otus.example.queues.model;

public class EventData {

    private String type;

    private Object item;

    public EventData() {
    }

    public EventData(String type, Object item) {
        this.type = type;
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }
}
