package tp.p1.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import tp.p1.exceptions.FileContentsException;
import tp.p1.logic.Level;
import tp.p1.logic.lists.*;
import tp.p1.logic.objects.*;
import tp.p1.logic.managers.*;
import tp.p1.logic.factories.*;

public class Game {
	private int currentCycle = 0, zAmount;
	private SuncoinManager scManager;
	private ZombieManager zManager;
	private ZombieList zombieList;
	private PlantList plantList;
	public static final int startingSuncoins = 5000, dimX = 8, dimY = 4; 
	private Level level;
	private int seed;
	private Random rand;
	private float zFrequency;
	private String printMode;
	private boolean exitGame = false;
	public static final String wrongPrefixMsg = "Unknown game attribute: ";
	public static final String lineTooLongMsg = "Too many words on line commencing: ";
	public static final String lineTooShortMsg = "Missing data on line commencing: ";

	//----CONSTRUCTORS-------------------------------------//
	public Game() {
		
	}
	public Game(Level level, int seed, String mode) {
		this.seed = seed;
		this.rand = new Random(seed);
		this.level = level;

		this.zAmount = level.getAmount();
		this.zFrequency = level.getFreq();

		this.printMode = mode;
		
		//Lists & Managers Creation
		plantList = new PlantList(dimX*dimY);
		zombieList = new ZombieList(zAmount);

		zManager = new ZombieManager(rand, zAmount);
		scManager = new SuncoinManager(startingSuncoins);
	}
	
	public Game(Level level, int seed, int cycle, int sunCoins, int remZombies) {
		this.seed = seed;
		this.rand = new Random(seed);
		this.level = level;
		this.currentCycle = cycle;

		this.zAmount = remZombies;
		this.zFrequency = level.getFreq();

		//Managers Creation
		zManager = new ZombieManager(rand, zAmount);
		scManager = new SuncoinManager(sunCoins);
	}

	//----GETTERS & SETTERS----------------------------------//	

	public int getCurrentCycle() {
		return currentCycle;
	}

	public void setCurrentCycle(int currentCycle) {
		this.currentCycle = currentCycle;
	}

	public PlantList getPlantList() {
		return plantList;
	}

	public ZombieList getZombieList() {
		return zombieList;
	}
	
	public void setZombieList(ZombieList zombieList) {
		this.zombieList = zombieList;
	}

	public void setPlantList(PlantList plantList) {
		this.plantList = plantList;
	}


	public SuncoinManager getScManager() {
		return scManager;
	}

	public void setScManager(SuncoinManager scManager) {
		this.scManager = scManager;
	}

	public ZombieManager getzManager() {
		return zManager;
	}

	public void setzManager(ZombieManager zManager) {
		this.zManager = zManager;
	}

	public Random getRand() {
		return rand;
	}

	public int getSeed() {
		return seed;
	}

	public Level getLevel() {
		return level;
	}
	
