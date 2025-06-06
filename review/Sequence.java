public class Sequence {
      private Object[] objects; //array of Object
      private int next = 0;
      public Sequence(int size) 
            { 
               objects = new Object[size];   
            }
      
      public void add(Object x) 
            { //x dont have length
               if (next < objects.length) 
               { 
                  objects[next] = x; 
                  next++;
               } 
            }

  
   }