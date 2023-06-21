package net.javaguides.springboot.Domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Revision {
    private int old;
    @JsonProperty("new")
    private int newVersion;
    // Getters and setters
}