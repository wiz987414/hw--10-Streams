package ru.sbt;

import ru.sbt.domain.Document;
import ru.sbt.streams.StreamsImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Document> report = new ArrayList<>();
        report.add(new Document("report1", 12, Calendar.getInstance(), "Manager"));
        report.add(new Document("report2", 10, Calendar.getInstance(), "Reporter"));
        report.add(new Document("report3", 1, Calendar.getInstance(), "Guest"));
        report.add(new Document("title", 100, Calendar.getInstance(), "Administrator"));
        Map<Object, Object> tempMap = StreamsImpl.of(report)
                .testOut()
                .filter(d -> d.getTitle().startsWith("report"))
                .testOut()
                .transform(d -> new Document(d.getTitle(), d.getSize() + 1, d.getCreated(), d.getAuthor()))
                .testOut()
                .toMap(Document::getTitle, Document::getSize);
        for (Map.Entry mapItem : tempMap.entrySet())
            System.out.println(mapItem.getKey().toString() + " - " + mapItem.getValue().toString());
    }
}
