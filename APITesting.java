import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class APITests {
    private final HttpClient httpClient;
    private final String baseUrl;

    // Main method to execute the API tests
    public static void main(String[] args) throws Exception {
        // Define the base URL of the API`
        String baseUrl = "https://reqres.in/api";
        // Create an instance of APITests class with the base URL
        APITests apiTests = new APITests(baseUrl);
        // Execute the test methods
        apiTests.testListUsers();
        apiTests.testCreateUser();
        
    }

    // Constructor to initialize HttpClient and base URL
    public APITests(String baseUrl) {
        // Create a new instance of HttpClient
        this.httpClient = HttpClient.newBuilder().build();
        // Set the base URL for the API
        this.baseUrl = baseUrl;
    }

    // Method to test listing users
    public void testListUsers() throws Exception {
        // Define the endpoint for listing users
        String endpoint = "/users";
        // Create a GET request for the specified endpoint
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + endpoint))
                .build();

        // Send the request and obtain the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 200) {
            // Print success message and response body if status code is 200
            System.out.println("List Users Request Successful");
            System.out.println("List Users Response:");
            System.out.println(response.body());
        } else {
            // Print failure message and response body if status code is not 200
            System.out.println("Failed to list users");
            System.out.println("Response: " + response.body());
        }
    }

    // Method to test creating a user
    public void testCreateUser() throws Exception {
        // Define the endpoint for creating a user
        String endpoint = "/users";
        // Create a POST request for the specified endpoint with JSON payload
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"name\":\"Pradeep Goswami\",\"job\":\"Software Engineer\"}"))
                .uri(URI.create(baseUrl + endpoint))
                .header("Content-Type", "application/json")
                .build();

        // Send the request and obtain the response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check the response status code
        if (response.statusCode() == 201) {
            // Print success message and response body if status code is 201
            System.out.println("Create User Request Successful");
            System.out.println("Create User Response:");
            System.out.println(response.body());
        } else {
            // Print failure message and response body if status code is not 201
            System.out.println("Failed to create user");
            System.out.println("Response: " + response.body());
        }
    }


    
    
}
