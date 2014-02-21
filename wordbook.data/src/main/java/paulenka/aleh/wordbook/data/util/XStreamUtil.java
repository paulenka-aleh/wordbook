package paulenka.aleh.wordbook.data.util;

import com.thoughtworks.xstream.XStream;

public final class XStreamUtil {

    private final static XStream X_STREAM = new XStream();;

    private XStreamUtil() {
    }

    public static XStream getXStream() {
        return X_STREAM;
    }

    public static String serialize(Object object) {
        return getXStream().toXML(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String xml) {
        return (T) getXStream().fromXML(xml);
    }
}