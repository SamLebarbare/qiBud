
import play.Logger;
import play.PlayPlugin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sloup
 */
public class BaseBuds extends PlayPlugin{
    
    @Override
    public void onApplicationStart(){
        Logger.info("Starting BaseBuds Module");
    }
    
}
