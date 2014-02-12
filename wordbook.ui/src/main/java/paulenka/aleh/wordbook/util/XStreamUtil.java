package paulenka.aleh.wordbook.util;

import com.thoughtworks.xstream.XStream;

public class XStreamUtil {

    private static XStream xStream;

    public static XStream getXStream() {
        if (xStream == null) {
            xStream = new XStream();

            // xStream.alias("WebCrawlerTask", WebCrawlerTask.class);
        }
        return xStream;
    }

    public static String serialize(Object object) {
        return getXStream().toXML(object);
    }

    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String xml) {
        return (T) getXStream().fromXML(xml);
    }
}