package il.ac.technion.cs.sd.app;

import java.util.OptionalInt;

/**
 * This class will only be instantiated after
 * {@link il.ac.technion.cs.sd.app.GradesInitializer#setup(java.lang.String) has been called}.
 */
public class GradesReader {
  /** Returns the grade associated with the ID, or empty. */
  public OptionalInt getGrade(String id) {
    throw new RuntimeException("Not implemented");
  }
}
