package org.crouse.sample.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
      List<String> contents = null;
      
      // Try-with-resources automatically closes the file reader
      try (Stream<String> lines = Files.lines(path)) {
         contents = lines.collect(Collectors.toList());
      }

      return (contents != null) ? contents : Collections.emptyList();
   }
}
