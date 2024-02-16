package DungeonGame;
import java.util.Scanner;
import java.util.ArrayList;
public class DungeonGameDriver {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//create enemies with their stats and put them in a list to refer to when creating a current enemy
		ArrayList<Enemy> enemyPool = new ArrayList<Enemy>();
		Enemy goblin = new Enemy("Goblin", 75, 13, 20, "EASY");
		Enemy skeleton = new Enemy("Skeleton", 120, 10, 15, "MEDIUM");
		Enemy bear = new Enemy("Bear", 200, 20, 10, "HARD");
		Enemy dragon = new Enemy("Dragon", 350, 30, 0, "BOSS");
		enemyPool.add(goblin);
		enemyPool.add(skeleton);
		enemyPool.add(bear);
		enemyPool.add(dragon);
		//player created and stat values depending on difficulty selected.
		Player player = new Player();
		int continueChoice = 1;
		while (continueChoice == 1){
			System.out.println("What will you name your player?: ");
			String name = input.nextLine();
			int normalOrEasy = inputValidation("Select difficulty: (1)Normal (2)Sooper Dooper Easy [increased stats and two actions per enemy action]: ", 1, 2);
			if (normalOrEasy == 1){
				player = new Player(name, 100, 5, 15, 20, 50, 1);
				System.out.println("NORMAL MODE");
			}
			if (normalOrEasy == 2){
				player = new Player(name, 125, 7, 17, 30, 55, 2);
				System.out.println("EASY MODE");
			}
			System.out.println();
			System.out.println(player.getPlayerName() + " walks into the dungeon...");
			System.out.println();
			char winOrLose = '0';
			int battleCount = 1;
			int battleNumber = 10;
			//creates enemies to then battle until player wins all battles or looses one battle.
			while (true) {
				Enemy currentEnemy = new Enemy();
				currentEnemy = spawnCurrentEnemy(battleCount, battleNumber, player, enemyPool);
				int enemyChargeStrongAttackChance = 30;
				boolean enemyReadyStrongAttack = false;
				System.out.println("As " + player.getPlayerName() + " ventures further in, something is lying in wait...");
				System.out.println("Encounter " + battleCount + "! A " + currentEnemy.getEnemyName() + " blocks your path! Prepare to fight.");
				System.out.println();
				battle:
				while (winOrLose != 'W' && winOrLose != 'L'){
					for (int i=0; i<player.getActionsAmount();i++){
						System.out.println(currentEnemy.getEnemyName() + " HP: " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth());
						System.out.println(player.getPlayerName() + " HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
						//player actions and checks if enemy is defeated.
						int action = inputValidation("What will you do? (1)Attack (2)Parry (3)Block: " , 1, 3);
						System.out.println();
						switch (action) {
							case 1:
								Player.attackEnemy(player, currentEnemy);
								System.out.println();
								break;
							case 2: 
								player.parry();
								break;
							case 3:
								player.block();
								break; 
						}
						if (currentEnemy.getCurrentHealth() <= 0) {
							System.out.println(currentEnemy.getEnemyName() + " HP: ! " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth());
							System.out.println(player.getPlayerName() + " HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
							System.out.println( player.getPlayerName() + " has defeated the "+ currentEnemy.getEnemyName() + "!");
							winOrLose = 'W';
							//stops enemy from making attack choices if its dead
							break battle;
						}
					}
					//enemy attack choices and checks if player or enemy is defeated (because enemy can take damage if parried).
					if (enemyReadyStrongAttack){
						Enemy.strongAttackPlayer(currentEnemy, player);
						enemyReadyStrongAttack = false;
					} else if ((Math.random()*100)+1 <= enemyChargeStrongAttackChance){
						enemyReadyStrongAttack = true;
						System.out.println(currentEnemy.getEnemyName() + " prepares to launch a devastating blow... (2x damage next attack!)");
					} else {
						Enemy.attackPlayer(currentEnemy, player);
					}
					System.out.println(); 
					if (player.getCurrentHealth() <= 0) {
						System.out.println(currentEnemy.getEnemyName() + " HP: " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth());
						System.out.println(player.getPlayerName() + " HP: ! " + player.getCurrentHealth() + "/" + player.getMaxHealth());
						winOrLose = 'L';
					}
					if (currentEnemy.getCurrentHealth() <= 0) {
						System.out.println(currentEnemy.getEnemyName() + " HP: ! " + currentEnemy.getCurrentHealth() + "/" + currentEnemy.getMaxHealth());
						System.out.println(player.getPlayerName() + " HP: " + player.getCurrentHealth() + "/" + player.getMaxHealth());
						System.out.println( player.getPlayerName() + " has defeated the "+ currentEnemy.getEnemyName() + "!");
						winOrLose = 'W';
					}
				}
				System.out.println();
				battleCount++;
				//battles loop ends when player loses a battle or wins last battle
				if (winOrLose == 'L'){
					break;
				}
				if (battleCount > battleNumber){
					break;
				}
				winOrLose = '0';
				levelUp(player);
				player.setCurrentHealth(player.getMaxHealth());
			}
			if (winOrLose == 'L'){
				System.out.println( player.getPlayerName() + " DIED in the dungeon. You lose...");
			}
			if (winOrLose == 'W'){
				System.out.println(player.getPlayerName() + " HAS DEFEATED ALL THE ENEMIES. You win!");
			}
			continueChoice = inputValidation("Play again? (1)Yes (2)No", 1, 2);
		}
		input.close();
	}
	//Confirms the input is an int and is in range before returning choice. Takes the input question and the range of choices.
	public static int inputValidation(String question, int rangeLow, int rangeHigh){
		while (true){
			Scanner input = new Scanner(System.in);
			System.out.println(question);
			try {
				int choice = input.nextInt();
				if (choice >= rangeLow && choice <= rangeHigh){
					return choice;
				}
				System.out.println("Choice not in range. Please try again. ");
			} catch (Exception e){
			System.out.println("Incorrect input. Please try again. ");
			}
		}
		
	}
	//Creates and returns a current enemy object that is copied from one of the enemy objects in enemyPool. The current enemy's difficulty depends on the proportion of battles the player won.
	public static Enemy spawnCurrentEnemy(int battleCount, int battleNumber, Player player, ArrayList<Enemy> enemyPool){
		if (battleCount <= battleNumber/3) {
			for (Enemy enemy : enemyPool){
				if (enemy.getEnemyDifficulty() == "EASY"){
					Enemy currentEnemy = new Enemy(enemy.getEnemyName(), enemy.getMaxHealth(), enemy.getAttackPoints(), enemy.getEvasion(), enemy.getEnemyDifficulty());
					return currentEnemy;
				}
			}
		}
		else if (battleCount <= (battleNumber/3)*2){
			for (Enemy enemy : enemyPool){
				if (enemy.getEnemyDifficulty() == "MEDIUM"){
					Enemy currentEnemy = new Enemy(enemy.getEnemyName(), enemy.getMaxHealth(), enemy.getAttackPoints(), enemy.getEvasion(), enemy.getEnemyDifficulty());
					return currentEnemy;
				}
			}
		}
		else if (battleCount > (battleNumber/3)*2 && battleCount < battleNumber){
			for (Enemy enemy : enemyPool){
				if (enemy.getEnemyDifficulty() == "HARD"){
					Enemy currentEnemy = new Enemy(enemy.getEnemyName(), enemy.getMaxHealth(), enemy.getAttackPoints(), enemy.getEvasion(), enemy.getEnemyDifficulty());
					return currentEnemy;
				}
			}
		}
		else if (battleCount == battleNumber){
			for (Enemy enemy : enemyPool){
				if (enemy.getEnemyDifficulty() == "BOSS"){
					Enemy currentEnemy = new Enemy(enemy.getEnemyName(), enemy.getMaxHealth(), enemy.getAttackPoints(), enemy.getEvasion(), enemy.getEnemyDifficulty());
					return currentEnemy;
				}
			}
		}
		Enemy currentEnemy = new Enemy();
		return currentEnemy;
	}	
	//increases the player stat depending on choice
	public static void levelUp(Player player){
		int choice = inputValidation("You can level up your stats! Please choose one: (1)Increase max HP by 10 (2)Increase defence by 1 (3)Increase attack points by 3 (4)Increase evasion chance by 5 (5)Increase parry chance by 4: ", 1, 5);
		switch (choice){
			case 1:
				player.setMaxHealth(player.getMaxHealth()+10);
				System.out.println("Max HP increased by 10! Your max HP is now " + player.getMaxHealth()+ "!");
				break;
			case 2:
				player.setDefence(player.getDefence()+1);
				System.out.println("Defence increased by 1! Your defence is now " + player.getDefence()+ "!");
				break;
			case 3:
				player.setAttackPoints(player.getAttackPoints()+3);
				System.out.println("Attack points increased by 1! Your attack points is now " + player.getAttackPoints() + "!");
				break;
			case 4:
				player.setEvasion(player.getEvasion()+5);
				System.out.println("Evasion chance increased by 5! Your evasion chance is now " + player.getEvasion() + "%!");
				break;
			case 5:
				player.setParryChance(player.getParryChance()+4);
				System.out.println("Parry chance increased by 4! Your parry chance is now " + player.getParryChance() + "%!");
				break;
		}
		System.out.println();
	}
}
