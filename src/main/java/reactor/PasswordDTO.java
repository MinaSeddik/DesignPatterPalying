package reactor;

public class PasswordDTO {

    private String raw;
    private String encoded;

    public PasswordDTO(String raw, String encoded){
        this.raw = raw;
        this.encoded = encoded;
    }

    public CharSequence getRaw() {
        return "123";
    }

    public String getSecured() {
        return "123";
    }
}