	public String getPrintMode() {
		return printMode;
	}

	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}

	public void nextCycle() {
		this.currentCycle += 1;
	}
	
	public void exitGame(){
		this.exitGame = true;
	}

	//----------------------------------------------------//

	public void update() {
		if (this.zManager.isZombieAdded(zFrequency)) {
			this.addZombie(this);
		}

		plantList.update();
		zombieList.update();
	}

	//----OBJECT ADDERS----------------------------------//

	public String addPlantToGame (Plant plant, int x, int y) {
		if (!areCoordsWithinBoard(x,y)) {
			return "ERROR: Invalid coordinates provided. Make sure the values <x> and <y> are within the board.\\n";
		}
		else { //Valid Position
			if (isPositionEmpty(x,y) && scManager.getCurrentSuncoins() >= plant.getCost()) {
				plant.setGame(this);
				plant.setX(x);
				plant.setY(y);
				if (plantList.addObject(plant) == false) {
					return "ERROR: Maximum of additions reached.\n";
				}
				else{ //Position available and object added
					scManager.paySunCoins(plant.getCost());
				}
			}
			else if (!isPositionEmpty(x,y)){
				return "ERROR: Position not available.\n";
			}
			else if (scManager.getCurrentSuncoins() < plant.getCost()) {
				return "ERROR: Not enough <Sun Coins> to buy.\n";
			}
		}
		return "Successful";
	}

	public void addZombie (Game game) {
		int x = 8;
		int y = rand.nextInt(4); //[0...3]
		int z = rand.nextInt(ZombieFactory.getNumberTypes());
		//System.out.println("Zombie at (" + x + "," + y+1 + ")");
		if (isPositionEmpty(x, y)) {
			Zombie zombie = ZombieFactory.parseZombie(String.valueOf(z));
			zombie.setGame(this);
			zombie.setX(x);
			zombie.setY(y);
			if (zombieList.addObject(zombie) == false) {
				System.err.println("ERROR: Maximum of additions reached for Zombie.\n");
			}
			else {
				zManager.updateRemainingZombies();
			}
		}
		else {
			System.err.println("ERROR: Position not available for Zombie.\n");
		}
	}


	//----CHECKERS----------------------------------------//

	public boolean hasFinished() {
		if (!exitGame) {
			if (zombieList.hasFinished()) { //Checks if any zombie reached the goal alive
				System.out.println("\nZOMBIES WIN!");
				return true;
			}
			else if (!zombieList.hasAlive() && zManager.getRemainingZombies() <= 0) { //Checks if all zombies are dead and there are no zombies remaining
				System.out.println("\nPLAYER WINS!");
				return true;
			}
		} else {
			return true;
		}
		return false;
	}

	public boolean isPositionEmpty(int x, int y) {
		return !(this.getPinPosition(x,y) != null || this.getZinPosition(x,y) != null);
	}

	public boolean areCoordsWithinBoard(int x, int y) {
		return (x > -1  && x < 7 && y > -1 && y < 4);
	}
	
	//Loop through SFlist to find any SF at (x,y)
	public Plant getPinPosition (int x, int y) {
		return plantList.getPlant(x, y);
	}

	public Zombie getZinPosition(int x, int y) {
		return zombieList.getZombie(x, y);
	}

	//----ATACKERS---------------------------------------//

	public boolean attackZombie(int x, int y, int dmg) {
		boolean ok = false;
		Zombie possibleZ = getZinPosition(x, y);
		if (possibleZ != null) {
			possibleZ.takeDamage(dmg);
			ok = true;
		}
		return ok;
	}

	public boolean attackPlant(int x, int y, int dmg) {
		boolean ok = false;
		Plant possibleP = getPinPosition(x, y);
		if (possibleP != null) {
			possibleP.takeDamage(dmg);
			ok = true;
		}
		return ok;
	}

	//----------------------------------------------------//

	public void generateSun(int sun) {
		scManager.increaseCurrentSuncoins(sun);
	}

	//------------------Save & Load-----------------------//

	public void store(BufferedWriter writer) throws IOException {
		writer.write("cycle: " + currentCycle + "\n");
		writer.write("sunCoins: " + scManager.getCurrentSuncoins() + "\n");
		writer.write("level: " + level.name() + "\n");
		writer.write("remZombies: " + zManager.getRemainingZombies() + "\n");
		plantList.store(writer);
		writer.newLine();
		zombieList.store(writer);
	}

	public void load(BufferedReader reader) throws FileContentsException, IOException{
		String[] line;
		String[] objList;
		int cycle, sunCoins, remZombies;
		Level loadLevel;
		PlantList pL = new PlantList(dimX*dimY);
		
		
		line = this.loadLine(reader, "cycle", false);
		cycle = Integer.parseInt(line[0]);
		if (cycle < 0) throw new FileContentsException();
		
		
		line = this.loadLine(reader, "sunCoins", false);
		sunCoins = Integer.parseInt(line[0]);
		if (sunCoins < 0) throw new FileContentsException();
		
		
		line = this.loadLine(reader, "level", false);
		loadLevel = Level.parse(line[0]);
		if (loadLevel == null) throw new FileContentsException();
		
		
		line = this.loadLine(reader, "remZombies", false);
		remZombies = Integer.parseInt(line[0]);
		if (remZombies < 0 || remZombies > loadLevel.getAmount()) throw new FileContentsException();
		
		
		Game loadGame = new Game(loadLevel, this.seed, cycle, sunCoins, remZombies);
		
		
		line = this.loadLine(reader, "plantList", true);//Carga la lista de plantas
		if (line.length >= 0 && line.length < dimX*dimY) {
			for (int i = 0; i < line.length; i++) {
				objList = line[i].split(":");
				if (objList.length != 5) throw new FileContentsException(); //symbol[0]:lr[1]:x[2]:y[3]:t[4]
				Plant plant = PlantFactory.parsePlant(objList[0]);
				if (plant == null) throw new FileContentsException();
				plant.setGame(loadGame);
				if ((Integer.parseInt(objList[2]) < 0  || Integer.parseInt(objList[2]) > 6) || (Integer.parseInt(objList[3]) < 0 || Integer.parseInt(objList[3]) > 3)) throw new FileContentsException();
				plant.setX(Integer.parseInt(objList[2]));
				plant.setY(Integer.parseInt(objList[3]));
				if (Integer.parseInt(objList[1]) < 0 || Integer.parseInt(objList[1]) > plant.getMaxHealth()) throw new FileContentsException();
				plant.setHealth(Integer.parseInt(objList[1]));
				if (Integer.parseInt(objList[4]) < 0) throw new FileContentsException();
				plant.setTurn(Integer.parseInt(objList[4]));
				pL.addObject(plant);
			}
		} else throw new FileContentsException();
		
		
		ZombieList zL = new ZombieList(loadLevel.getAmount());//Carga la lista de zombies
		line = this.loadLine(reader, "zombieList", true);
		if (line.length >= 0 && line.length + remZombies <= loadLevel.getAmount()) {
			for (int i = 0; i < line.length; i++) {
				objList = line[i].split(":");
				if (objList.length != 5) throw new FileContentsException(); //symbol[0]:lr[1]:x[2]:y[3]:t[4]
				Zombie zombie = ZombieFactory.parseZombie(objList[0]);
				if (zombie == null) throw new FileContentsException();
				zombie.setGame(loadGame);
				if ((Integer.parseInt(objList[2]) < 0  || Integer.parseInt(objList[2]) > 7) || (Integer.parseInt(objList[3]) < 0 || Integer.parseInt(objList[3]) > 3)) throw new FileContentsException();
				zombie.setX(Integer.parseInt(objList[2]));
				zombie.setY(Integer.parseInt(objList[3]));
				if (Integer.parseInt(objList[1]) < 0 || Integer.parseInt(objList[1]) > zombie.getMaxHealth()) throw new FileContentsException();
				zombie.setHealth(Integer.parseInt(objList[1]));
				if (Integer.parseInt(objList[4]) < 0) throw new FileContentsException();
				zombie.setTurn(Integer.parseInt(objList[4]));
				zL.addObject(zombie);
			}
			
		} else throw new FileContentsException();
		
		loadGame.setPlantList(pL);
		loadGame.setZombieList(zL);
		this.replaceGame(loadGame);
		
	}
	public void replaceGame(Game game) {///Para reemplazar la partida con una partida leida.
		this.level = game.level;
		this.plantList = game.plantList;
		this.zombieList = game.zombieList;
		this.zManager = game.zManager;
		this.scManager = game.scManager;
		this.currentCycle = game.currentCycle;
		this.zAmount = game.zAmount;
		this.zFrequency = game.zFrequency;
		this.seed = game.seed;
		this.rand = game.rand;
	}

	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList)
			throws IOException, FileContentsException{
		String[] words;
		String line = inStream.readLine().trim();
		// absence of the prefix is invalid
		if (!line.startsWith(prefix + ":") )
			throw new FileContentsException(wrongPrefixMsg + prefix);
		// cut the prefix and the following colon off the line then trim it to get attribute contents
		String contentString = line.substring(prefix.length()+1).trim();
		// the attribute contents are not empty
		if (!contentString.equals("")) {
			if (!isList ) {
				// split non−list attribute contents into words
				// using 1−or−more−white−spaces as separator
				words = contentString.split("\\s+");
				// a non−list attribute with contents of more than one word is invalid
				if (words.length != 1)
					throw new FileContentsException(lineTooLongMsg + prefix);
			} else
				// split list attribute contents into words
				// using comma+0−or−more−white−spaces as separator
				words = contentString.split (",\\s*");
			// the attribute contents are empty
		} else {
			// a non−list attribute with empty contents is invalid
			if (!isList )
				throw new FileContentsException(lineTooShortMsg + prefix);
			// a list attribute with empty contents is valid; use a zero−length array to store its words
			words = new String[0];
		}
		return words;
	}

	//----------------------------------------------------//
}