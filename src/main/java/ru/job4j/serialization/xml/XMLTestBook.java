package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLTestBook {
    public static void main(String[] args) {
        Author smith = new Author(true, "Tom", 43);
        Book book = new Book(false, smith, "Stories",
                23.4, new String[]{"Piter", "Exmo"});
        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xmlBook = "";
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(book, writer);
                xmlBook = writer.getBuffer().toString();
                System.out.println(xmlBook);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (StringReader reader = new StringReader(xmlBook)) {
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Book returned = (Book) unmarshaller.unmarshal(reader);
                System.out.println(returned);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
