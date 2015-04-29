package quannk.thesis.core;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import quannk.thesis.constraint.CNFClause;
import quannk.thesis.constraint.Clause;
import quannk.thesis.constraint.PathConstraint;
import quannk.thesis.constraint.Term;

public class JPFOutput {
  public Vector<PathConstraint> pathConstraints;

  public JPFOutput(String jpfOutput) {
    pathConstraints = extractPathConditions(jpfOutput);
  }

  public CNFClause getErrorCheckConstraint() {
    CNFClause result = new CNFClause();
    for (PathConstraint path : pathConstraints) {
      result.clauses.add(new Clause("!(" + path.preConditions.toString() + ")"));
    }
    return result;
  }

  public String getDeclares() {
    Set<String> declares = new HashSet<String>();
    Set<Term> terms = new HashSet<Term>();
    for (PathConstraint path : pathConstraints) {
      terms.addAll(path.getTerms());
    }
    for (Term term : terms) {
      declares.add(term.getSMTDeclare());
    }
    StringBuilder sb = new StringBuilder();
    for (String s : declares)
      sb.append(s + "\n");
    return sb.toString();
  }

  private static Vector<PathConstraint> extractPathConditions(String jpfOutput) {
    String[] lines = jpfOutput.replaceAll("\n\r", "\n").replaceAll("\r\n", "\n").replaceAll("CONST_", "").split("\n");

    Vector<PathConstraint> pathConditions = new Vector<PathConstraint>();

    PathConstraint currentPath = null;
    for (int iLine = 0; iLine < lines.length; iLine++) {
      if (lines[iLine].startsWith("CHECK_O")) {
        String checkOutString = lines[iLine].trim();
        if (currentPath == null)
          currentPath = new PathConstraint();
        currentPath.postConditions.clauses.add(new Clause(checkOutString));
      } else if (lines[iLine].startsWith("path constraint # = ")) {
        int constraintNumber = Integer.parseInt(lines[iLine].substring("path constraint # = ".length()));
        for (int iConstrain = 0; iConstrain < constraintNumber; iConstrain++) {
          iLine++;
          String constrainString = lines[iLine].replaceAll(" &&", "").replaceAll("%NonLinInteger% ", "");
          currentPath.preConditions.clauses.add(new Clause(constrainString));
        }
        pathConditions.add(currentPath);
        currentPath = null;
      } else {

      }
    }
    return pathConditions;
  }
}