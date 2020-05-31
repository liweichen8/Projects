//===================================================================================								
//Program name: Geometric Assignment							
//Programmer:   David Chen							
//Date: 2017.5.23							
//Language: Java(Eclipse neon.3)								
//===================================================================================
//Program Description: This program is very useful to deal with the data list of geometric objects. Throught using
// object-orient programming, the user can efficiently creates a new shape and store in the database with unique identity.
// The geometric shapes includes common shapes such as square and rectangle and three d shapes such as cube. So it is 
// very convinenent to make a list of shapes with parameters such as area. It allows user to check the shapes in database
// and find a particular object by a unique ID.
//===================================================================================	
//Input -	Entering the number to run the program, there is a menu that each number refers to particular order.(Such as creating a new shape)
//output-   output the attributes of each object once the user created and the number of each shape compare to the total number of shapes.
//processing-  Using object-orient programming to access each class of shape and calculate the particular attributes.
//===================================================================================
//List of Variables -   Let GeometricList as an arraylist to store the geometric objects (type: object)
//List of Variables -   Let numObject as an gobal variable as the index of GeometricList (type: int)
//===================================================================================		


import java.util.ArrayList;
import java.io.*;

public class AssignmentDavidChen {
	
	static ArrayList<Geometric> GeometricList = new ArrayList<Geometric>(); //object type of arraylist
	static int numObject = 0; //index of arraylist

	/** main method:
	 * procedural method
	 * This method automatically run and is used to call other methods defined in the class
	 * an local variable called 'option' is to determine what the user's want to do next and there will be a menu to list the options
	 * then, the user will get into different method in the switch loop
	 * 
	 * List of Local Variable
	 * option - an int variable that user can input a new value to it refers to the order on the menu
	 * 
	 * input() - the method to return a value giving to the variable such as 'option'.
	 * input() - the user choose the option base on his or her own idea.
	 * switch - a selection loop that each case of options runs different function such as creating a new shape
	 * switch - each case connects to different method such as Output() to show the number of shapes.
	 * 
	 * The variable 'option' gets the value from the user input, it connects to 'input' method so that the code looks clear.
	 * The program would not end until the user entered the number of '5', just like what the condition in the 'while' loop
	 * (option != 5), so that users can changer their mind to do other option.
	 * 
	 * When the user choose to create a new shape, the program continues to make two options that either default or custom shape,
	 * you just need to enter the number '1' or '2', typing the word will be easy to make mistake.
	 * 
	 * @param args <type int>
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/*
		Cuboid myCuboid = new Cuboid();
		System.out.println(myCuboid.toString());
		System.out.println();
		System.out.println("The number of geometric objects is " + Geometric.getnumShape());
		System.out.println("The number of 3D objects is " + ThreeDShape.getnumThreeD());
		*/
		int option = 0; //initialize as '0'
		
		System.out.println("Welcome to the shape's constructor");
		System.out.println("This program allows you to create and store various kinds of shape's object");
		System.out.println("(Up to 20 or more)");
		System.out.println("Your just needs to choose the option base on the menu is given");
		System.out.println("You can choose to list the number of each shape that is already created");
		System.out.println("Once a shape is created, the attributes such as area will be performed");
		System.out.println("Each shape has unique ID that can be used to search a particular shape");
		System.out.println("The program is easy to follow, so enjoy it.");
		
