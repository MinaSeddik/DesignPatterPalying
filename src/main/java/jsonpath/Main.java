package jsonpath;

//import org.springframework.data.web.JsonPath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.util.List;

public class Main {


    private static String jsonDataSourceString = "{\n" +
            "  \"timestamp\" : \"2023-03-04T20:47:57.762110700Z\",\n" +
            "  \"status\" : \"400 BAD_REQUEST\",\n" +
            "  \"error\" : {\n" +
            "    \"code\" : 1,\n" +
            "    \"message\" : \"Fields Validation\"\n" +
            "  },\n" +
            "  \"path\" : \"\",\n" +
            "  \"traceId\" : \"350d7755-8867-4dab-b35e-a3a43461f62f\",\n" +
            "  \"validation\" : [ {\n" +
            "    \"fieldName\" : \"placeOfBirth\",\n" +
            "    \"errorMessage\" : \"Place of Birth is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"eyes\",\n" +
            "    \"errorMessage\" : \"Eyes Color is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"hair\",\n" +
            "    \"errorMessage\" : \"Hair Color is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"wight\",\n" +
            "    \"errorMessage\" : \"Wight is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"completionDate\",\n" +
            "    \"errorMessage\" : \"Completion Date is required with format MM/dd/yyyy\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"fingerprints\",\n" +
            "    \"errorMessage\" : \"Applicant Fingerprints are required\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"applicantName\",\n" +
            "    \"errorMessage\" : \"Applicant Name is required\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"residenceOfPersonFingerPrinted\",\n" +
            "    \"errorMessage\" : \"Applicant Address is required\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"signatureImage\",\n" +
            "    \"errorMessage\" : \"Applicant signature is required\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"ssn\",\n" +
            "    \"errorMessage\" : \"SSN is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"sex\",\n" +
            "    \"errorMessage\" : \"Sex is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"dateOfBirth\",\n" +
            "    \"errorMessage\" : \"Date of Birth is required with format MM/dd/yyyy\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"race\",\n" +
            "    \"errorMessage\" : \"Race is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"citizenship\",\n" +
            "    \"errorMessage\" : \"Citizenship is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"height\",\n" +
            "    \"errorMessage\" : \"Height is a required field\"\n" +
            "  }, {\n" +
            "    \"fieldName\" : \"application\",\n" +
            "    \"errorMessage\" : \"Invalid Application Data, Please provide a valid application data\"\n" +
            "  } ]\n" +
            "}";
    public static void main(String[] args) {

        DocumentContext jsonContext = JsonPath.parse(jsonDataSourceString);
        JSONArray jsonpathCreatorName = jsonContext.read("$.validation[*].fieldName");

        JSONArray x1 = jsonContext.read("$.validation[?(@.fieldName=='eyes')]");
        JSONArray x2 = jsonContext.read("$.validation[?(@.fieldName=='eyes')].errorMessage");

        System.out.println(jsonpathCreatorName);
        System.out.println(x1);
        System.out.println(x2);

    }
}
