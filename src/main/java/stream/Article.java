package stream;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Article {
    private List<Author> listOfAuthors;
    private int id;
    private String name;

    public Article(String name) {
        this.name = name;
    }

    // standard constructors/getters/setters


}

