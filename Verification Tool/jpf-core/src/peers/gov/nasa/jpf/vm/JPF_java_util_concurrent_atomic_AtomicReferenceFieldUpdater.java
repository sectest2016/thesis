//
// Copyright (C) 2007 United States Government as represented by the
// Administrator of the National Aeronautics and Space Administration
// (NASA).  All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//

package gov.nasa.jpf.vm;

import gov.nasa.jpf.annotation.MJI;


/**
 * a full peer for the AtomicReferenceFieldUpdater
 */
public class JPF_java_util_concurrent_atomic_AtomicReferenceFieldUpdater extends AtomicFieldUpdater {

  @MJI
  public void $init__Ljava_lang_Class_2Ljava_lang_Class_2Ljava_lang_String_2__V (MJIEnv env, int objRef,
                                 int tClsObjRef, int fClsObjRef, int fNameRef) {

    // direct Object subclass, so we don't have to call a super ctor

    ClassInfo ci = env.getReferredClassInfo(tClsObjRef);
    String fname = env.getStringObject(fNameRef);
    FieldInfo fi = ci.getInstanceField(fname);

    ClassInfo fci = fi.getTypeClassInfo();

    ClassInfo fciCheck = env.getReferredClassInfo( fClsObjRef);
    if (!fci.isInstanceOf(fciCheck)) {
      // that's also just an approximation, but we need to check
      env.throwException("java.lang.RuntimeException", "wrong field type");
    }

    int fidx = fi.getFieldIndex();
    env.setIntField(objRef, "fieldId", fidx);
  }

  @MJI
  public boolean compareAndSet__Ljava_lang_Object_2Ljava_lang_Object_2Ljava_lang_Object_2__Z
                                  (MJIEnv env, int objRef, int tRef, int fExpect, int fUpdate){

    if (tRef == MJIEnv.NULL){
      env.throwException("java.lang.NullPointerException", "AtomicFieldUpdater called on null object");
      return false;
    }
    
    ThreadInfo ti = env.getThreadInfo();
    ElementInfo ei = ti.getModifiableElementInfo(tRef);
    FieldInfo fi = getFieldInfo( ti.getElementInfo(objRef), ei);

    if (reschedulesAccess(ti, ei, fi)){
      env.repeatInvocation();
      return false;
    }

    int v = ei.getReferenceField(fi);
    if (v == fExpect) {
      ei.setReferenceField(fi, fUpdate);
      return true;
    } else {
      return false;
    }
  }

  @MJI
  public boolean weakCompareAndSet__Ljava_lang_Object_2Ljava_lang_Object_2Ljava_lang_Object_2__Z
  (MJIEnv env, int objRef, int tRef, int fExpect, int fUpdate){
    return(compareAndSet__Ljava_lang_Object_2Ljava_lang_Object_2Ljava_lang_Object_2__Z(env, objRef, tRef, fExpect, fUpdate));
  }

  @MJI
  public void set__Ljava_lang_Object_2Ljava_lang_Object_2__V (MJIEnv env, int objRef, int tRef, int fNewValue){

    if (tRef == MJIEnv.NULL){
      env.throwException("java.lang.NullPointerException", "AtomicFieldUpdater called on null object");
      return;
    }
    
    ThreadInfo ti = env.getThreadInfo();
    ElementInfo ei = ti.getModifiableElementInfo(tRef);
    FieldInfo fi = getFieldInfo( ti.getElementInfo(objRef), ei);

    if (reschedulesAccess(ti, ei, fi)){
      env.repeatInvocation();
      return;
    }

    ei.setReferenceField(fi, fNewValue);
  }

  @MJI
  public void lazySet__Ljava_lang_Object_2Ljava_lang_Object_2__V (MJIEnv env, int objRef, int tRef, int fNewValue){
     set__Ljava_lang_Object_2Ljava_lang_Object_2__V(env, objRef, tRef, fNewValue);
  }

  @MJI
  public int get__Ljava_lang_Object_2__Ljava_lang_Object_2 (MJIEnv env, int objRef, int tRef){
    if (tRef == MJIEnv.NULL){
      env.throwException("java.lang.NullPointerException", "AtomicFieldUpdater called on null object");
      return MJIEnv.NULL;
    }
    
    ThreadInfo ti = env.getThreadInfo();
    ElementInfo ei = ti.getElementInfo(tRef);
    FieldInfo fi = getFieldInfo( ti.getElementInfo(objRef), ei);

    if (reschedulesAccess(ti, ei, fi)){
      env.repeatInvocation();
      return MJIEnv.NULL;
    }

    return ei.getReferenceField(fi);
  }

  @MJI
  public int getAndSet__Ljava_lang_Object_2Ljava_lang_Object_2__Ljava_lang_Object_2 (MJIEnv env, int objRef, int tRef, int fNewValue){
    if (tRef == MJIEnv.NULL){
      env.throwException("java.lang.NullPointerException", "AtomicFieldUpdater called on null object");
      return MJIEnv.NULL;
    }
    
    ThreadInfo ti = env.getThreadInfo();
    ElementInfo ei = ti.getElementInfo(tRef);
    FieldInfo fi = getFieldInfo( ti.getElementInfo(objRef), ei);

    if (reschedulesAccess(ti, ei, fi)){
      env.repeatInvocation();
      return MJIEnv.NULL;
    }
    
    int result = ei.getReferenceField(fi);
    ei.setReferenceField(fi, fNewValue);

    return result;
  }
}