		while(option != 5){
		System.out.println();
		System.out.println("Please enter the number of options to choose what you want to do.");
		System.out.println("enter '1': To create an object of geometric");
		System.out.println("enter '2': To show the number of objects you already created");
		System.out.println("enter '3': To find a particular geometric object in the database");
		System.out.println("enter '4': List all the objects in the database");
		System.out.println("enter '5': exit");
		option = (int) input("Your option(1-5): ");
		System.out.println();
		
		switch(option){
		case 1:
			System.out.println("(1)default or (2)custom");
			option = (int) input("please enter the number: ");	
			switch(option){
			case 1: 
				Creation1(); //method to create a default shapes
				break;
			case 2:
				Creation2(); //method to create a custom shape
				break;
			}
			break;
		case 2:
			Output(); //output the number of each shape and total number of all shape
			break;
		case 3:
			Finding(); //allow the user to find a particular shape through a unique key
			break;
		case 4:
			List();//list all the database includes shape's name and attributes
			break;
		case 5: 
			break;
		}
		if(option == 5)//end the program
			break;
		}
		System.out.println("The program end, thank you for playing.");
	}//end of main method

	/**input method:
	 * functional method
	 * allow the user to input a number and return a value
	 * The representation of 'num' depends on what method call it. It can be the attribute of some shape such as
	 * the width of the square. It also is the number represents the option in the menu.
	 * 
	 * 
	 * List of Local Variable
	 * num - a double type of variable for user to input the value, for example, the width of the square
	 * num - if the number input is used to determine the option, it is unnecessary to use double type 
	 * num - sometimes it will be a casting (int) in front of input in other method
	 * words - the parameter that in Creation2() method, for example, requires user to input the width or height of 
	 * 		   some shapes, so the interpretation such as 'please enter the height of rhombus' will sent to this string 
	 *         'words' to make user clear to know what to do
	 *         
	 * @param words
	 * @return num
	 * @throws IOException
	 */
	public static double input(String words) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double num;
		System.out.print(words);
		num = Double.parseDouble(br.readLine());
		//System.out.println();
		return num;
	}
	
	/**Menu method
	 * Each number relates to particular shapes that the user choose to create
	 * 
	 * @param none
	 * @return void
	 */
	public static void Menu(){
		System.out.println("Please enter the order of number (such as '1') to create different geometric shapes");
		System.out.println("TwoD shapes: (1)Square (2)Rectangle (3)Rhombus (4)Parallelogram (5)IsoTrapezoid (6)Kite");
		System.out.println("ThreeD shapes: (7)Cube (8)Cuboid");
		System.out.println("If you want to end the program: (9) exit");
	}
	
	/**Creation1 method:
	 * a functional method that followed by Menu() method and used to create a default shape
	 * In the menu method, there are nine options include eight shape to create and one option to exit.
	 * Again, the program will not end until the user input '9', so that you can continue create other default shapes.
	 * The object creation connects to default constructor in the object class
	 * 
	 * Once you create a new shape, the program tells you the number of the shape such as No.1 or No.2, the next is the properties.
	 * Something like 'new' plus 'Shape's name()' is the format of create a new object
	 * 'GeomericList.add' means to add one element of object in the arraylist.
	 * The object can be stored because it is an object type of arraylist.
	 * 
	 * List of Local Variable and Loop
	 * choice - the similar function as 'option' that allow the user choose what shape to create
	 * input - the method make the users input a number and give to 'choice'
	 * switch - each case refers to a particular shape, so it is very clear that the program will know witch shape the user want to create
	 * switch - the loop connects to static arraylist 'GeometricList', once a new shape of object is created, it stores in arraylist
	 * GeometricList - the arraylist is used to store the data, later on it allows user to check a particular shape
	 * 
	 * @throws IOException
	 * @return void
	 */
	public static void Creation1()throws IOException{
		int choice = 0;
		Menu();
		choice = (int) input("Your choice: ");
		System.out.println();
		
		while(choice!=9){ //if the option equals to '9', the user chooses to exit and gets out of the loop
			switch(choice){
			case 1:
				GeometricList.add(new Square());
				break;
			case 2:
				GeometricList.add(new Rectangle());
				break;
			case 3:
				GeometricList.add(new Rhombus());
				break;
			case 4:
				GeometricList.add(new Parallelogram());
				break;
			case 5:
				GeometricList.add(new IsoTrapezoid());
				break;
			case 6:
				GeometricList.add(new Kite());
				break;
			case 7:
				GeometricList.add(new Cube());
				break;
			case 8:
				GeometricList.add(new Cuboid());
				break;
			default:
			    System.out.println ("The number you enter is incorrect, enter again(1-9).");
			    break;
			}
			System.out.println("This is your No." + (numObject+1) + " shape's object");//It tells the user the number of the shape is created
			System.out.println(GeometricList.get(numObject).toString());//output the attributes of the shape
			numObject++; // once a new object is created, the index has to plus one
			System.out.println();
			Menu();
			System.out.println();
			System.out.println("You can continue to create a default shape");
			choice = (int) input("or enter '9' back to main menu, please input number(1-9): ");
			System.out.println();
		}//end of while loop
		
	}//end of Creation1 method
	
	/** Creation2 method:
	 * a functional method that followed by Menu() method and used to create a custom shape
	 * Again, the program will not end until the user input '9' so that you can continue create other default shapes.
	 * Once you create a new shape, the program tells you the number of the shape such as No.1 or No.2, the next is the properties.
	 * Something like 'new' plus 'Shape's name()' is the format of create a new object
	 * 'GeomericList.add' means to add one element of object in the arraylist.
	 * The object can be stored because it is an object type of arraylist.
	 * 
	 * This time, the object creation will connects to the overloaded constructor of the class, such as rhombus, the constructor looks like
	 * 'public Rhombus(double width, double height, boolean gonext)' so that we need two number and a 'true' send to the object
	 * so in the bracket, 'input' method will return the number, and 'true' will automaticly send to the class.
	 *
	 * The purpose of 'true' is that some shapes is follow by a superclass such as square and rectangle. When we create a rectangle, 
	 * it passes through square class and inherits the width, also the 'numSquare' will be awake. Boolean variable is used to
	 * judge a shape whether is really created or not. Otherwise, 'false' will be send to the class(later on in the particular class
	 * will explain it.)
	 * 
	 * The structure: input("Please enter the width of the square: "), is acceptable. Normally doing is that 
	 * 'System.out.println("Please enter the width of the square") then the following is  'int num = input();''
	 * However, that will be tedious that repeat so much "int num = input()" and that is not efficient because I need multiple
	 * parameter so only one 'num' is not enough. I have to create "int num1 = input(); int num2 = input()" and so on.
	 * 
	 * The number return from the 'input("Please enter the width of the square: ")' is the attributes of the shape, 
	 * from this example, the number is the width of the square. The representation of the number is very clear by the description
	 * in the bracket. 
	 * 
	 * List of Local Variable and Loop
	 * choice - the similar function as 'option' that allow the user choose what shape to create
	 * input - the method make the users input a number and give to 'choice'
	 * switch - each case refers to a particular shape, so it is very clear that the program will know witch shape the user want to create
	 * switch - the loop connects to static arraylist 'GeometricList', once a new shape of object is created, it stores in arraylist
	 * GeometricList - the arraylist is used to store the data, later on it allows user to check a particular shape
	 * GeometricList - this time is a little bit different from the Creation1() method because it requires use to input a value
	 * 				   new Square() will connect to the constructor in Square class that receive two parameter, one is the value of width 
	 * 				   and a boolean of ture that means it turely creates a new square object
	 * 
	 * @throws IOException
	 * @return void
	 */
	public static void Creation2() throws IOException {
		int choice = 0;
		Menu();
		choice = (int) input("Your choice: ");
		System.out.println();
		
		while(choice!=9){ // if the option equals to '9', the user chooses to exit and gets out of the loop
		switch(choice){
		case 1:
			GeometricList.add(new Square(input("Please enter the width of the square: "), true));
			break;//end of the square switch
		case 2:
			GeometricList.add(new Rectangle(input("Please enter the width of the rectangle: "),
					input("please enter the length of the rectangle: ")));
			break;//end of the rectangle switch
		case 3:
			GeometricList.add(new Rhombus(input("Please enter the width of the rhombus: "),
					input("please enter the height of the rhombus: "), true));
			break;//end of the rhombus switch
		case 4:
			GeometricList.add(new Parallelogram(input("Please enter the width of the Parallelogram: "),
					input("please enter the height of the Parallelogram: "), 
					input("please enter the widthbase of the Parallelogram: "), true));
			break;//end of the Parallelogram switch
		case 5:
			GeometricList.add(new IsoTrapezoid(input("Please enter the width of the IsoTrapezoid: "),
					input("please enter the height of the IsoTrapezoid: "), 
					input("please enter the widthbase of the IsoTrapezoid: "), 
					input("please enter the widthtop of the IsoTrapezoid: "), true));
			break;//end of the IsoTrapezoid switch
		case 6:
			GeometricList.add(new Kite(input("Please enter the first diagonal of the Kite: "),
					input("please enter the second diagonal of the Kite: "), 
					input("please enter the short width of the Kite: "), 
					input("please enter the long width of the Kite: ")));
			break;//end of the Kite switch
		case 7:
			GeometricList.add(new Cube(input("Please enter the width of the Cube: "), true));
			break;//end of the Cube switch
		case 8:
				GeometricList.add(new Cuboid(input("Please enter the width of the Cuboid: "), 
						input("please enter the lenght of the Cuboid: "), 
						input("please enter the height of the Cuboid: ")));
			break;//end of the Cube switch
		default:
		    System.out.println ("The number you enter is incorrect, enter again(1-9).");
		    break;
		}//end of the all shapes switch
		System.out.println("This is your No." + (numObject+1) + " shape's object");
		System.out.println(GeometricList.get(numObject).toString());
		numObject++;
		System.out.println();
		Menu();
		System.out.println();
		System.out.println("You can continue to create a custom shape");
		choice = (int) input("Or enter '9' back to main menu, Please input number(1-9) ");
		System.out.println();
		}//end of while loop
		
	}//end of Creation2 method
	
	/** Output method:
	 * a procedural method to output the number of each shape and the number of total shape
	 * It is very clear to list all the shapes includes quadrilaterals and three d shapes
	 * 
	 * @param none
	 * @return void
	 */
	public static void Output(){
		System.out.println("The num of total shapes:  " + Geometric.getnumGeo());
		System.out.println("The num of square:        " + Square.getNumSquare() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of rectangle:     " + Rectangle.getNumRectangle() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of rhombus:       " + Rhombus.getNumRhombus() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of parallelogram: " + Parallelogram.getNumParallelogram() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of isotrapezoid:  " + IsoTrapezoid.getNumIsoTrap() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of kite:          " + Kite.getNumKite() + " quadrilaterals: " + TwoDShape.getnumShape());
		System.out.println("The num of cube:          " + Cube.getNumCube() + " threeD shapes:  " + ThreeDShape.getnumThreeD());
		System.out.println("The num of cuboid:        " + Cuboid.getNumCuboid() + " threeD shapes:  " + ThreeDShape.getnumThreeD());
	}//end of Output method
	
	/**Finding method
	 * a functional method, there is a loop to search the database and find the particular shape through the unique ID
	 * The format of code(unique ID) will tell you include some codes that start with same letter.
	 * 
	 * 'GeometricList.get(x).getUniqueID().equals(code)'is the format, 'get(number)' represents the element(object) with related index
	 * 'getUniqueID()' is the method inside the object class so the dot notation is to awake it.
	 * 'equal' method, obviously, is used to compare the user input and the 'uniqueID' in the objects' database.
	 * 
	 * List of Local Variable and Loop
	 * find - boolean type of variable. Sometimes the ID is wrong or the shape is not exist so that the loop cannot find it and causes error
	 * code - the unique key with the format such as "s001", is used to test to whether is equal to an ID in one of the shape
	 * loop - to search through the index, from 0 to less than the size of GeometricList
	 * GeometricList - the arraylist calls the index then call the getUniqueID relates to that particular object 
	 * 
	 * @param find, code
	 * @return void
	 * @throws IOException
	 */
	public static void Finding() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean find = false;
		
		System.out.println("the format of code: 's001'");
		System.out.println("start with a lowercase of first letter of shape's name, then follow '00' and number of index");
		System.out.println("Square: s00, Parallelogram: p00, IsoTrapzoid: i00");
		System.out.println("Rectangel: r00, Rhombus: r01, Kite: k00");
		System.out.println("Cube: c00, Cuboid: c01");
		System.out.print("Please enter the unique key: ");
		String code = br.readLine();
		System.out.println();
		
		for (byte x = 0; x < GeometricList.size(); x++){
			if (GeometricList.get(x).getUniqueID().equals(code)){ //dot notation awakes the getUniqueID method in the object
				System.out.println("The index is " + x);
				System.out.println(GeometricList.get(x).toString());
				find = true;
			}
		}
		if (find == false)
			System.out.println("Sorry, not exist.");
	}//end of Finding method
	
	/**List method
	 * a procedural method that output all the object in the database(GeometricList)
	 * includes the ID and attributes of each shapes
	 * 
	 * @param none
	 * @return void
	 */
	public static void List(){
		if (GeometricList.isEmpty()){
			System.out.println("The database is empty");
		}
		else{
			for (byte y = 0; y < GeometricList.size(); y++){
				System.out.println("This is your No." + (y+1) + " shape");
				System.out.println(GeometricList.get(y).toString());
				System.out.println();
			}
		}
	}//end of List method
	
}//end of AssignmentTwo class

