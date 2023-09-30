package aware.p2c;

import twitter4j.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataParser {

    private JSONObject json;

    public DataParser(String file) {
//        System.out.println("Data file: " + file);
        try {
            String jsonAsString = new String(Files.readAllBytes(Paths.get(file)));
            json = new JSONObject(jsonAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFirstName() {
        return json.getString("firstName");
    }

    public String getLastName() {
        return json.getString("lastName");
    }

    public String getMiddleName() {
        try {
            return json.getString("middleName");
        } catch (Exception ex) {
            return null;
        }
    }

    public String getORI() {
        try {
            return json.getString("ORI");
        } catch (Exception ex) {
            return null;
        }
    }

    public String getDateOfBirth() {
        return json.getString("dateOfBirth");
    }

    public String getAddress() {
        return json.getString("address");
    }

    public String getCompilationDate() {
        return json.getString("compilationDate");
    }

    public String getReasonFingerPrinted() {
        try {
            return json.getString("reasonOfFingerPrint");
        } catch (Exception ex) {
            return null;
        }
    }

    public String getPlaceOfBirth() {
        return json.getString("placeOfBirth");
    }

    public String getCitizenShip() {
        return json.getString("citizenship");
    }

    public String getOCA() {
        try {
            return json.getString("OCA");
        } catch (Exception ex) {
            return null;
        }
    }

    public String getSex() {
        return json.getString("sex");
    }

    public String getRace() {
        return json.getString("race");
    }

    public String getHeight() {
        return json.getString("height");
    }

    public String getWight() {
        return json.getString("wight");
    }

    public String getEyes() {
        return json.getString("eyes");
    }

    public String getHair() {
        return json.getString("hair");
    }

    public String getSSN() {
        try {
            return json.getString("ssn");
        } catch (Exception ex) {
            return null;
        }
    }
}
