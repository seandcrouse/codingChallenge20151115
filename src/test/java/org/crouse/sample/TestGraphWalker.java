package org.crouse.sample;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import org.crouse.sample.GNode;
import org.crouse.sample.GraphWalker;
import org.crouse.sample.utils.GraphBuilder;
import org.crouse.sample.utils.GraphFileParser;

public class TestGraphWalker {

   private TestGraphWalker() {
   }

   /**
    * Parse the file passed as the first argument, and print out the {@link ArrayList}
    * of {@link GNodes}.
    * 
    * @param args
    *           First array element is the path of the file to parse. All other
    *           array elements are ignored.
    * 
    * @throws URISyntaxException
    * @throws IOException
    */
   public static void main(final String[] args) throws URISyntaxException, IOException {
      if (args.length < 1) {
         System.out.println("Please provide a filepath.");
         return; 
      }
      
      List<String> contents = GraphFileParser.parse(args[0]);

      // Build the graph
      GNode root = GraphBuilder.buildGraph(contents);

      // Walk the graph, and print each GNode on a separate line
      for (GNode node : GraphWalker.INSTANCE.walkGraph(root)) {
         System.out.println(node);
      }
   }

}