//****************************************************************************************
//Geometric Class
//Programmer: David Chen							
//Date: 2017.5.23							
//Language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a generic template of geometric object which includes a ID of shape
//<List Of Identifiers>
//let numGeometric = static variable to records the number of totall geometric objects
//****************************************************************************************
	abstract class Geometric{
		private static int numGeometric = 0;
		
		/** Default Constructor method which makes an increment of 'numGeometric'
		 * means that a new object is created
         */
		public Geometric(){
			numGeometric++;
		}//end of Geometric(Default Constructor)
		/** Accessor returns the number of geometric created
		 * 
		 * @return numGeometric - number of geometric created<int>
		 */
		public static int getnumGeo(){
			return numGeometric;
		}//end of getnumGeo
		/** abstract method returns the ID of particular shape
		 * 
		 * @return none(will be overrided in later specific class)
		 */
		public abstract String getUniqueID();
	}//end of Geometric class
	
//****************************************************************************************
//TwoDShape Class
//Programmer: David Chen							
//Date: 2017.5.23							
//Language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a generic template of two dimensional shape which includes a ID of shape
//<List Of Identifiers>
//let numShape = static variable to records the number of totall two dimensional shapes of objects
//****************************************************************************************
	abstract class TwoDShape extends Geometric{
		private static int numShape = 0;
		
		/** Default Constructor method which makes an increment of 'numShape'
		 * means that a new two dimensional shape is created
         */
		public TwoDShape(){
			numShape++;
		}//end of TwoDShape (Default constructor)
		/** Accessor returns the number of twoD shapes created
		 * 
		 * @return numShape - number of twoD shapes created<int>
		 */
		public static int getnumShape(){
			return numShape;
		}//end of getnumShape
		/** abstract method returns the area of particular shape
		 * 
		 * @return none(will be overrided in later specific class)
		 */
		public abstract double findArea();
		/** abstract method returns the perimeter of particular shape
		 * 
		 * @return none(will be overrided in later specific class)
		 */
		public abstract double findPerimeter();
	}//end of TwoDShape class
	
