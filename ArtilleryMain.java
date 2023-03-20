package Artillery_Main;

import java.util.Random;
import java.util.Scanner;

public class ArtilleryMain {
	static int min;
	static int max;
	static int angle;
	static int power;
	static int ehp = 100;	//enemy health points
	static int php = 100; //player health points
	static int distance;
	static int affect;
	static int enemy_shot;
	public static void main (String[] args) throws InterruptedException {	
		welcome();
		turn_cycle();
	}
	public static void welcome() throws InterruptedException {
		System.out.println("Welcome to Artillery.");
		//Thread.sleep(1500);
		System.out.println("In this game you will play as an artillery piece.");
		//Thread.sleep(2000);
		System.out.println("As such, you will try to hit the enemies artillery piece with your artillery shells.");
		//Thread.sleep(3000);
		System.out.println("To do so you will enter the angle at which you will shoot in degrees.");
		//Thread.sleep(2500);
		System.out.println("Then you will dictate how much power you want to use in percentile values.");
		//Thread.sleep(2500);
		System.out.println();
		distance = distance(min, max);	//idk why but this prints a 0 
		System.out.println("The enemy is " + distance + " meters away.");
		System.out.println();
		enemy_shot = distance;
	}
	public static int distance(int min, int max) {
		Random rand = new Random();
        int val = Math.abs(rand.nextInt());
        min = 50;
		max = 725; 
        val = val % (max + 1 - min);
	    val = val + min;
	    System.out.println(enemy_shot);
	    return val;
	}
	public static void UI() {
		Scanner scan = new Scanner(System.in);
		System.out.println("At what angle would you like to fire?");
		System.out.println("(Enter a number between 1-90 degrees)");
		angle = scan.nextInt();
		System.out.println("How hard would you like to shoot?");
		System.out.println("(Enter a number between 1-100%)");
		power = scan.nextInt();
		hitnohit(angle, power);
	}
	public static void hitnohit(int angle, int power) {
		double deg_rad = Math.toRadians(angle);
		int affect = (int) ((power*power*Math.sin(2*deg_rad))/9.8);
		System.out.println("You fired " + affect + " meters");
		if (affect == distance){
			ehp = ehp - 50;
		}else if (affect >= distance - 5 && affect <= distance + 5) {
			ehp = ehp - 25;
		}else if (affect >= distance - 10 && affect <= distance + 10) {
			ehp = ehp - 15;
		}else if (affect >= distance - 25 && affect <= distance + 25) {
			ehp = ehp - 10;
		}else if (affect >= distance - 50 && affect <= distance + 50){
			ehp = ehp -5;	
		}
		System.out.println("ehp " + ehp);
		System.out.println();
	}
	public static void enemy_turn() {
		enemy_shot = enemy_shot /2;
		System.out.println("The enemy fired " + enemy_shot + " meters.");
		if (enemy_shot == 0){
			php = php - 50;
		}else if (enemy_shot <5) {
			php = php - 25;
		}else if (enemy_shot <10) {
			php = php - 15;
		}else if (enemy_shot <25) {
			php = php - 10;
		}else if (enemy_shot <50){
			php = php -5;	
		}
		System.out.println("php " + php);
		System.out.println();
	if (php <= 0) {
		lose();
	}else {
	turn_cycle();
	}
	}
	public static void turn_cycle() {
		UI();
		if (ehp <= 0) {
			win();
		}else {
			enemy_turn();
		}
	}
	public static void win() {
		System.out.println("You have destroyed your enemy!");
		System.out.println("Congratulations, you have won!");
	}
	public static void lose() {
		System.out.println("You have been destroyed.");
		System.out.println("You lose.");
	}
	
}


