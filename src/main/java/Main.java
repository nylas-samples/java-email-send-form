//Import Java Utilities
import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;

//Import Nylas Packages
import com.nylas.NylasClient;
import com.nylas.models.*;

//Import DotEnv to handle .env files
import io.github.cdimascio.dotenv.Dotenv;

//Import to handle the SparkJava Web Framework
import static spark.Spark.*;

public class Main {

    static String SendEmail(String name, String email, String comments){
        Dotenv dotenv = Dotenv.load();
        // Initialize the Nylas client
        NylasClient nylas = new NylasClient.Builder(dotenv.get("NYLAS_API_KEY")).apiUri(dotenv.get("NYLAS_API_URI")).build();

        List<EmailName> emailNames = new ArrayList<>();
        emailNames.add(new EmailName(email, name));

        try {
            SendMessageRequest requestBody = new SendMessageRequest.Builder(emailNames).
                    subject("Complaint from " + name + " - " + email).
                    body(comments).build();

            nylas.messages().send(dotenv.get("NYLAS_GRANT_ID"), requestBody);
            //Print a friendly message
            return ("<!DOCTYPE html>" +
                    "<html><head>" +
                    "<meta http-equiv=\"refresh\" content=\"5; URL=/feedback\">" +
                    "<script src=\"https://cdn.tailwindcss.com\"></script>" +
                    "</head>" +
                    "<body>" +
                    "<div class=\"bg-neutral-200 border-neutral-600 border-b p-4 m-4 rounded\">" +
                    "<h2 class=\"font-semibold\">Thanks! We received your feedback.</h1>" +
                    "<br><p class=\"font-semibold\">You will be automatically redirected." +
                    "</div>" +
                    "</body>" +
                    "<html>");
        } catch (Exception e) {
            //Something went wrong, display the error
            return (e.getMessage());
        }
    }

    public static void main(String[] args) throws NylasSdkTimeoutError, NylasApiError{
        get("/feedback", (request, response) ->
                "<!DOCTYPE html>" +
                        "<html>" +
                        "<head>" +
                        "<title>Complaint Form</title>" +
                        "<script src=\"https://cdn.tailwindcss.com\"></script>" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"bg-neutral-200 border-neutral-600 border-b p-4 m-4 rounded\">" +
                        "<h1 class=\"font-black\">If you're not happy with the provided service, " +
                        "please let us know</h1>" +
                        "<br>" +
                        "<form method=\"post\">" +
                        "<label for=\"name\" class=\"font-bold\">Name  </label>" +
                        "<input type=\"text\" name=\"name\" placeholder=\"Devrel\"></input>" +
                        "<br><br>" +
                        "<label for=\"email\" class=\"font-bold\">Email  </label>" +
                        "<input type=\"text\" name=\"email\" placeholder=\"devrel@nylas.com\"></input>" +
                        "<br><br>" +
                        "<p class=\"font-bold\">Comments</p>" +
                        "<textarea name=\"comments\"\n" +
                        "        placeholder=\"Your comments\"\n" +
                        "        rows=5\n" +
                        "        cols=50></textarea>" +
                        "<br><br>" +
                        "<button type=\"submit\" class=\"bg-blue-500 hover:bg-blue-700 " +
                        "text-white font-bold py-2 px-4 rounded-full\">Submit</button>" +
                        "</form>" +
                        "</div>" +
                        "</body>" +
                        "</html>"
        );

        post("/feedback", (request, response) ->
                SendEmail(request.queryParams("name"), request.queryParams("email"), request.queryParams("comments"))
        );
    }
}
