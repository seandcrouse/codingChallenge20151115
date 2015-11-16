package org.crouse.sample.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GraphFileParser {

   /**
    * Parse the file into a {@link List} of {@link String}s, with each {@link String} being a line in
    * the file
    * 
    * @param filePath
    *           {@link String} representation of the path of the file to be parsed
    * 
    * @return {@link List} of {@link String}s, with each String being a line in the file
    * 
    * @throws IOException
    */
   public static List<String> parse(final String filePath) throws IOException {
      Path path = new File(filePath).toPath();

      // Store the file as a list of strings
      List<String> contents = new ArrayList<>();

      // Try-with-resources automatically closes the file reader
      try (BufferedReader reader = Files.newBufferedReader(path)) {
         String line = null;
         while ((line = reader.readLine()) != null) {
            contents.add(line);
         }
      }

      return contents;
   }
}