//****************************************************************************************
//ThreeDShape Class
//Programmer: David Chen							
//Date: 2017.5.23							
//Language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a generic template of three dimensional shape which includes a ID of shape
//<List Of Identifiers>
//let numThreeD = static variable to records the number of totall three dimensional shapes of objects
//****************************************************************************************
	abstract class ThreeDShape extends Geometric{
		private static int numThreeD = 0;
		
		/** Default Constructor method which makes an increment of 'numThreeD'
		 * means that a new three dimensional shape is created
         */
		public ThreeDShape(){
			numThreeD++;
		}//end of ThreeDShape(Default Constructor)
		/** Accessor returns the number of threeD shapes created
		 * (Cube or Cuboid)
		 * 
		 * @return numShape - number of threeD shapes created<int>
		 */
		public static int getnumThreeD(){
			return numThreeD;
		}
		/** abstract method returns the Surface area of particular shape
		 * 
		 * @return none(will be overrided in later specific class)
		 */
		public abstract double findSurface();
		/** abstract method returns the volume of particular shape
		 * 
		 * @return none(will be overrided in later specific class)
		 */
		public abstract double findVolume();
	}//end of ThreeDShape class

//****************************************************************************************
//Square Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Square object which extends the TwoDShape class.
//<List Of Identifiers>
//let numSquare = static variable to records the number of objects of square
//let width = instance variable to determine the width of the square so that it can be used to calculate perimeter and area
//let squareID = instance string type of variable as a unique key of a specific square
//****************************************************************************************
	class Square extends TwoDShape{
		private static int numSquare = 0;
		private double width;
		private String squareID;
		
		/** Default Constructor method which initializes the width
         * of the square, increments the number of squares and creates a new string of square's ID.
         */
		public Square(){
			super();
			width = 2.0;
			numSquare++;
			squareID = "s00" + numSquare; //each time the program access the class will have different numSquare
		}//end of Square (Default Constructor)
        /** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of square and creates a new square's ID. 
         * 
         * The purpose of the 'gonext':
         * If we creates a rectangle, for example, it will pass through square and also make an increment of numSquare
         * and that is not correct because a square doesn't create actually. So once the user creates a rectangle, the boolean of 'false'
         * will sent to this constructor so that numSquare will not increment. Mind that, 'width' is necessary to be awaked, inherits to the 
         * rectangle, so the value of width will give to rectangle, but 'numSquare' is still asleep.
         * 
         * @param width - holds value of specific width<double>
         * @param gonext - holds determination of this square<boolean>
         */
		public Square(double width, boolean gonext){
			super();
			this.width = width;
			if(gonext) // the same as 'if(gonext == true)' 
			numSquare++;
			squareID = "s00" + numSquare;
		}// end of Square (overloaded constructor)
		/** Accessor returns the value of specific width
		 * 
		 * @return width - the width of the specific square<double>
		 */
		public double getWidth(){
			return width;
		}// end of getWidth
		/** Mutator allows to modify the value of specific width
		 * receive the parameter of w and gives to width
		 * 
		 * @return width - the width of the specific square<double>
		 */
		public void setWidth(double w){
			width = w;
		}// end of setWidth
		/** Accessor returns the number of square
		 * 
		 * @return numSquare - the number of total number of squares<int>
		 */
		public static int getNumSquare(){
			return numSquare;
		}// end of getNumSquare
		/** Accessor returns the text of unique key of specifc square
		 * 
		 * @return squareID - the text of unique ID of specifc square<String>
		 */
		public String getUniqueID(){
			return squareID;
		}// end of getUniqueID
		/**Overridden findArea method which returns the area of the square
		 * the formula to calculate the area of square: width*width
		 */
		public double findArea(){
			return width*width;
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the square
		 * the formula to calculate the area of square: width*4
		 */
		public double findPerimeter(){
			return width*4;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of square has same width
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Square){
				Square s = (Square) o;
				if (this.squareID.equals(s.squareID)){
					return true;
				}
			}
			return false;
		}//end of equals
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the square object     
         */
		public String toString (){
			return	"The unique key of this square is " + getUniqueID() + "\n" +
					"The width of the square is " + getWidth() + "\n" +
					"The area of the square is " + findArea() + "\n" +
					"The perimeter of the square is " + findPerimeter();
		}//end of toString
	}//end of Square class
	
