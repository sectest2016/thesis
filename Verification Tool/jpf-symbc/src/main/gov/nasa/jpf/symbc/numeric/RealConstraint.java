//
//Copyright (C) 2006 United States Government as represented by the
//Administrator of the National Aeronautics and Space Administration
//(NASA).  All Rights Reserved.
//
//This software is distributed under the NASA Open Source Agreement
//(NOSA), version 1.3.  The NOSA has been approved by the Open Source
//Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
//directory tree for the complete NOSA document.
//
//THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
//KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
//LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
//SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
//A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
//THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
//DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//

package gov.nasa.jpf.symbc.numeric;

public class RealConstraint extends Constraint {
    public RealConstraint(RealExpression l, Comparator c, RealExpression r) {
      super(l, c, r);
    }
    
    public RealConstraint(RealConstraint rc) {
        super(rc.getLeft(), rc.getComparator(), rc.getRight());
      }

    public String toString() {
      return /* "%Real% " + */super.toString();
    }
    
    public RealExpression getLeft() {
        return (RealExpression) super.getLeft();
    }
    
    public RealExpression getRight() {
        return (RealExpression) super.getRight();
    }
    
    @Override
    public RealConstraint not() {
        return new RealConstraint(getLeft(), getComparator().not(), getRight());
    }    
}
