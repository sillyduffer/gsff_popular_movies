package com.example.ealan.getswollforfilm.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;


public class NetworkUtils {

    public static String getJSONResponseFromUrl(String inputUrl) throws IOException {


        URL url = createUrl(inputUrl);

        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }


    private static URL createUrl(String s) {
        URL url = null;
        try {
            if (s != null) {
                url = new URL(s);
                return url;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


}
