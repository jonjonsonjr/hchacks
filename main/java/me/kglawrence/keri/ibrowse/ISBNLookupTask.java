/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package me.kglawrence.keri.ibrowse;

import android.os.AsyncTask;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volumes;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.NumberFormat;

/**
 * A sample application that demonstrates how Google Books Client Library for
 * Java can be used to query Google Books. It accepts queries in the command
 * line, and prints the results to the console.
 * <p/>
 * $ java com.google.sample.books.ISBNLookupTask [--author|--isbn|--title] "<query>"
 * <p/>
 * Please start by reviewing the Google Books API documentation at:
 * http://code.google.com/apis/books/docs/getting_started.html
 */
public class ISBNLookupTask extends AsyncTask<String, Void, Void> {

  /**
   * Be sure to specify the name of your application. If the application name is {@code null} or
   * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
   */
  private static final String APPLICATION_NAME = "iBrowse";

  private static void queryGoogleBooks(JsonFactory jsonFactory, String query) throws Exception {
    String API_KEY = "AIzaSyBUr6gqSA6w6hBBAbPjEDUoB7qqDwzntus";

    final Books books = new Books.Builder(new NetHttpTransport(), jsonFactory, null)
        .setApplicationName(APPLICATION_NAME)
        .setGoogleClientRequestInitializer(new BooksRequestInitializer(API_KEY))
        .build();
    // Set query string and filter only Google eBooks.
    System.out.println("Query: [" + query + "]");
    List volumesList = books.volumes().list(query);
    volumesList.setMaxResults(1L);

    // Execute the query.
    Volumes volumes = volumesList.execute();
    if (volumes.getTotalItems() == 0 || volumes.getItems() == null) {
      System.out.println("No matches found.");
      return;
    }

    // Output results.
    for (Volume volume : volumes.getItems()) {
      Volume.VolumeInfo volumeInfo = volume.getVolumeInfo();
      // Title.
      System.out.println("Title: " + volumeInfo.getTitle());
      // Author(s).
      java.util.List<String> authors = volumeInfo.getAuthors();
      if (authors != null && !authors.isEmpty()) {
        System.out.print("Author(s): ");
        for (int i = 0; i < authors.size(); ++i) {
          System.out.print(authors.get(i));
          if (i < authors.size() - 1) {
            System.out.print(", ");
          }
        }
        System.out.println();
      }
      // Thumbnail.
      if (volumeInfo.getImageLinks() != null && volumeInfo.getImageLinks().getThumbnail() != null) {
        System.out.println(volumeInfo.getImageLinks().getThumbnail());
      }
    }
  }

  @Override
  protected Void doInBackground(String... params) {
    String isbn = params[0];
    JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    String query = "isbn:" + isbn;
    try {
      queryGoogleBooks(jsonFactory, query);
      // Success!
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }

    return null;
  }
}