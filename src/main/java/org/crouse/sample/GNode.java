package org.crouse.sample;

/**
 * Defines an acyclic graph with no {@code null}s.
 * 
 * @author Sean Crouse
 *
 */
public interface GNode {
   /**
    * Get the name of the node.
    * 
    * Does not return {@code null}, but can return an empty {@link String}.
    * 
    * @return Name
    */
   public String getName();

   /**
    * Get the child nodes.
    * 
    * Does not return  {@code null}, but can return a 0-length array. Returned array does
    * not contain {@code null}.
    * 
    * @return Child nodes
    */
   public GNode[] getChildren();
}
