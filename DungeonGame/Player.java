class Player {
	String playerName;
	int maxHealth;
    int currentHealth;
    int defence;
    int attackPoints;
    int evasion;
    int parryChance;
    int actionsAmount;
    boolean isParrying;
    boolean isBlocking;
    Player(){}
    Player(String playerName, int maxHealth, int defence, int attackPoints, int evasion, int parryChance, int actionsAmount){
        this.playerName = playerName;
    	this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.defence = defence;
        this.attackPoints = attackPoints;
        this.evasion = evasion;
        this.parryChance = parryChance;
        this.actionsAmount = actionsAmount;
        this.isParrying = false;
        this.isBlocking = false;
    }
	//subtracts an enemy object's currenthealth by the player object's attack points. Will not subtract if evasion check is less than the enemy's evasion.
	public static void attackEnemy(Player player, Enemy currentEnemy){
		int evasionCheck = (int)(Math.random()*100)+1;
		if (evasionCheck <= currentEnemy.getEvasion()){
			System.out.println(player.getPlayerName() + " attacks the " + currentEnemy.getEnemyName() + "...but missed!");
		}
		else {
			System.out.println(player.getPlayerName() + " attacks the " + currentEnemy.getEnemyName() + ", dealing " + player.getAttackPoints() + " damage!");
			currentEnemy.setCurrentHealth(currentEnemy.getCurrentHealth() - player.getAttackPoints());
		}
	}
	//methods for setting player as blocking or parrying. Both cannot be true at the same time.
    public void block(){
    	this.isBlocking = true;
		this.isParrying = false;
    	System.out.println(this.playerName + " is blocking the next attack...");
		System.out.println();
    }
    public void parry(){
        this.isParrying = true;
		this.isBlocking = false;
		System.out.println(this.playerName + " prepares to parry the next attack...");
		System.out.println();
    }
    
    public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
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
	public int getParryChance() {
		return parryChance;
	}
	public void setParryChance(int parryChance) {
		this.parryChance = parryChance;
	}
	public int getActionsAmount() {
		return actionsAmount;
	}
	public void setActionsAmount(int actionsAmount) {
		this.actionsAmount = actionsAmount;
	}
	public boolean isParrying() {
		return isParrying;
	}
	public void setParrying(boolean isParrying) {
		this.isParrying = isParrying;
	}
	public boolean isBlocking() {
		return isBlocking;
	}
	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}
    
}
