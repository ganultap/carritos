package geniar.siscar.util;

/**
 * 
 * @author Rorigo Lopez Ramos 
 *  Geniar WEB
 */

public class  IsNullOrEmpty {

    public static boolean isNullOrEmpty(Object object){
    	if(object==null)
    		return true;
    	if (object instanceof String && ((String) object).trim().length()==0)
    		return true;
    	
    	return false;
    }
}
