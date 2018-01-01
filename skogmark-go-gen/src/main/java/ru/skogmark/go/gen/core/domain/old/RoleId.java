package ru.skogmark.go.gen.core.domain.old;

/**
 * Created by SwEEp on 06.01.2017.
 */
public enum RoleId {
    NONE(1),
    COMPLEX(2),
    COMPOUND(3),
    SUBJECT(4),
    PREDICATE(5),
    OBJECT(6),
    ADVERBIAL(7),
    MODIFIER(8);

    public final int value;

    RoleId(int value) {
        this.value = value;
    }
}
