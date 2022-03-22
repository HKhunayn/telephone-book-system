import java.util.Scanner;

class LinkedList implements Values {

	Node head;
	Node tail;
	int Length;

	LinkedList() {
		head = tail = null;
		Length = 0;
	}


	public void Print(String line1,String line2, String line3, String line4, String line5,String options) {
		for(int i =0;i<10;i++){System.out.println("\n");} //clearing chat
		if (options.charAt(options.length()-1)== ':') // This if just for know if there is input or its just show
			System.out.print(upper + "\n\n" + line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n\n" + lower + "\n\n" + options );

		else if (options.equals(enterOptionMSG+ " ")) // just to detect if this method called by display method (this is a check for it)
			System.out.println(upper + "\n"+dataNemesfixed2+"\n" + line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n\n" + lower + "\n" + options );
		else // no input
			System.out.println(upper + "\n\n" + line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n\n" + lower + "\n" + options );
	}

	// called to show mainMenu page
	public void mainMenu(String info) {
		Print("","",info,"","",fiveOptionsMSG);
		Scanner input = new Scanner(System.in);
		String userChoice= input.nextLine();
		if (userChoice.equals("1"))add();
		else if (userChoice.equals("2"))remove();
		else if (userChoice.equals("3"))update();
		else if (userChoice.equals("4"))Display();
		else if (userChoice.equals("5"))Search();
		else if (userChoice.equals("6"))Sort();
		else if (userChoice.equals("7"))exit();
		else mainMenu("you have to enter number from 1 to 7!");
	}
	void exit() { // to exit the program
		Scanner input = new Scanner(System.in);
		Print("","",exitMSG,"",deletedMSG,twoOptionsMSG);
		String temp = input.nextLine();
		if (temp.equals("1")) {
			Print("","","",thanksMSG,"",shutDownMSG);
			System.exit(0);
		}
		else	mainMenu("");
	}
	void add(){ // taking input from user then pass it to another add() method
		Scanner input = new Scanner(System.in);
		String a[] = new String[7];
		String aa ="";
		for(int i = 0;i<a.length;i++){
			if(i==2){
				boolean keep = true;
				Print("",dataNemesfixed2,aa,"","enter the "+ dataNemes[i],dataNemes[i]+":");
				while(keep){

					a[i]= input.nextLine();
					if(!contains(getRightLength(a[i]))) {
						keep = false;
					}else {
						Print("", dataNemesfixed2, aa, "", a[i] + " " + phone + " already exist try another one!", dataNemes[i] + ":");
					}
				}
			}else{

				Print("",dataNemesfixed2,aa,"","enter the "+ dataNemes[i],dataNemes[i]+":");
				//Print("","","enter the "+ dataNemes[i],"",""," ");
				a[i]= input.nextLine();
			}
			if(a[i].length()>12)
				a[i]=a[i].substring(0,12);
			while(a[i].length() != 15){
				a[i]+= " ";
			}
			aa = aa + a[i];
		}
		add(a);
		mainMenu("added successfully");
	}
	void add(String[] data) { // create new now with an array of string
		if (head == null) {
			head = new Node(data, null, null);
			tail = head;
		} else {
			Node temp = new Node(data, tail, null);
			tail.next = temp;
			tail = temp;
		}
		Length++;
	}

	void update(){ // to change the phone number
		Print("","",updateMSG,"","",firstName+":");
		boolean keep =true;
		Scanner input = new Scanner(System.in);
		String s ;
		Node t=null;
		while(keep){
			s=input.nextLine();
			s= getRightLength(s);
			t=Search(s);
			if(t != null){
				Print("","",foundedMSG +"type the new phone number","","",phone+":");
				boolean keep2=true;
				while(keep2){
					s=input.nextLine();
					s= getRightLength(s);
					if(!contains(s))
						keep2=false;
					else
						Print("","",foundedMSG +"type another phone number","","",phone+":");

				}

				t.data[2]=s;
				keep = false;
			}else
				Print("","",notFoundedMSG+" try again","","",firstName+":");
		}
		mainMenu(getRealLeangth(t.data[0])+updatedMSG);
	}

	void Display(){ // to show all nodes
		Node a[] = new Node[5];
		Node temp = head;
		String s[] = new String[a.length];
		int i =-1;
		while(temp != null){
			i++;
			s[i] = temp.toString();
			temp = temp.next;
		}
		Print(s[0],s[1],s[2],s[3],s[4],enterOptionMSG+" ");
		Scanner input = new Scanner(System.in);
		String sd= input.nextLine();
		mainMenu(mainMSG);
	}
	void Search(){
		Scanner input = new Scanner(System.in);
		Print("","",searchMSG,"","",firstName+":");
		String s = input.nextLine();
		Node t = Search(s);
		if(t == null)
			mainMenu(s+notFoundedMSG);
		else
			mainMenu(s+" "+foundedMSG+"his/her phone number ="+t.data[2]);

	}
	Node Search(String FirstName) {
		FirstName = getRightLength(FirstName);
		Node temp = head;
		while (temp != null) {
			if (temp.data[0].equals(FirstName))
				return temp;
			temp = temp.next;
		}
		return null;
	}

	boolean contains(String PhoneNumber) {
		Node temp = head;
		while (temp != null) {
			if (temp.data[2].equals(PhoneNumber))
				return true;
			temp = temp.next;
		}
		return false;
	}

	boolean Compare(Node n1,Node n2){ // return true if the first node must be first!
		int n;

		if(n1.data[0].length()<n2.data[0].length())
			n=n1.data[0].length();
		else
			n=n2.data[0].length();
		for(int i =0;i<n;i++){
			int N1 = (int)n1.data[0].charAt(i);
			int N2 = (int)n2.data[0].charAt(i);
			if(N1 < N2)
				return true;
			else if(N1 > N2)
				return false;
		}
		if(n1.data[0].length() < n2.data[0].length())
			return true;
		else
			return false;
	}
	void Sort(){
		BubbleSort();
		Display();
	}
	void BubbleSort(){
		if(head == tail)
			return;
		Node i = head;
		Node j = i.next;
		while(i != null){
			while(j != null){
				if(!Compare(j.prev,j))
					Swip(j.prev,j);
				j = j.next;
			}
			i=i.next;
			j=i;

		}

	}
	void  Swip(Node n1,Node n2){
		if(n1 == null || n2==null)
			return;
		if(n1==n2)
			return;

		String temp[] = n1.data;
		n1.data=n2.data;
		n2.data=temp;
	}
	String getRightLength(String s){ // to make the length of string = 15
		while(s.length()!= 15)
			s+=" ";
		return s;
	}
	String getRealLeangth(String s){ // removing all unwanted spaces in any String
		while(s.charAt(s.length()-1) == ' ')
			s=s.substring(0,s.length()-1);
		return s;
	}
	void remove(){
		boolean keep = true;
		Scanner input = new Scanner(System.in);
		Print("","",removeMSG,"","",phone+":");
		while(keep){
			String phone = getRightLength(input.nextLine());

			if(phone.equals("-1             ")){
				mainMenu("No body removed");
				return;
			}
			if(contains(phone)){
				Delete(phone);
				keep = false;
			}else{
				Print("","","There is no body have this number type \"-1\" to back to mainmenu","","",this.phone+":");
			}


		}
		mainMenu("removed Successfully!");

	}
	void Delete(String PhoneNumber) {
		Node temp = head;
		//Node prev = null;
		while (temp != null && !temp.data[2].equals(PhoneNumber)) {
			//prev = temp;
			temp = temp.next;
		}
		if (temp!= null){
			Length--;
			if(head == tail){
				head=tail=null;
				return;
			}
			if(head == temp){
				head = head.next;
				head.prev=null;
			}else{
				temp.prev.next = temp.next;
			}
			if (tail == temp){
				tail=tail.prev;
				tail.next = null;
			}else{
				temp.next.prev = temp.prev;
			}

		}

	}
	

	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.mainMenu(welcomeMSG);
	}
}

class Node{
	String data[]; 			//0= first,	1= last,	2= phone,	3=city,		4=address,	5=sex,	6=email
	Node prev,next;

	Node(String[] data,Node prev,Node next){
		this.data = data;
		this.next=next;this.prev=prev;
	}
	public String toString() {
		String s = data[0]  + data[1]  + data[2] + data[3] + data[4]  + data[5]  + data[6];

		return s;

	}
}
