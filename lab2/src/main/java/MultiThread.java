
import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.commons.httpclient.methods.GetMethod;
import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class MultiThread {

    public static void main(String[] args) {

        // Creating MultiThreadedHttpConnectionManager
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

        // Passing it to the HttpClient.
        HttpClient httpClient = new HttpClient(connectionManager);
        String[] urisToGet = {"https://evil-legacy-service.herokuapp.com/api/v101/categories/",
                "https://evil-legacy-service.herokuapp.com/api/v101/orders/?start=2000-02-22&end=2010-02-24"};

        // create a thread for each URI
        for (int i = 0; i < urisToGet.length; i++) {
            GetMethod get = new GetMethod(urisToGet[i]);
            get.addRequestHeader("x-api-key","55193451-1409-4729-9cd4-7c65d63b8e76");
            get.addRequestHeader("ACCEPT","text/csv");
            new CreateThread(httpClient, get, "Thread-" + i).start();
        }
    }

    static class CreateThread extends Thread {
        private final HttpClient httpClient;
        private final GetMethod method;

        public CreateThread(HttpClient httpClient, GetMethod method, String name) {
            super(name);
            this.httpClient = httpClient;
            this.method = method;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " - about to get something from "
                        + method.getURI());

                // Execute the method.
                int statusCode = httpClient.executeMethod(method);

                if (statusCode != HttpStatus.SC_OK) {
                    System.err.println("Method failed: " + method.getStatusLine());
                }

                // Reading the status body.
                StatusLine statusLine = method.getStatusLine();
                System.out.println(Thread.currentThread().getName() + " " + statusLine);
            } catch (HttpException e) {
                System.err.println("Fatal protocol violation: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Fatal transport error: " + e.getMessage());
            } finally {
                // Release the connection.
                method.releaseConnection();
            }
        }

    }

}