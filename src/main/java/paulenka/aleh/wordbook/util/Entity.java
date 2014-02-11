package paulenka.aleh.wordbook.util;

import java.io.Serializable;

public abstract class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return XStreamUtil.serialize(this);
    }
}