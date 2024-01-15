package tp.p1.logic.managers;

public class SuncoinManager {
	private int currentSuncoins = 0;
	
	//----CONSTRUCTOR-------------------------------------//
	
	public SuncoinManager (int currentSuncoins){
		this.currentSuncoins = currentSuncoins;
	}

	//---------------------------------------------------//
	
	public int getCurrentSuncoins() {
		return currentSuncoins;
	}

	public void setCurrentSuncoins(int Suncoins) {
		currentSuncoins = Suncoins;
	} 
	
	public void increaseCurrentSuncoins(int Suncoins) {
		currentSuncoins += Suncoins;
	}
	
	public void paySunCoins(int cost) {
		currentSuncoins -= cost;
	}
}