package com.Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class Example {

	public static void main(String[] args) throws Exception, SecurityException ,CloneNotSupportedException{
//		Samosa samosa1 = Samosa.getSamosa();
//		System.out.println(samosa1.hashCode());
//		
//		Samosa samosa2 = Samosa.getSamosa();
//		System.out.println(samosa2.hashCode());
//		
//		Samosa sam1 = Samosa.getsam12();
//		System.out.println(sam1.hashCode());
//		Samosa sam2 = Samosa.getsam12();
//		System.out.println(sam2.hashCode());
//		
//		
//		System.out.println(Samosa.synchronizedSamosa().hashCode());
		
		
		
		
		
		
		
						// Breaking of singleton Design pattern
		/*
		 * 1. API  [ Reflection API is use to break singleton pattern
		 * 		 Solution to except from reflrvyion =>
		 * 				if object is there ==> throw exception form inside constri=uctor
		 * 				or use enum
		 * 
		 * 
		 * 2. Deserialization :
		 * 	solution : implementing readResolve
		 * 
		 * 3. Cloning
		 *     solution just return the object in overRide method clone
		 * 
		 */
		
		
		
		
		
		
		
//		Samosa s1 = Samosa.getSamosa();
//		System.out.println(s1.hashCode());
//		//    s1.test();   this method is from enum class.
//		
//		Constructor<Samosa> constructor = Samosa.class.getDeclaredConstructor();
//		constructor.setAccessible(true);
//		Samosa s2 = constructor.newInstance();
//		System.out.println(s2.hashCode());
		
		
		
		
		
		 
//		Samosa s1 = Samosa.getSamosa();
//		System.out.println(s1.hashCode());
//		ObjectOutputStream Oos = new ObjectOutputStream(new FileOutputStream("abc.ob"));
//		Oos.writeObject(s1);
//		
//		System.out.println("Serilization done....");
//		
//		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc.ob"));
//		Samosa s2 = (Samosa) ois.readObject();
//		System.out.println(s2.hashCode());
		
		
		
		
		
		Samosa s1 = Samosa.getSamosa();
		System.out.println(s1.hashCode());
		
		Samosa s2 = (Samosa)s1.clone();
		System.out.println(s2.hashCode());
		
	}
}
