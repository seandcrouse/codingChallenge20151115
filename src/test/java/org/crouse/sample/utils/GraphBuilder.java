package org.crouse.sample.utils;

import java.util.List;
import java.util.Stack;

import org.crouse.sample.GNode;
import org.crouse.sample.GNodeImpl.NodeBuilder;

public final class GraphBuilder {

   private GraphBuilder() {
      // TODO Auto-generated constructor stub
   }

   /**
    * Build a graph of {@link GNodes} from the {@link List} of {@link Strings}, with tab indentation
    * representing the nesting structure.
    * 
    * @param contents
    *           {@link List} of {@link Strings} from which to build {@link GNodes}
    * 
    * @return Root node
    */
   public static GNode buildGraph(final List<String> contents) {
      Stack<NodeBuilder> nodeBuilderStack = new Stack<>();

      int level = -1; // Start at -1 to ensure that 0 indents results in the root being pushed to the stack
      GNode currentNode = null;
      
      // For each line, count the number of indents.  
      // 1. If the number of indents is greater than the current nesting level,
      //    then we just started a new list of child nodes.
      // 2. If the number of indents is the same, then the previous line is a 
      //    node with no children.  
      // 3. If the number of indents is less than the current nesting level, 
      //    then one or more nodes have had all their lists of children 
      //    completed.
      for (String nodeStr : contents) {
         final int indents = countIndents(nodeStr);
         // Move down a level
         if (indents > level) {
            level = indents;
         }
         // Same level - previous line is a leaf node - build GNode for previous
         // line
         else if (indents == level) {
            currentNode = buildNode(nodeBuilderStack);
         }
         // Go up one or more levels - build GNode for each level that it goes
         // up
         else {
            while (level - indents > -1) {
               currentNode = buildNode(nodeBuilderStack);
               level--;
            }
         }

         // Always push the current line onto the stack
         NodeBuilder builder = new NodeBuilder().setName(nodeStr.substring(indents));
         nodeBuilderStack.push(builder);
      }

      // Finished processing all lines.  Need to build all the remaining nodes 
      // in the stack
      while (!nodeBuilderStack.empty()) {
         currentNode = buildNode(nodeBuilderStack);
      }

      return currentNode; // Root node
   }

   /**
    * Count the number of indents.  Indicates nesting depth.
    * 
    * @param node {@link String} representing the node, with leading indents
    * 
    * @return  Number of indentations, i.e. nesting level
    */
   private static int countIndents(final String node) {
      int count = 0;
      String trimmed = node;
      
      // Cannot use replaceAll, and take a diff of the String lengths.
      // The replaceAll method will remove all tab characters, and not just 
      // the leading ones.
      while (trimmed.charAt(0) == '\t') {
         count++;
         trimmed = trimmed.replaceFirst("\t", "");
      }
      return count;
   }

   /**
    * Build the {@link GNode} and add it to the parent node's list of children
    * 
    * @param nodeBuilderStack {@link Stack} of {@link NodeBuilder} objects, 
    *                         which hold the node data until all children have 
    *                         been built.
    * 
    * @return  New node
    */
   private static GNode buildNode(final Stack<NodeBuilder> nodeBuilderStack) {
      final GNode gnode = nodeBuilderStack.pop().build();

      // Add node to children list of parent node
      if (!nodeBuilderStack.empty()) {
         nodeBuilderStack.peek().addChild(gnode);
      }

      return gnode;
   }
}
