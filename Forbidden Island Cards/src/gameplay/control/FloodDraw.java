package gameplay.control;
import gameplay.view.GameInputs;
import gameplay.view.GameOutputs;
    
    /**
     * PlayerTurn class manages all of the options a player can make whilst they
     * have a turn.
     * 
     * @author Cathal Ryan and Conor Kneafsey
     *
     */
    public class FloodDraw {
    
        // ===========================================================
        // Setup Variables
        // ===========================================================
        private GameOutputs theOutputs;
        private GameInputs theInputs;   
        private Controller theController; 
    
        public FloodDraw() {
            theOutputs = new GameOutputs();
            theInputs = new GameInputs();    
            this.theController = Controller.getInstance();
        }
    
        public void doFloodDraw() {
            theOutputs.floodDrawTime();
            int waterLevel = theController.getWaterLevel();
            for(int i =0;i<waterLevel;i++) {
                theOutputs.cardsLeft(waterLevel-i);
                boolean isSpecialPlayed = theController.enquirePlayers(false);
                if(theController.isGameOver()){
                    return;
                }
                if(isSpecialPlayed){
                    theOutputs.pressReturn();
                    theInputs.confirm(); //
                }             
                theController.dealFloodCard();
                if(theController.isGameOver()){
                    return; //Game is lost
                }
            }    
            theOutputs.turnEndo();    
        }
}    