//****************************************************************************************
//Rectangle Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Rectangle object which extends the Square class.
//<List Of Identifiers>
//let numRectangle = static variable to records the number of objects of rectangle
//let length = instance variable to determine the length of the rectangle so that it can be used to calculate perimeter and area
//let rectangleID = instance string type of variable as a unique key of a specific rectangle
//****************************************************************************************
	final class Rectangle extends Square{
		private static int numRectangle = 0;
		private double length;
		private String rectangleID;
		
		/** Default Constructor method which initializes the length
         * of the rectangle, increments the number of rectangle and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0
         * 
         * IMPORTANT Point: the 'super(2.0, false)'
         * Why it will call the overloaded constructor rather than default constructor, although we create a default rectangle?
         * We can see '2.0' and 'false' is the parameter that send to super class, so base on the knowledge of overloaded method,
         * method with same name but receive different parameter! The same as constructor, only 'Square(double width, boolean gonext)'
         * has the ability to receive but not 'Square()'. Additionaly, If you come back to Square class, you will see a code 'if(gonext)' in overloaded 
         * constructor instead of default constructor because only the overloaded constructor has the responsibility to judge whether a 
         * square object is created or not.
         * 
         * the 'false' will send to super class because a square is not created here
         */
		public Rectangle(){
			super(2.0, false); //call the super class overloaded constructor
			length = 3.0;
			numRectangle++;
			rectangleID = "r00" + numRectangle;
		}//end of Rectangle(Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of rectangle and creates a new rectangle's ID. 
         * 
         * It doesn't need a 'gonext' here because this is the last subclass of superclass 'square',
         * there is no following shape after rectangle.
         * 
         * @param width - holds value of specific width<double>
         * @param length - holds value of specific length<double>
         */
		public Rectangle(double width, double length){
			super(width, false);
			this.length = length;
			numRectangle++;
			rectangleID = "r00" + numRectangle;
		}//end of Rectangle(Overloaded Constructor)
		/** Accessor returns the value of specific length
		 * 
		 * @return length - the length of the specific rectangle<double>
		 */
		public double getLength(){
			return length;
		}//end of getLength
		/** Mutator allows to modify the value of specific length
		 * receive the parameter of l and gives to length
		 * 
		 * @return length - the length of the specific rectangle<double>
		 */
		public void setLength(double l){
			length = l;
		}//end of setLength
		/** Accessor returns the number of rectangle
		 * 
		 * @return numRectangle - the number of total number of rectangle<int>
		 */
		public static int getNumRectangle(){
			return numRectangle;
		}//end of getNumRectangle
		/** Accessor returns the text of unique key of specifc rectangle
		 * 
		 * @return rectangleID - the text of unique ID of specifc rectangle<String>
		 */
		public String getUniqueID(){
			return rectangleID;
		}//end of getUniqueID
		/**Overridden findArea method which returns the area of the rectangle
		 * the formula to calculate the area of rectangle: width*length
		 */
		public double findArea(){
			return getWidth()*length;
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the rectangle
		 * the formula to calculate the area of rectangle: width*2 + length*2
		 */
		public double findPerimeter(){
			return getWidth()*2 + length*2;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of rectangle has same length
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Rectangle){
				Rectangle r = (Rectangle) o;
				if (this.length == r.length){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the rectangle object     
         */
		public String toString (){
			return "The unique key of this rectangle is " + getUniqueID() + "\n" +
					"The width of the rectangle is " + getWidth() + "\n" +
					"The length of the rectangle is " + getLength() + "\n" +
					"The area of the rectangle is " + findArea() + "\n" +
					"The perimeter of the rectangle is " + findPerimeter();
		}//end of toString method
	}//end of Rectangle class
	
//****************************************************************************************
//Rhombus Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Rhombus object which extends the Square class.
//<List Of Identifiers>
//let numRhombus = static variable to records the number of objects of rhombus
//let height = instance variable to determine the height of the rhombus so that it can be used to calculate perimeter and area
//let rhombusID = instance string type of variable as a unique key of a specific rhombus
//****************************************************************************************	
	class Rhombus extends Square{
		private static int numRhombus = 0;
		private double height;
		private String rhombusID;
		
		/** Default Constructor method which initializes the height
         * of the rhombus, increments the number of rhombus and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0
         * 
         * the 'false' will send to super class because a square is not created here
         */
		public Rhombus(){
			super(2.0, false);//call the super class overloaded constructor
			height = 1.5;
			numRhombus++;
			rhombusID = "r01" + numRhombus;
		}//end of Rhombus(Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of rhombus and creates a new rhombus's ID. 
         * 
         * The purpose of the 'gonext':
         * If we creates a rhombus, it will pass through square and also make an increment of numSquare
         * and that is not correct because a square doesn't create actually. So once the user creates a new shape, a boolean of 'false'
         * will sent to this constructor so that numSquare will not increment.
         * 
         * @param width - holds value of specific width<double>
         * @param height - holds value of specific height<double>
         */
		public Rhombus(double width, double height, boolean gonext){
			super(width, false);
			this.height = height;
			if(gonext)
			numRhombus++;
			rhombusID = "r01" + numRhombus;
		}//end of Rhombus(Overloaded Constructor)
		/** Accessor returns the value of specific height
		 * 
		 * @return height - the length of the specific rhombus<double>
		 */
		public double getHeight(){
			return height;
		}//end of getHeight
		/** Mutator allows to modify the value of specific height
		 * receive the parameter of h and gives to height
		 * 
		 * @return height - the height of the specific rhombus<double>
		 */
		public void setHeight(double h){
			height = h;
		}//end of setHeight
		/** Accessor returns the number of rhombus
		 * 
		 * @return numRhombus - the number of total number of rhombus<int>
		 */
		public static int getNumRhombus(){
			return numRhombus;
		}//end of getNumRhombus
		/** Accessor returns the text of unique key of specifc rhombus
		 * 
		 * @return rhombusID - the text of unique ID of specifc rhombus<String>
		 */
		public String getUniqueID(){
			return rhombusID;
		}//end of getUniqueId
		/**Overridden findArea method which returns the area of the rhombus
		 * the formula to calculate the area of rhombus: width*height
		 */
		public double findArea(){
			return getWidth()*height;
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the rhombus
		 * the formula to calculate the area of rhombus: width*4
		 */
		public double findPerimeter(){ //the same formula as square
			return getWidth()*4;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of rhombus has same height
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Rhombus){
				Rhombus r = (Rhombus) o;
				if (this.height == r.height){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the rhombus object     
         */
		public String toString (){
			return "The unique key of this rhombus is " + getUniqueID() + "\n" +
					"The width of the rhombus is " + getWidth() + "\n" +
					"The height of the rhombus is " + getHeight() + "\n" +
					"The area of the rhombus is " + findArea() + "\n" +
					"The perimeter of the rhombus is " + findPerimeter();
		}//end of toString method
	}//end of Rhombus class
	
//****************************************************************************************
//Parallelogram Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Parallelogram object which extends the Rhombus class.
//<List Of Identifiers>
//let numParallelogram = static variable to records the number of objects of parallelogram
//let widthBase = instance variable to determine the base width of the parallelogram so that it can be used to calculate perimeter and area
//let parallelogramID = instance string type of variable as a unique key of a specific parallelogram
//****************************************************************************************	
	class Parallelogram extends Rhombus{
		private static int numParallelogram = 0;
		private double widthBase;
		private String parallelID;
		
		/** Default Constructor method which initializes the widthBase
         * of the parallelogram, increments the number of parallelogram and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0 and height of 1.5
         * 
         * the 'false' will send to super class because a rhombus is not created here
         */
		public Parallelogram(){
			super(2.0, 1.5, false);
			widthBase = 3.5;
			numParallelogram++;
			parallelID = "p00" + numParallelogram;
		}//end of Parallelogram (Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of parallelogram and creates a new parallelogram's ID. 
         * 
         * The purpose of the 'gonext':
         * If we creates a parallelogram, it will pass through rhombus and also make an increment of numRhombus
         * and that is not correct because a rhombus doesn't create actually. So once the user creates a new shape, a boolean of 'false'
         * will sent to this constructor so that numRhombus will not increment.
         * 
         * @param width - holds value of specific width<double>
         * @param height - holds value of specific height<double>
         * @param widthBase - holds value of specific widthBase<double>
         */
		public Parallelogram(double width, double height, double widthBase, boolean gonext){
			super(width, height, false);
			this.widthBase = widthBase;
			if(gonext)
			numParallelogram++;
			parallelID = "p00" + numParallelogram;
		}//end of Parallelogram (Overloaded Constructor)
		/** Accessor returns the value of specific widthBase
		 * 
		 * @return widthBase - the widthBase of the specific parallelogram<double>
		 */
		public double getWidthBase(){
			return widthBase;
		}//end of getWidthBase 
		/** Mutator allows to modify the value of specific widthBase
		 * receive the parameter of wb and gives to widthBase
		 * 
		 * @return widthBase - the widthBase of the specific parallelogram<double>
		 */
		public void setWidthBase(double wb){
			widthBase = wb;
		}//end of setWidthBase
		/** Accessor returns the number of parallelogram
		 * 
		 * @return numParallelogram - the number of total number of parallelogram<int>
		 */
		public static int getNumParallelogram(){
			return numParallelogram;
		}//end of getNumParallelogram
		/** Accessor returns the text of unique key of specifc parallelogram
		 * 
		 * @return parallelogramID - the text of unique ID of specifc parallelogram<String>
		 */
		public String getUniqueID(){
			return parallelID;
		}//end of getUniqueID
		/**Overridden findArea method which returns the area of the parallelogram
		 * the formula to calculate the area of parallelogram: widthBase*height
		 */
		public double findArea(){
			return getHeight()*widthBase;
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the parallelogram
		 * the formula to calculate the area of parallelogram: width*2 + widthBase*2
		 */
		public double findPerimeter(){ 
			return getWidth()*2 + widthBase*2;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of parallelogram has same widthBase
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Parallelogram){
				Parallelogram p = (Parallelogram) o;
				if (this.widthBase == p.widthBase){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the parallelogram object     
         */
		public String toString (){
			return 	"The unique key of this parallelogram is " + getUniqueID() + "\n" +
					"The width of the parallelogram is " + getWidth() + "\n" +
					"The base width of the parallelogram is " + getWidthBase() + "\n" +
					"The area of the parallelogram is " + findArea() + "\n" +
					"The perimeter of the parallegoram is " + findPerimeter();
		}//end of toString method
	}//end of Parallegoram class
	
//****************************************************************************************
//IsoTrapezoid Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a IsoTrapezoid object which extends the Parallelogram class.
//<List Of Identifiers>
//let numIsoTrapezoid = static variable to records the number of objects of isoTrapezoid
//let widthTop = instance variable to determine the upper width of the isoTrapezoid so that it can be used to calculate perimeter and area
//let isoTrapeID = instance string type of variable as a unique key of a specific isoTrapezoid
//****************************************************************************************	
	class IsoTrapezoid extends Parallelogram{
		private static int numIsoTrap = 0;
		private double widthTop;
		private String IsoTrapID;
		
		/** Default Constructor method which initializes the widthTop
         * of the isoTrapezoid, increments the number of isoTrapezoid and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0, height of 1.5 and widthbase of 3.5
         * 
         * the 'false' will send to super class because a parallelogram is not created here
         */
		public IsoTrapezoid(){
			super(2.0, 1.5, 3.5, false);
			widthTop = 0.9;
			numIsoTrap++;
			IsoTrapID = "i00" + numIsoTrap;
		}//end of IsoTrapezoid (Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of isoTrapezoid and creates a new isoTrapezoid's ID. 
         * 
         * The purpose of the 'gonext':
         * If we creates a isoTrapezoid, it will pass through parallelogram and also make an increment of numParalelogram
         * and that is not correct because a parallelogram doesn't create actually. So once the user creates a new shape, a boolean of 'false'
         * will sent to this constructor so that numParallelogram will not increment.
         * 
         * @param width - holds value of specific width<double>
         * @param height - holds value of specific height<double>
         * @param widthBase - holds value of specific widthBase<double>
         * @param widthTop - holds value of specific widthTop<double>
         */
		public IsoTrapezoid(double width, double height, double widthBase, double widthTop, boolean gonext){
			super(width, height, widthBase, false);
			this.widthTop = widthTop;
			if(gonext)
			numIsoTrap++;
			IsoTrapID = "i00" + numIsoTrap;
		}//end of IsoTrapezoid (Overloaded Constructor)
		/** Accessor returns the value of specific widthTop
		 * @return widthTop - the widthBase of the specific isoTrapezoid<double>
		 */
		public double getWidthTop(){
			return widthTop;
		}//end of getWidthTop
		/** Mutator allows to modify the value of specific widthTop
		 * receive the parameter of wt and gives to widthTop
		 * 
		 * @return widthTop - the widthTop of the specific isoTrapezoid<double>
		 */
		public void setWidthTop(double wt){
			widthTop = wt;
		}//end of setWidthTop
		/** Accessor returns the number of isoTrapezoid
		 * 
		 * @return numIsoTrapezoid - the number of total number of isoTrapezoid<int>
		 */
		public static int getNumIsoTrap(){
			return numIsoTrap;
		}//end of getNumIsoTrap
		/** Accessor returns the text of unique key of specifc isoTrapezoid
		 * 
		 * @return isoTrapezoidID - the text of unique ID of specifc isoTrapezoid<String>
		 */
		public String getUniqueID(){
			return IsoTrapID;
		}//end of getUniqueID
		/**Overridden findArea method which returns the area of the isoTrapezoid
		 * the formula to calculate the area of isoTrapezoid: (widthTop+widthBase)*height/2
		 */
		public double findArea(){
			return (widthTop + getWidthBase()) * getHeight() / 2;
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the isoTrapezoid
		 * the formula to calculate the area of isoTrapezoid: width*2 + widthBase + widthTop
		 */
		public double findPerimeter(){ 
			return getWidth()*2 + getWidthBase() + widthTop;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of isoTrapezoid has same widthTop
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof IsoTrapezoid){
				IsoTrapezoid i = (IsoTrapezoid) o;
				if (this.widthTop == i.widthTop){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the isoTrapezoid object     
         */
		public String toString (){
			return "The unique key of this IsoTrapzoid is " + getUniqueID() + "\n" +
					"The width of the Isotrapzoid is " + getWidth() + "\n" +
					"The base width of the parallelogram is " + getWidthBase() + "\n" +
					"The top width of IsoTrapzoid is " + getWidthTop() + "\n" +
					"The area of the IsoTrapzoid is " + findArea() + "\n" +
					"The perimeter of the IsoTrapzoid is " + findPerimeter();
		}//end of toString method
	}//end of IsoTrapezoid class
	
//****************************************************************************************
//Kite Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Kite object which extends the IsoTrapzoid class.
//<List Of Identifiers>
//let numKite = static variable to records the number of objects of kite
//let diagonal = instance variable to determine the diagonal of the kite so that it can be used to calculate perimeter and area
//let kiteID = instance string type of variable as a unique key of a specific kite
//****************************************************************************************	
	final class Kite extends IsoTrapezoid{
		private static int numKite = 0;
		private double diagonal;
		private String kiteID;
		
		/** Default Constructor method which initializes the widthTop
         * of the isoTrapezoid, increments the number of isoTrapezoid and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0(gives to diagonal)
         * height of 1.5(gives to second diagonal)
         * base width of 3.5(gives to longer width)
         * top width of 0.9(gives to shorter width)
         * The Kite class inherits the number but gives to different name, they actually have the same value.
         * 
         * the 'false' will send to super class because a isoTrapezoid is not created here
         */
		public Kite(){
			super(2.0, 1.5, 3.5, 0.9, false);
			diagonal = getWidth();
			numKite++;
			kiteID = "k00" + numKite;
		}//end of Kite (Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of kite and creates a new kite's ID. 
         * 
         * It doesn't need a 'gonext' here because this is the last subclass of superclass 'square',
         * there is no following shape after the kite.
         * 
         * @param diagonal - supposing it holds value of specific width<double>
         * @param height - supposing holds value of specific another diagonal<double>
         * @param widthBase - supposing holds value of specific longer width<double>
         * @param widthTop - supposing holds value of specific shorter width<double>
         */
		public Kite(double width, double height, double widthBase, double widthTop){
			super(width, height, widthBase, widthTop, false);
			diagonal = width;
			numKite++;
			kiteID = "k00" + numKite;
		}//end of Kite (Overloaded Constructor)
		/** Accessor returns the number of kite
		 * 
		 * @return numKite - the number of total number of kite<int>
		 */
		public static int getNumKite(){
			return numKite;
		}//end of getNumKite
		/** Accessor returns the text of unique key of specifc kite
		 * 
		 * @return kiteID - the text of unique ID of specifc kite<String>
		 */
		public String getUniqueID(){
			return kiteID;
		}//end of getUniqueID
		/** Accessor returns the value of specific diagonal
		 * @return diagonal - the widthBase of the specific kite<double>
		 */
		public double getDiagonal(){
			return diagonal;
		}//end of accessor: getDiagonal
		/** Mutator allows to modify the value of specific diagonal
		 * receive the parameter of di and gives to diagonal
		 * 
		 * @return diagonal - the diagonal of the specific kite<double>
		 */
		public void setDiagonal(double di){
			diagonal = di;
		}//end of mutator: setDiagonal
		/**Overridden findArea method which returns the area of the kite
		 * the formula to calculate the area of kite: (diagonal+height(second diagonal))/2
		 */
		public double findArea(){
			return (diagonal * getHeight()) / 2; //the height represents the second diagonal
		}//end of findArea
		/**Overridden findPerimeter method which returns the perimeter of the kite
		 * the formula to calculate the area of kite: widthbase(longer width)*2 + widthTop(shorter width)*2
		 */
		public double findPerimeter(){ 
			return getWidthBase()*2 + getWidthTop() * 2;
		}//end of findPerimeter
		/**equals method
		 * determine whether other object of kite has same diagonal
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Kite){
				Kite k = (Kite) o;
				if (this.diagonal == k.diagonal){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the kite object     
         */
		public String toString (){
			return "The unique key of this kite is " + getUniqueID() + "\n" +
					"The first diagonal of the kite is " + getDiagonal() + "\n" +
					"The second diagonal of the kite is " + getHeight() + "\n" +
					"The longer width of the kite is " + getWidthBase() + "\n" +
					"The shorter width of the kite is " + getWidthTop() + "\n" +
					"The area of the Kite is " + findArea() + "\n" +
					"The perimeter of the Kite is " + findPerimeter();
		}//end of toString method
	}//end of Kiteclass
	
//****************************************************************************************
//Cube Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Cube object which extends the ThreeDShape class.
//<List Of Identifiers>
//let numCube = static variable to records the number of objects of cube
//let width = instance variable to determine the width of the cube so that it can be used to calculate surface area and volume
//let cubeID = instance string type of variable as a unique key of a specific cube
//****************************************************************************************
	class Cube extends ThreeDShape{
		private static int numCube = 0;
		private double width;
		private String cubeID;
		
		/** Default Constructor method which initializes the width
         * of the cube, increments the number of cube and creates a new string of cube's ID.
         */
		public Cube(){
			super();
			width = 2.0;
			numCube++;
			cubeID = "c00" + numCube;
		}//end of Cube (Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of cube and creates a new cube's ID. 
         * 
         * The purpose of the 'gonext':
         * If we creates a cuboid, it will pass through square and also make an increment of numCube
         * and that is not correct because a cube doesn't create actually. So once the user creates a cuboid, the boolean of 'false'
         * will sent to this constructor so that numCube will not increment.
         * 
         * @param width - holds value of specific width<double>
         * @param gonext - holds determination of this square<boolean>
         */
		public Cube(double width, boolean gonext){
			super();
			this.width = width;
			if(gonext == true)
			numCube++;
			cubeID = "c00" + numCube;
		}//end of Cube (Overloaded Constructor)
		/** Accessor returns the value of specific width
		 * @return width - the width of the specific cube<double>
		 */
		public double getWidth(){
			return width;
		}//end of accessor: getWidth
		/** Mutator allows to modify the value of specific diagonal
		 * receive the parameter of di and gives to diagonal
		 * 
		 * @return width - the width of the specific cube<double>
		 */
		public void setWidth(double w){
			width = w;
		}//end of mutator: setWidth
		/** Accessor returns the number of cube
		 * 
		 * @return numCube - the number of total number of cube<int>
		 */
		public static int getNumCube(){
			return numCube;
		}//end of getNumCube
		/** Accessor returns the text of unique key of specifc cube
		 * 
		 * @return cubeID - the text of unique ID of specifc cube<String>
		 */
		public String getUniqueID(){
			return cubeID;
		}//end of getUniqueID
		/**Overridden findSurface method which returns the surface area of the cube
		 * the formula to calculate the area of cube: width*width*6
		 */
		public double findSurface(){
			return width * width * 6;
		}//end of findSurface
		/**Overridden findPerimeter method which returns the perimeter of the cube
		 * the formula to calculate the area of cube: width * width * width
		 */
		public double findVolume(){
			return width * width * width;
		}//end of findVolume
		/**equals method
		 * determine whether other object of cube has same width
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Cube){
				Cube c = (Cube) o;
				if (this.width == c.width){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the cube object     
         */
		public String toString (){
			return "The unique key of this cube is " + getUniqueID() + "\n" +
					"The width of the cube is " + getWidth() + "\n" +
					"The surface area of the cube is " + findSurface() + "\n" +
					"The volume of the cube is " + findVolume();
		}//end of toString method
	}//end of Cube class
	
//****************************************************************************************
//Cuboid Class
//Programmer: David Chen
//Date: 2017.5.23
//Programming language: Java(Eclipse neon.3)
//****************************************************************************************
//<Class>
//This class creates a template for a Cuboid object which extends the Cube class.
//<List Of Identifiers>
//let numCuboid = static variable to records the number of objects of Cuboid
//let length = instance variable to determine the length of the Cuboid so that it can be used to calculate surface area and volume
//let height = instance variable to determine the height of the Cuboid so that it can be used to calculate surface area and volume
//let cuboidID = instance string type of variable as a unique key of a specific Cuboid
//****************************************************************************************
	final class Cuboid extends Cube{
		private static int numCuboid = 0;
		private double length, height;
		private String cuboidID;
		
		/** Default Constructor method which initializes the length and the height
         * of the cuboid, increments the number of cuboid and creates a new string of its ID.
         * 
         * call the super class overloaded constructor and inherit the width of 2.0
         * 
         * the 'false' will send to super class because a cube is not created here
         */
		public Cuboid(){
			super(2.0, false);
			length = 3.0;
			height = 1.0;
			numCuboid++;
			cuboidID = "c01" + numCuboid;
		}//end of Cuboid (Default Constructor)
		/** Overloaded Constructor. Setting the attributes of the geometric in super class with
         * specified values. It increments the number of cuboid and creates a new cuboid's ID. 
         * 
         * It doesn't need a 'gonext' here because this is the last subclass of superclass 'cube',
         * there is no following shape after cuboid.
         * 
         * @param width - holds value of specific width<double>
         * @param length - holds value of specific length<double>
         * @param height - holds value of specific height
         */
		public Cuboid(double width, double length, double height){
			super(width, false);
			this.length = length;
			this.height = height;
			numCuboid++;
			cuboidID = "c01" + numCuboid;
		}//end of Cuboid (Overloaded Constructor)
		/** Accessor returns the value of specific length
		 * @return width - the length of the specific cuboid<double>
		 */
		public double getLength(){
			return length;
		}//end of accessor: getLength
		/** Mutator allows to modify the value of specific length
		 * receive the parameter of l and gives to length
		 * 
		 * @return width - the length of the specific cuboid<double>
		 */
		public void setLength(double l){
			length = l;
		}//end of mutator: setLength
		/** Accessor returns the value of specific height
		 * @return width - the height of the specific cuboid<double>
		 */
		public double getHeight(){
			return height;
		}//end of accessor: getHeight
		/** Mutator allows to modify the value of specific height
		 * receive the parameter of h and gives to height
		 * 
		 * @return width - the height of the specific cuboid<double>
		 */
		public void setHeight(double h){
			height = h;
		}//end of mutator: setHeight
		/** Accessor returns the number of cuboid
		 * 
		 * @return numCube - the number of total number of cuboid<int>
		 */
		public static int getNumCuboid(){
			return numCuboid;
		}//end of accessor: getNumCuboid
		/** Accessor returns the text of unique key of specifc cuboid
		 * 
		 * @return cubeID - the text of unique ID of specifc cuboid<String>
		 */
		public String getUniqueID(){
			return cuboidID;
		}//end of accessor: getUniqueID
		/**Overridden findSurface method which returns the surface area of the cuboid
		 * the formula to calculate the area of cuboid: width*length*2 + width*height*2 + length*height*2;
		 */
		public double findSurface(){
			return getWidth() * length * 2 + getWidth() * height * 2 + length * height * 2;
		}//end of findSurface
		/**Overridden findPerimeter method which returns the perimeter of the cuboid
		 * the formula to calculate the area of cuboid: width * length * height
		 */
		public double findVolume(){
			return getWidth() * length * height;
		}//end of findVolume
		/**equals method
		 * determine whether other object of cuboid has same length and same height
		 * 
		 * @return true/false
		 */
		public boolean equals(Object o){
			if (o instanceof Cuboid){
				Cuboid c = (Cuboid) o;
				if (this.length == c.length && this.height == c.height){
					return true;
				}
			}
			return false;
		}//end of equals method
		/**Overridden toString method which returns information about the geometric
         * @return a String illustrating the properties of the cuboid object     
         */
		public String toString (){
			return "The unique key of this cuboid is " + getUniqueID() + "\n" +
					"The width of the cuboid is " + getWidth() + "\n" +
					"The length of the cuboid is " + getLength() + "\n" +
					"The height of the cuboid is " + getHeight() + "\n" +
					"The surface area of the cuboid is " + findSurface() + "\n" +
					"The volume of the cuboid is " + findVolume();
		}//end of toString method
	}//end of Cuboid class
