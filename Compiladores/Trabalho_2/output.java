public class output {
public static float add(float a, float b) {
return a + b;
}
public static void main(String[] args) {
float x;
float y;
boolean flag;
x = 5.0f;
y = 1.0f;
flag = true;
while(flag == true){
System.out.println(x);
x = add(x,y);
flag = false;
}
if(x > 5.0f){
System.out.println(x);
}
}
}
