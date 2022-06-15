package by.ashurmatov.anime.model.entity.enums;

public enum Role {
    USER(1),
    ADMIN(2);

    private int priority;

    Role(int priority){
        this.priority=priority;
    }

    public int getPriority() {
        return priority;
    }
}
