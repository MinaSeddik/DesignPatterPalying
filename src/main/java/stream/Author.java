package stream;

import lombok.Data;

@Data
public class Author {
    private String name;
    private int relatedArticleId;

    // standard getters, setters & constructors
}