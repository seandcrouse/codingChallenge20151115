package org.crouse.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

/**
 * Singleton graph walker.
 * 
 * @author Sean Crouse
 *
 */
public enum GraphWalker {
   INSTANCE; // Singleton

   /**
    * Explicit empty constructor prevents instantiation
    */
   private GraphWalker() {
   }

   /**
    * Returns an {@link ArrayList} of {@link GNode} objects. The elements are
    * ordered by a depth-first search of the graph, with the first element being
    * the {@code node} argument. Duplicate nodes are filtered from the graph,
    * after their first occurrence in the depth-first search.
    * 
    * @param node
    *           Root node of the graph
    * 
    * @return {@link ArrayList} of {@link GNode} objects.
    */
   public ArrayList<GNode> walkGraph(final GNode node) {
      // This method signature is slightly different than the one specified for
      // the coding challenge. Since the introduction of generics in Java 5,
      // there is no reason to use raw types. The final keyword on the
      // parameter is just good Java coding practice.

      // Maintain insertion-order, and prevent duplicates
      final LinkedHashSet<GNode> graph = new LinkedHashSet<>();
      graph.add(node);

      // Recursively walk the graph for each child node
      // TODO Investigate performance, and consider using parallelism 
      Arrays.stream(node.getChildren()).forEach(child -> graph.addAll(walkGraph(child)));

      // Convert to an ArrayList with a defensive copy
      return new ArrayList<GNode>(graph);
   }
   
   /**
    * Get an {@link ArrayList} of all paths extending from the given node.
    * 
    * @param node Node to get all the paths for
    * 
    * @return  {@link ArrayList} of all paths extending from the given node
    */
   public ArrayList<ArrayList<GNode>> paths(final GNode node) {
      // Leaf node - return a new ArrayList of an ArrayList, containing the leaf node
      if (node.getChildren().length < 1) {
         // Singleton list containing just the node
         return getSingleton(node);
      }

      // Insert node into every child path, and collect them in a single list
      final ArrayList<ArrayList<GNode>> paths = new ArrayList<>();
      for (GNode child : node.getChildren()){
         for (ArrayList<GNode> childPaths : paths(child)){
            childPaths.add(0, node); // Insert node into every child path
            paths.add(childPaths); // Collect child paths into a single list
         }
      }
      
      return paths;
   }
   
   /**
    * Get singleton of {@link GNode}, wrapped in an {@link ArrayList}
    * 
    * @param node singleton
    * 
    * @return singleton wrapped in an {@link ArrayList}
    */
   private ArrayList<ArrayList<GNode>> getSingleton(final GNode node) {
      ArrayList<GNode> singleton = new ArrayList<>();
      singleton.add(node);
      
      // Wrap single path in a list of paths
      ArrayList<ArrayList<GNode>> result = new ArrayList<>();
      result.add(singleton);
      
      return result;
   }
   
}
