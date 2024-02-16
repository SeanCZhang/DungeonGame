public class Enemy {
	String enemyName;
	int maxHealth;
	int currentHealth;
	int attackPoints;
	int evasion;
	String enemyDifficulty;
	Enemy(){}
	Enemy(String enemyName, int maxHealth, int attackPoints, int evasion, String enemyDifficulty){
		this.enemyName = enemyName;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
		this.attackPoints = attackPoints;
		this.evasion = evasion;
		this.enemyDifficulty = enemyDifficulty.toUpperCase();
	}
	//subtracts a player object's currenthealth by the enemy object's attack points OR subracts an enemy object's currenthealth by the player object's 
	//attack points if parrycheck its less than the player's parrychance. Will not subtract if evasion check is less than the player's evasion.
	//Evasion is not checked if the player is blocking or parrying
	public static void attackPlayer(Enemy currentEnemy, Player player){
		int evasionCheck = (int)(Math.random()*100)+1;
		int parryCheck = (int)(Math.random()*100)+1;
		if (player.isParrying()){
			if (parryCheck <= player.getParryChance()){
				System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...but " + player.getPlayerName() + " parries the attack and counters!, dealing " + ((currentEnemy.getAttackPoints())+player.getAttackPoints()) + " damage back to " + currentEnemy.enemyName + "!");
				currentEnemy.setCurrentHealth(currentEnemy.getCurrentHealth() - ((currentEnemy.getAttackPoints())+player.getAttackPoints()));
			}
			else {
				System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...and " + player.getPlayerName() + " failed to parry the attack, taking the entire " + currentEnemy.getAttackPoints() + " damage!");
				player.setCurrentHealth(player.getCurrentHealth() - currentEnemy.getAttackPoints());
			}
			player.setParrying(false);
		}
		else if (player.isBlocking()){
			System.out.println(currentEnemy.getEnemyName() + " attacks, and " + player.getPlayerName() + " blocks! Dealing only " + (currentEnemy.getAttackPoints() - player.getDefence())/2 + " damage!");
			player.setCurrentHealth(player.getCurrentHealth() - (currentEnemy.getAttackPoints() - player.getDefence())/2);
			player.setBlocking(false);
		}
		else if (evasionCheck <= player.getEvasion()){
			System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...but missed!");
		}
		else {
			System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + ", dealing " + (currentEnemy.getAttackPoints() - player.getDefence()) + " damage!");
			player.setCurrentHealth(player.getCurrentHealth() - (currentEnemy.getAttackPoints() - player.getDefence()));
		}
	}
	//same thing as attackPlayer, but the enemy's attack points are doubled in all calculations
	public static void strongAttackPlayer(Enemy currentEnemy, Player player){
		int evasionCheck = (int)(Math.random()*100)+1;
		int parryCheck = (int)(Math.random()*100)+1;
		if (player.isParrying()){
			if (parryCheck <= player.getParryChance()){
				System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...but " + player.getPlayerName() + " parries the attack and counters!, dealing " + ((currentEnemy.getAttackPoints()*2)+player.getAttackPoints()) + " damage back to " + currentEnemy.enemyName + "!");
				currentEnemy.setCurrentHealth(currentEnemy.getCurrentHealth() - ((currentEnemy.getAttackPoints()*2)+player.getAttackPoints()));
			}
			else {
				System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...and " + player.getPlayerName() + " failed to parry the attack, taking the entire " + currentEnemy.getAttackPoints()*2 + " damage!");
				player.setCurrentHealth(player.getCurrentHealth() - currentEnemy.getAttackPoints()*2);
			}
			player.setParrying(false);
		}
		else if (player.isBlocking()){
			System.out.println(currentEnemy.getEnemyName() + " attacks, and " + player.getPlayerName() + " blocks! Dealing only " + (currentEnemy.getAttackPoints()*2 - player.getDefence())/2 + " damage!");
			player.setCurrentHealth(player.getCurrentHealth() - (currentEnemy.getAttackPoints()*2 - player.getDefence())/2);
			player.setBlocking(false);
		}
		else if (evasionCheck <= player.getEvasion()){
			System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + "...but missed!");
		}
		else {
			System.out.println(currentEnemy.getEnemyName() + " attacks " + player.getPlayerName() + ", dealing " + (currentEnemy.getAttackPoints()*2 - player.getDefence()) + " damage!");
			player.setCurrentHealth(player.getCurrentHealth() - (currentEnemy.getAttackPoints()*2 - player.getDefence()));
		}
	}
	public String getEnemyName() {
		return enemyName;
	}
	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public int getAttackPoints() {
		return attackPoints;
	}
	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}
	public int getEvasion() {
		return evasion;
	}
	public void setEvasion(int evasion) {
		this.evasion = evasion;
	}
	public String getEnemyDifficulty() {
		return enemyDifficulty;
	}
	public void setEnemyDifficulty(String enemyDifficulty) {
		this.enemyDifficulty = enemyDifficulty;
	}
	
}
