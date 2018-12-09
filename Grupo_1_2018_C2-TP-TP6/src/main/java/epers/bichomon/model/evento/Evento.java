package epers.bichomon.model.evento;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EventoAbandono.class),
        @JsonSubTypes.Type(value = EventoArribo.class),
        @JsonSubTypes.Type(value = EventoCaptura.class),
        @JsonSubTypes.Type(value = EventoCoronacion.class)
})
@Document
public abstract class Evento {
    @Id
    private String id;

    private Date fecha = new Date();

    protected Evento() {}

    public String getFecha() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(fecha);
    }

}
